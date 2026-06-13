package servicios;

import estructuras.ArbolAVL;
import estructuras.BibliotecaGeneral;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelos.Musica;
import modelos.Playlist;

public class GestorArchivoPlaylist {
    private static final String CABECERA_PLAYLIST = "SMARTPLAYER_PLAYLIST";
    private static final String VERSION = "2";

    private static final String CABECERA_ENCRIPTADA = "SMARTPLAYER_ENCRYPTED";
    private static final String ALGORITMO = "SPP-MANUAL-XOR-ROT";
    private static final String COMPRESION = "LZW-MANUAL";
    private static final String RECORRIDO = "INORDEN";
    private static final int TAMANIO_SALT = 16;

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
                    encriptada ? ALGORITMO : "SPP",
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
                    encriptada ? ALGORITMO : "SPP",
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
                    ALGORITMO,
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
                    encriptada ? ALGORITMO : "SPP",
                    playlist.getCantidad(),
                    encriptada,
                    true,
                    "Playlist exportada correctamente usando recorrido " + RECORRIDO
            );

            return "Playlist exportada correctamente.\n"
                    + "Archivo: " + rutaArchivo + "\n"
                    + "Recorrido usado: " + RECORRIDO + "\n"
                    + (encriptada
                    ? "Compresión: " + COMPRESION + "\n"
                    + "Encriptación: " + ALGORITMO
                    : "");

        } catch (Exception e) {
            biblioteca.getHistorial().registrarExportacionPlaylist(
                    playlist.getId(),
                    playlist.getNombre(),
                    rutaArchivo,
                    encriptada ? ALGORITMO : "SPP",
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

        } catch (ArchivoSeguroException e) {
            biblioteca.getHistorial().registrarImportacionPlaylist(
                    rutaArchivo,
                    "",
                    0,
                    0,
                    0,
                    0,
                    true,
                    false,
                    e.getMessage()
            );

            return "No se pudo importar la playlist.\n"
                    + "Motivo: " + e.getMessage();

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
                    "Archivo encriptado correctamente con compresión manual"
            );

            return "Archivo encriptado correctamente.\n"
                    + "Destino: " + rutaDestino + "\n"
                    + "Compresión: " + COMPRESION + "\n"
                    + "Encriptación: " + ALGORITMO;

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

        } catch (ArchivoSeguroException e) {
            biblioteca.getHistorial().registrarDesencriptacionPlaylist(
                    rutaOrigen,
                    "Archivo de playlist",
                    ALGORITMO,
                    false,
                    e.getMessage()
            );

            return "No se pudo desencriptar.\n"
                    + "Motivo: " + e.getMessage();

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
        ArbolAVL arbolPlaylist = new ArbolAVL();

        for (Musica musica : playlist.getMusicas()) {
            if (musica != null) {
                arbolPlaylist.agregarMusica(musica);
            }
        }

        List<Musica> musicasInOrden = arbolPlaylist.recorridoInOrden();

        sb.append(CABECERA_PLAYLIST).append("\n");
        sb.append("VERSION=").append(VERSION).append("\n");
        sb.append("RECORRIDO=").append(RECORRIDO).append("\n");
        sb.append("NOMBRE=").append(codificar(playlist.getNombre())).append("\n");
        sb.append("CANCIONES=").append(musicasInOrden.size()).append("\n");

        for (Musica musica : musicasInOrden) {
            if (musica != null && musica.getRuta() != null) {
                sb.append("RUTA=").append(codificar(musica.getRuta())).append("\n");
            }
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
        byte[] comprimidos = comprimirLZW(datos);
        byte[] salt = generarBytes(TAMANIO_SALT);
        byte[] cifrados = aplicarCifradoManual(comprimidos, clave, salt, true);
        long check = calcularVerificacion(comprimidos, clave, salt);

        StringBuilder sb = new StringBuilder();

        sb.append(CABECERA_ENCRIPTADA).append("\n");
        sb.append("VERSION=").append(VERSION).append("\n");
        sb.append("ALGORITMO=").append(ALGORITMO).append("\n");
        sb.append("COMPRESION=").append(COMPRESION).append("\n");
        sb.append("RECORRIDO=").append(RECORRIDO).append("\n");
        sb.append("SALT=").append(Base64.getEncoder().encodeToString(salt)).append("\n");
        sb.append("CHECK=").append(Long.toUnsignedString(check)).append("\n");
        sb.append("DATOS=").append(Base64.getEncoder().encodeToString(cifrados)).append("\n");

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    private byte[] desencriptarBytes(byte[] archivo, String clave) throws Exception {
        String contenido = new String(archivo, StandardCharsets.UTF_8);
        String[] lineas = contenido.split("\\R");

        if (lineas.length < 8 || !CABECERA_ENCRIPTADA.equals(lineas[0].trim())) {
            throw new ArchivoSeguroException("El archivo no tiene formato encriptado de SmartPlayer.");
        }

        String saltTexto = "";
        String checkTexto = "";
        String datosTexto = "";

        for (String linea : lineas) {
            if (linea.startsWith("SALT=")) {
                saltTexto = linea.substring("SALT=".length());
            } else if (linea.startsWith("CHECK=")) {
                checkTexto = linea.substring("CHECK=".length());
            } else if (linea.startsWith("DATOS=")) {
                datosTexto = linea.substring("DATOS=".length());
            }
        }

        if (saltTexto.isBlank() || checkTexto.isBlank() || datosTexto.isBlank()) {
            throw new ArchivoSeguroException("El archivo encriptado está incompleto o dañado.");
        }

        try {
            byte[] salt = Base64.getDecoder().decode(saltTexto);
            byte[] cifrados = Base64.getDecoder().decode(datosTexto);
            byte[] comprimidos = aplicarCifradoManual(cifrados, clave, salt, false);

            long checkGuardado = Long.parseUnsignedLong(checkTexto);
            long checkCalculado = calcularVerificacion(comprimidos, clave, salt);

            if (checkGuardado != checkCalculado) {
                throw new ArchivoSeguroException("La clave es incorrecta o el archivo está dañado.");
            }

            return descomprimirLZW(comprimidos);

        } catch (ArchivoSeguroException e) {
            throw e;
        } catch (Exception e) {
            throw new ArchivoSeguroException("La clave es incorrecta o el archivo está dañado.");
        }
    }

    private byte[] aplicarCifradoManual(byte[] datos, String clave, byte[] salt, boolean encriptar) throws Exception {
        if (clave == null || clave.isBlank()) {
            throw new Exception("La clave no puede estar vacía.");
        }

        byte[] claveBytes = clave.getBytes(StandardCharsets.UTF_8);
        byte[] resultado = new byte[datos.length];

        long estado = crearSemilla(claveBytes, salt);

        for (int i = 0; i < datos.length; i++) {
            estado = siguienteEstado(
                    estado,
                    claveBytes[i % claveBytes.length],
                    salt[i % salt.length],
                    i
            );

            int valor = datos[i] & 0xFF;
            int mascara = (int) (estado & 0xFF);
            int claveActual = claveBytes[i % claveBytes.length] & 0xFF;
            int rotacion = (i % 7) + 1;

            if (encriptar) {
                valor = valor ^ mascara ^ claveActual;
                valor = rotarIzquierda(valor, rotacion);
            } else {
                valor = rotarDerecha(valor, rotacion);
                valor = valor ^ mascara ^ claveActual;
            }

            resultado[i] = (byte) (valor & 0xFF);
        }

        return resultado;
    }

    private long crearSemilla(byte[] clave, byte[] salt) {
        long hash = 1469598103934665603L;

        for (byte b : clave) {
            hash ^= (b & 0xFF);
            hash *= 1099511628211L;
        }

        for (byte b : salt) {
            hash ^= (b & 0xFF);
            hash *= 1099511628211L;
        }

        return hash;
    }

    private long siguienteEstado(long estado, byte clave, byte salt, int posicion) {
        long valor = estado;

        valor ^= (clave & 0xFF);
        valor ^= ((long) (salt & 0xFF) << 8);
        valor ^= ((long) posicion << 16);

        valor ^= (valor << 13);
        valor ^= (valor >>> 7);
        valor ^= (valor << 17);

        return valor;
    }

    private int rotarIzquierda(int valor, int posiciones) {
        valor &= 0xFF;
        return ((valor << posiciones) | (valor >>> (8 - posiciones))) & 0xFF;
    }

    private int rotarDerecha(int valor, int posiciones) {
        valor &= 0xFF;
        return ((valor >>> posiciones) | (valor << (8 - posiciones))) & 0xFF;
    }

    private long calcularVerificacion(byte[] datos, String clave, byte[] salt) {
        long hash = 1469598103934665603L;
        byte[] claveBytes = clave.getBytes(StandardCharsets.UTF_8);

        for (byte b : claveBytes) {
            hash ^= (b & 0xFF);
            hash *= 1099511628211L;
        }

        for (byte b : salt) {
            hash ^= (b & 0xFF);
            hash *= 1099511628211L;
        }

        for (byte b : datos) {
            hash ^= (b & 0xFF);
            hash *= 1099511628211L;
        }

        return hash;
    }

    private byte[] comprimirLZW(byte[] datos) {
        if (datos == null || datos.length == 0) {
            return new byte[0];
        }

        Map<String, Integer> diccionario = new HashMap<>();

        for (int i = 0; i < 256; i++) {
            diccionario.put(String.valueOf((char) i), i);
        }

        int siguienteCodigo = 256;
        String actual = "";
        List<Integer> codigos = new ArrayList<>();

        for (byte dato : datos) {
            String caracter = String.valueOf((char) (dato & 0xFF));
            String combinado = actual + caracter;

            if (diccionario.containsKey(combinado)) {
                actual = combinado;
            } else {
                codigos.add(diccionario.get(actual));

                if (siguienteCodigo <= 65535) {
                    diccionario.put(combinado, siguienteCodigo);
                    siguienteCodigo++;
                }

                actual = caracter;
            }
        }

        if (!actual.isEmpty()) {
            codigos.add(diccionario.get(actual));
        }

        ByteArrayOutputStream salida = new ByteArrayOutputStream(codigos.size() * 2);

        for (int codigo : codigos) {
            salida.write((codigo >>> 8) & 0xFF);
            salida.write(codigo & 0xFF);
        }

        return salida.toByteArray();
    }

    private byte[] descomprimirLZW(byte[] datos) throws Exception {
        if (datos == null || datos.length == 0) {
            return new byte[0];
        }

        if (datos.length % 2 != 0) {
            throw new Exception("Los datos comprimidos están dañados.");
        }

        List<Integer> codigos = new ArrayList<>();

        for (int i = 0; i < datos.length; i += 2) {
            int codigo = ((datos[i] & 0xFF) << 8) | (datos[i + 1] & 0xFF);
            codigos.add(codigo);
        }

        Map<Integer, String> diccionario = new HashMap<>();

        for (int i = 0; i < 256; i++) {
            diccionario.put(i, String.valueOf((char) i));
        }

        int siguienteCodigo = 256;
        String actual = diccionario.get(codigos.get(0));

        if (actual == null) {
            throw new Exception("Los datos comprimidos están dañados.");
        }

        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        escribirCadenaBytes(salida, actual);

        for (int i = 1; i < codigos.size(); i++) {
            int codigo = codigos.get(i);
            String entrada;

            if (diccionario.containsKey(codigo)) {
                entrada = diccionario.get(codigo);
            } else if (codigo == siguienteCodigo) {
                entrada = actual + actual.charAt(0);
            } else {
                throw new Exception("Los datos comprimidos están dañados.");
            }

            escribirCadenaBytes(salida, entrada);

            if (siguienteCodigo <= 65535) {
                diccionario.put(siguienteCodigo, actual + entrada.charAt(0));
                siguienteCodigo++;
            }

            actual = entrada;
        }

        return salida.toByteArray();
    }

    private void escribirCadenaBytes(ByteArrayOutputStream salida, String texto) {
        for (int i = 0; i < texto.length(); i++) {
            salida.write(texto.charAt(i) & 0xFF);
        }
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

    private static class ArchivoSeguroException extends Exception {

        public ArchivoSeguroException(String mensaje) {
            super(mensaje);
        }
    }
}