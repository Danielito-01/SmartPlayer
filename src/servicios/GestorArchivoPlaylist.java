package servicios;

import estructuras.BibliotecaGeneral;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import modelos.Musica;
import modelos.Playlist;

public class GestorArchivoPlaylist {

    private static final String CABECERA_PLAYLIST = "SMARTPLAYER_PLAYLIST";
    private static final String VERSION = "1";

    private static final String CABECERA_ENCRIPTADA = "SMARTPLAYER_ENCRYPTED";
    private static final String ALGORITMO = "AES/GCM/NoPadding";
    private static final int TAMANIO_SALT = 16;
    private static final int TAMANIO_IV = 12;
    private static final int TAMANIO_TAG = 128;
    private static final int ITERACIONES = 65536;
    private static final int TAMANIO_CLAVE = 256;

    private final BibliotecaGeneral biblioteca;

    public GestorArchivoPlaylist() {
        biblioteca = BibliotecaGeneral.getInstance();
    }

    public String exportarPlaylist(int idPlaylist, String rutaArchivo, boolean encriptada, String clave) {
        Playlist playlist = biblioteca.buscarPlaylistPorId(idPlaylist);

        if (playlist == null) {
            biblioteca.getHistorial().registrarExportacionPlaylist(
                    idPlaylist,
                    "No encontrada",
                    rutaArchivo,
                    encriptada ? "SPP-AES" : "SPP",
                    0,
                    encriptada,
                    false,
                    "Playlist no encontrada"
            );

            return "No se pudo exportar la playlist.\n"
                    + "Motivo: Playlist no encontrada.";
        }

        if (rutaArchivo == null || rutaArchivo.trim().isEmpty()) {
            biblioteca.getHistorial().registrarExportacionPlaylist(
                    playlist.getId(),
                    playlist.getNombre(),
                    "",
                    encriptada ? "SPP-AES" : "SPP",
                    playlist.getCantidad(),
                    encriptada,
                    false,
                    "Ruta inválida"
            );

            return "No se pudo exportar la playlist.\n"
                    + "Motivo: Ruta inválida.";
        }

        if (encriptada && (clave == null || clave.isBlank())) {
            biblioteca.getHistorial().registrarExportacionPlaylist(
                    playlist.getId(),
                    playlist.getNombre(),
                    rutaArchivo,
                    "SPP-AES",
                    playlist.getCantidad(),
                    true,
                    false,
                    "Clave vacía"
            );

            return "No se pudo exportar la playlist.\n"
                    + "Motivo: Debes ingresar una clave para encriptar.";
        }

        try {
            String contenido = crearContenidoPlaylist(playlist);
            File archivo = new File(rutaArchivo);

            crearCarpetaDestino(archivo);

            if (encriptada) {
                byte[] datosEncriptados = encriptarBytes(
                        contenido.getBytes(StandardCharsets.UTF_8),
                        clave
                );

                Files.write(archivo.toPath(), datosEncriptados);
            } else {
                Files.writeString(archivo.toPath(), contenido, StandardCharsets.UTF_8);
            }

            biblioteca.getHistorial().registrarExportacionPlaylist(
                    playlist.getId(),
                    playlist.getNombre(),
                    rutaArchivo,
                    encriptada ? "SPP-AES" : "SPP",
                    playlist.getCantidad(),
                    encriptada,
                    true,
                    "Playlist exportada correctamente"
            );

            return "Playlist exportada correctamente.\n"
                    + "Archivo: " + rutaArchivo;

        } catch (Exception e) {
            biblioteca.getHistorial().registrarExportacionPlaylist(
                    playlist.getId(),
                    playlist.getNombre(),
                    rutaArchivo,
                    encriptada ? "SPP-AES" : "SPP",
                    playlist.getCantidad(),
                    encriptada,
                    false,
                    e.getMessage()
            );

            return "No se pudo exportar la playlist.\n"
                    + "Motivo: " + e.getMessage();
        }
    }

    public String importarPlaylist(String rutaArchivo, boolean encriptada, String clave) {
        if (rutaArchivo == null || rutaArchivo.trim().isEmpty()) {
            biblioteca.getHistorial().registrarImportacionPlaylist(
                    "",
                    "",
                    0,
                    0,
                    0,
                    0,
                    encriptada,
                    false,
                    "Ruta inválida"
            );

            return "No se pudo importar la playlist.\n"
                    + "Motivo: Ruta inválida.";
        }

        if (encriptada && (clave == null || clave.isBlank())) {
            biblioteca.getHistorial().registrarImportacionPlaylist(
                    rutaArchivo,
                    "",
                    0,
                    0,
                    0,
                    0,
                    true,
                    false,
                    "Clave vacía"
            );

            return "No se pudo importar la playlist.\n"
                    + "Motivo: Debes ingresar la clave.";
        }

        try {
            File archivo = new File(rutaArchivo);

            if (!archivo.exists() || !archivo.isFile()) {
                biblioteca.getHistorial().registrarImportacionPlaylist(
                        rutaArchivo,
                        "",
                        0,
                        0,
                        0,
                        0,
                        encriptada,
                        false,
                        "Archivo no encontrado"
                );

                return "No se pudo importar la playlist.\n"
                        + "Motivo: Archivo no encontrado.";
            }

            boolean archivoEncriptado = esArchivoEncriptado(archivo);
            boolean archivoNormal = esArchivoPlaylistNormal(archivo);

            if (archivoEncriptado && !encriptada) {
                biblioteca.getHistorial().registrarImportacionPlaylist(
                        rutaArchivo,
                        "",
                        0,
                        0,
                        0,
                        0,
                        false,
                        false,
                        "El archivo está encriptado, pero no se marcó la opción de desencriptar"
                );

                return "No se pudo importar la playlist.\n"
                        + "Motivo: El archivo está encriptado. Marca la opción \"Desencriptar\" e ingresa la clave.";
            }

            if (!archivoEncriptado && encriptada) {
                biblioteca.getHistorial().registrarImportacionPlaylist(
                        rutaArchivo,
                        "",
                        0,
                        0,
                        0,
                        0,
                        true,
                        false,
                        "El archivo no está encriptado, pero se intentó desencriptar"
                );

                return "No se pudo importar la playlist.\n"
                        + "Motivo: El archivo no está encriptado. Desmarca la opción \"Desencriptar\".";
            }

            if (!archivoEncriptado && !archivoNormal) {
                biblioteca.getHistorial().registrarImportacionPlaylist(
                        rutaArchivo,
                        "",
                        0,
                        0,
                        0,
                        0,
                        false,
                        false,
                        "El archivo no tiene formato de playlist SmartPlayer"
                );

                return "No se pudo importar la playlist.\n"
                        + "Motivo: El archivo no es una playlist válida de SmartPlayer.";
            }

            String contenido;

            if (encriptada) {
                byte[] datos = Files.readAllBytes(archivo.toPath());
                byte[] desencriptados = desencriptarBytes(datos, clave);
                contenido = new String(desencriptados, StandardCharsets.UTF_8);
            } else {
                contenido = Files.readString(archivo.toPath(), StandardCharsets.UTF_8);
            }

            DatosPlaylist datosPlaylist = leerContenidoPlaylist(contenido);

            if (datosPlaylist.nombrePlaylist.isEmpty()) {
                biblioteca.getHistorial().registrarImportacionPlaylist(
                        rutaArchivo,
                        "",
                        datosPlaylist.rutas.size(),
                        0,
                        0,
                        datosPlaylist.rutas.size(),
                        encriptada,
                        false,
                        "El archivo no contiene una playlist válida"
                );

                return "No se pudo importar la playlist.\n"
                        + "Motivo: El archivo no contiene una playlist válida.";
            }

            String nombreFinal = crearNombreDisponible(datosPlaylist.nombrePlaylist);
            String mensajeCrear = biblioteca.crearPlaylist(nombreFinal);
            Playlist playlistCreada = biblioteca.buscarPlaylistPorNombre(nombreFinal);

            if (playlistCreada == null) {
                biblioteca.getHistorial().registrarImportacionPlaylist(
                        rutaArchivo,
                        nombreFinal,
                        datosPlaylist.rutas.size(),
                        0,
                        0,
                        datosPlaylist.rutas.size(),
                        encriptada,
                        false,
                        mensajeCrear
                );

                return "No se pudo importar la playlist.\n"
                        + "Motivo: No se pudo crear la playlist.";
            }

            List<Musica> musicasEncontradas = new ArrayList<>();
            int faltantes = 0;

            for (String ruta : datosPlaylist.rutas) {
                Musica musica = biblioteca.buscarPorRuta(ruta);

                if (musica == null) {
                    faltantes++;
                } else {
                    musicasEncontradas.add(musica);
                }
            }

            int antes = playlistCreada.getCantidad();

            biblioteca.agregarMusicasAPlaylist(
                    playlistCreada.getId(),
                    musicasEncontradas
            );

            int agregadas = playlistCreada.getCantidad() - antes;
            int duplicadas = musicasEncontradas.size() - agregadas;

            biblioteca.getHistorial().registrarImportacionPlaylist(
                    rutaArchivo,
                    playlistCreada.getNombre(),
                    datosPlaylist.rutas.size(),
                    agregadas,
                    duplicadas,
                    faltantes,
                    encriptada,
                    true,
                    "Importación finalizada"
            );

            return "Playlist importada correctamente.\n"
                    + "Nombre: " + playlistCreada.getNombre() + "\n"
                    + "Músicas leídas: " + datosPlaylist.rutas.size() + "\n"
                    + "Agregadas: " + agregadas + "\n"
                    + "Duplicadas: " + duplicadas + "\n"
                    + "Faltantes en biblioteca: " + faltantes;

        } catch (AEADBadTagException e) {
            biblioteca.getHistorial().registrarImportacionPlaylist(
                    rutaArchivo,
                    "",
                    0,
                    0,
                    0,
                    0,
                    true,
                    false,
                    "Clave incorrecta o archivo dañado"
            );

            return "No se pudo importar la playlist.\n"
                    + "Motivo: La clave es incorrecta o el archivo está dañado.";

        } catch (Exception e) {
            biblioteca.getHistorial().registrarImportacionPlaylist(
                    rutaArchivo,
                    "",
                    0,
                    0,
                    0,
                    0,
                    encriptada,
                    false,
                    e.getMessage()
            );

            return "No se pudo importar la playlist.\n"
                    + "Motivo: " + e.getMessage();
        }
    }

    public String encriptarArchivo(String rutaOrigen, String rutaDestino, String clave) {
        if (rutaOrigen == null || rutaOrigen.trim().isEmpty()) {
            biblioteca.getHistorial().registrarEncriptacionPlaylist(
                    0,
                    "Archivo de playlist",
                    rutaDestino,
                    ALGORITMO,
                    false,
                    "Ruta de origen inválida"
            );

            return "No se pudo encriptar.\n"
                    + "Motivo: Ruta de origen inválida.";
        }

        if (rutaDestino == null || rutaDestino.trim().isEmpty()) {
            biblioteca.getHistorial().registrarEncriptacionPlaylist(
                    0,
                    "Archivo de playlist",
                    "",
                    ALGORITMO,
                    false,
                    "Ruta de destino inválida"
            );

            return "No se pudo encriptar.\n"
                    + "Motivo: Ruta de destino inválida.";
        }

        if (clave == null || clave.isBlank()) {
            biblioteca.getHistorial().registrarEncriptacionPlaylist(
                    0,
                    "Archivo de playlist",
                    rutaDestino,
                    ALGORITMO,
                    false,
                    "Clave vacía"
            );

            return "No se pudo encriptar.\n"
                    + "Motivo: Debes ingresar una clave.";
        }

        try {
            File origen = new File(rutaOrigen);
            File destino = new File(rutaDestino);

            if (!origen.exists() || !origen.isFile()) {
                biblioteca.getHistorial().registrarEncriptacionPlaylist(
                        0,
                        "Archivo de playlist",
                        rutaDestino,
                        ALGORITMO,
                        false,
                        "Archivo origen no encontrado"
                );

                return "No se pudo encriptar.\n"
                        + "Motivo: Archivo origen no encontrado.";
            }

            if (esMismoArchivo(origen, destino)) {
                biblioteca.getHistorial().registrarEncriptacionPlaylist(
                        0,
                        "Archivo de playlist",
                        rutaDestino,
                        ALGORITMO,
                        false,
                        "El archivo origen y destino son el mismo"
                );

                return "No se pudo encriptar.\n"
                        + "Motivo: El archivo origen y destino no pueden ser el mismo.";
            }

            if (esArchivoEncriptado(origen)) {
                biblioteca.getHistorial().registrarEncriptacionPlaylist(
                        0,
                        "Archivo de playlist",
                        rutaDestino,
                        ALGORITMO,
                        false,
                        "El archivo ya está encriptado"
                );

                return "No se pudo encriptar.\n"
                        + "Motivo: El archivo ya está encriptado.";
            }

            if (!esArchivoPlaylistNormal(origen)) {
                biblioteca.getHistorial().registrarEncriptacionPlaylist(
                        0,
                        "Archivo de playlist",
                        rutaDestino,
                        ALGORITMO,
                        false,
                        "El archivo no es una playlist normal de SmartPlayer"
                );

                return "No se pudo encriptar.\n"
                        + "Motivo: Solo puedes encriptar archivos de playlist normales de SmartPlayer.";
            }

            crearCarpetaDestino(destino);

            byte[] datos = Files.readAllBytes(origen.toPath());
            byte[] encriptados = encriptarBytes(datos, clave);

            Files.write(destino.toPath(), encriptados);

            biblioteca.getHistorial().registrarEncriptacionPlaylist(
                    0,
                    "Archivo de playlist",
                    rutaDestino,
                    ALGORITMO,
                    true,
                    "Archivo encriptado correctamente"
            );

            return "Archivo encriptado correctamente.\n"
                    + "Destino: " + rutaDestino;

        } catch (Exception e) {
            biblioteca.getHistorial().registrarEncriptacionPlaylist(
                    0,
                    "Archivo de playlist",
                    rutaDestino,
                    ALGORITMO,
                    false,
                    e.getMessage()
            );

            return "No se pudo encriptar.\n"
                    + "Motivo: " + e.getMessage();
        }
    }

    public String desencriptarArchivo(String rutaOrigen, String rutaDestino, String clave) {
        if (rutaOrigen == null || rutaOrigen.trim().isEmpty()) {
            biblioteca.getHistorial().registrarDesencriptacionPlaylist(
                    "",
                    "Archivo de playlist",
                    ALGORITMO,
                    false,
                    "Ruta de origen inválida"
            );

            return "No se pudo desencriptar.\n"
                    + "Motivo: Ruta de origen inválida.";
        }

        if (rutaDestino == null || rutaDestino.trim().isEmpty()) {
            biblioteca.getHistorial().registrarDesencriptacionPlaylist(
                    rutaOrigen,
                    "Archivo de playlist",
                    ALGORITMO,
                    false,
                    "Ruta de destino inválida"
            );

            return "No se pudo desencriptar.\n"
                    + "Motivo: Ruta de destino inválida.";
        }

        if (clave == null || clave.isBlank()) {
            biblioteca.getHistorial().registrarDesencriptacionPlaylist(
                    rutaOrigen,
                    "Archivo de playlist",
                    ALGORITMO,
                    false,
                    "Clave vacía"
            );

            return "No se pudo desencriptar.\n"
                    + "Motivo: Debes ingresar una clave.";
        }

        try {
            File origen = new File(rutaOrigen);
            File destino = new File(rutaDestino);

            if (!origen.exists() || !origen.isFile()) {
                biblioteca.getHistorial().registrarDesencriptacionPlaylist(
                        rutaOrigen,
                        "Archivo de playlist",
                        ALGORITMO,
                        false,
                        "Archivo origen no encontrado"
                );

                return "No se pudo desencriptar.\n"
                        + "Motivo: Archivo origen no encontrado.";
            }

            if (esMismoArchivo(origen, destino)) {
                biblioteca.getHistorial().registrarDesencriptacionPlaylist(
                        rutaOrigen,
                        "Archivo de playlist",
                        ALGORITMO,
                        false,
                        "El archivo origen y destino son el mismo"
                );

                return "No se pudo desencriptar.\n"
                        + "Motivo: El archivo origen y destino no pueden ser el mismo.";
            }

            if (!esArchivoEncriptado(origen)) {
                biblioteca.getHistorial().registrarDesencriptacionPlaylist(
                        rutaOrigen,
                        "Archivo de playlist",
                        ALGORITMO,
                        false,
                        "El archivo no está encriptado"
                );

                return "No se pudo desencriptar.\n"
                        + "Motivo: El archivo no está encriptado.";
            }

            crearCarpetaDestino(destino);

            byte[] datos = Files.readAllBytes(origen.toPath());
            byte[] desencriptados = desencriptarBytes(datos, clave);

            Files.write(destino.toPath(), desencriptados);

            biblioteca.getHistorial().registrarDesencriptacionPlaylist(
                    rutaOrigen,
                    "Archivo de playlist",
                    ALGORITMO,
                    true,
                    "Archivo desencriptado correctamente"
            );

            return "Archivo desencriptado correctamente.\n"
                    + "Destino: " + rutaDestino;

        } catch (AEADBadTagException e) {
            biblioteca.getHistorial().registrarDesencriptacionPlaylist(
                    rutaOrigen,
                    "Archivo de playlist",
                    ALGORITMO,
                    false,
                    "Clave incorrecta o archivo dañado"
            );

            return "No se pudo desencriptar.\n"
                    + "Motivo: La clave es incorrecta o el archivo está dañado.";

        } catch (Exception e) {
            biblioteca.getHistorial().registrarDesencriptacionPlaylist(
                    rutaOrigen,
                    "Archivo de playlist",
                    ALGORITMO,
                    false,
                    e.getMessage()
            );

            return "No se pudo desencriptar.\n"
                    + "Motivo: " + e.getMessage();
        }
    }

    private String crearContenidoPlaylist(Playlist playlist) {
        StringBuilder sb = new StringBuilder();

        sb.append(CABECERA_PLAYLIST).append("\n");
        sb.append("VERSION=").append(VERSION).append("\n");
        sb.append("NOMBRE=").append(codificar(playlist.getNombre())).append("\n");
        sb.append("CANCIONES=").append(playlist.getCantidad()).append("\n");

        for (Musica musica : playlist.getMusicas()) {
            sb.append("RUTA=").append(codificar(musica.getRuta())).append("\n");
        }

        return sb.toString();
    }

    private DatosPlaylist leerContenidoPlaylist(String contenido) {
        DatosPlaylist datos = new DatosPlaylist();

        if (contenido == null || contenido.trim().isEmpty()) {
            return datos;
        }

        String[] lineas = contenido.split("\\R");

        if (lineas.length == 0 || !CABECERA_PLAYLIST.equals(lineas[0].trim())) {
            return datos;
        }

        for (String linea : lineas) {
            if (linea.startsWith("NOMBRE=")) {
                datos.nombrePlaylist = decodificar(linea.substring("NOMBRE=".length()));
            } else if (linea.startsWith("RUTA=")) {
                datos.rutas.add(decodificar(linea.substring("RUTA=".length())));
            }
        }

        return datos;
    }

    private String crearNombreDisponible(String nombreOriginal) {
        String nombreBase = nombreOriginal == null ? "Playlist importada" : nombreOriginal.trim();

        if (nombreBase.isEmpty()) {
            nombreBase = "Playlist importada";
        }

        if (!biblioteca.existePlaylist(nombreBase)) {
            return nombreBase;
        }

        int contador = 1;

        while (biblioteca.existePlaylist(nombreBase + " (" + contador + ")")) {
            contador++;
        }

        return nombreBase + " (" + contador + ")";
    }

    private byte[] encriptarBytes(byte[] datos, String clave) throws Exception {
        byte[] salt = generarBytes(TAMANIO_SALT);
        byte[] iv = generarBytes(TAMANIO_IV);

        SecretKey key = crearClave(clave, salt);

        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAMANIO_TAG, iv));

        byte[] cifrado = cipher.doFinal(datos);

        StringBuilder sb = new StringBuilder();
        sb.append(CABECERA_ENCRIPTADA).append("\n");
        sb.append("SALT=").append(Base64.getEncoder().encodeToString(salt)).append("\n");
        sb.append("IV=").append(Base64.getEncoder().encodeToString(iv)).append("\n");
        sb.append("DATOS=").append(Base64.getEncoder().encodeToString(cifrado)).append("\n");

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    private byte[] desencriptarBytes(byte[] archivo, String clave) throws Exception {
        String contenido = new String(archivo, StandardCharsets.UTF_8);
        String[] lineas = contenido.split("\\R");

        if (lineas.length < 4 || !CABECERA_ENCRIPTADA.equals(lineas[0].trim())) {
            throw new Exception("El archivo no tiene formato encriptado de SmartPlayer.");
        }

        String saltTexto = "";
        String ivTexto = "";
        String datosTexto = "";

        for (String linea : lineas) {
            if (linea.startsWith("SALT=")) {
                saltTexto = linea.substring("SALT=".length());
            } else if (linea.startsWith("IV=")) {
                ivTexto = linea.substring("IV=".length());
            } else if (linea.startsWith("DATOS=")) {
                datosTexto = linea.substring("DATOS=".length());
            }
        }

        if (saltTexto.isBlank() || ivTexto.isBlank() || datosTexto.isBlank()) {
            throw new Exception("El archivo encriptado está incompleto o dañado.");
        }

        byte[] salt = Base64.getDecoder().decode(saltTexto);
        byte[] iv = Base64.getDecoder().decode(ivTexto);
        byte[] datos = Base64.getDecoder().decode(datosTexto);

        SecretKey key = crearClave(clave, salt);

        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(TAMANIO_TAG, iv));

        return cipher.doFinal(datos);
    }

    private SecretKey crearClave(String clave, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(
                clave.toCharArray(),
                salt,
                ITERACIONES,
                TAMANIO_CLAVE
        );

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] bytes = factory.generateSecret(spec).getEncoded();

        return new SecretKeySpec(bytes, "AES");
    }

    private byte[] generarBytes(int cantidad) {
        byte[] bytes = new byte[cantidad];
        new SecureRandom().nextBytes(bytes);
        return bytes;
    }

    private String codificar(String texto) {
        if (texto == null) {
            texto = "";
        }

        return Base64.getEncoder().encodeToString(texto.getBytes(StandardCharsets.UTF_8));
    }

    private String decodificar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return "";
        }

        return new String(Base64.getDecoder().decode(texto), StandardCharsets.UTF_8);
    }

    private String leerPrimeraLinea(File archivo) {
        try (BufferedReader br = Files.newBufferedReader(
                archivo.toPath(),
                StandardCharsets.UTF_8
        )) {
            String linea = br.readLine();
            return linea == null ? "" : linea.trim();
        } catch (Exception e) {
            return "";
        }
    }

    private boolean esArchivoEncriptado(File archivo) {
        return CABECERA_ENCRIPTADA.equals(leerPrimeraLinea(archivo));
    }

    private boolean esArchivoPlaylistNormal(File archivo) {
        return CABECERA_PLAYLIST.equals(leerPrimeraLinea(archivo));
    }

    private void crearCarpetaDestino(File archivo) {
        File carpeta = archivo.getParentFile();

        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    private boolean esMismoArchivo(File origen, File destino) {
        try {
            return origen.getCanonicalPath().equals(destino.getCanonicalPath());
        } catch (Exception e) {
            return origen.getAbsolutePath().equals(destino.getAbsolutePath());
        }
    }

    private static class DatosPlaylist {
        private String nombrePlaylist = "";
        private final List<String> rutas = new ArrayList<>();
    }
}