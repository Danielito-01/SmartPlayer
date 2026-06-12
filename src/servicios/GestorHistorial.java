package servicios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import modelos.Musica;

public class GestorHistorial {

    public enum Tipo {
        CARGA,
        REPRODUCCION,
        PLAYLIST,
        ELIMINACION,
        BUSQUEDA,
        ARCHIVO,
        SEGURIDAD
    }

    public enum Resultado {
        CORRECTO,
        ADVERTENCIA,
        ERROR,
        INFORMATIVO
    }

    public enum Origen {
        BIBLIOTECA,
        PLAYLIST,
        COLA,
        BUSQUEDA,
        RECORRIDO_ARBOL,
        ARCHIVO,
        SEGURIDAD,
        DESCONOCIDO
    }

    public static final class RegistroHistorial {

        private static final DateTimeFormatter FORMATO_FECHA =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        private final int id;
        private final LocalDateTime fechaHora;
        private final Tipo tipo;
        private final String accion;
        private final Resultado resultado;
        private final String entidad;
        private final String idEntidad;
        private final String nombreEntidad;
        private final Origen origen;
        private final String nombreOrigen;
        private final String resumen;
        private final String detalle;

        private RegistroHistorial(
                int id,
                LocalDateTime fechaHora,
                Tipo tipo,
                String accion,
                Resultado resultado,
                String entidad,
                String idEntidad,
                String nombreEntidad,
                Origen origen,
                String nombreOrigen,
                String resumen,
                String detalle
        ) {
            this.id = id;
            this.fechaHora = fechaHora;
            this.tipo = tipo;
            this.accion = accion;
            this.resultado = resultado;
            this.entidad = entidad;
            this.idEntidad = idEntidad;
            this.nombreEntidad = nombreEntidad;
            this.origen = origen;
            this.nombreOrigen = nombreOrigen;
            this.resumen = resumen;
            this.detalle = detalle;
        }

        public int getId() {
            return id;
        }

        public LocalDateTime getFechaHora() {
            return fechaHora;
        }

        public String getFechaFormateada() {
            return fechaHora.format(FORMATO_FECHA);
        }

        public Tipo getTipo() {
            return tipo;
        }

        public String getAccion() {
            return accion;
        }

        public Resultado getResultado() {
            return resultado;
        }

        public String getEntidad() {
            return entidad;
        }

        public String getIdEntidad() {
            return idEntidad;
        }

        public String getNombreEntidad() {
            return nombreEntidad;
        }

        public Origen getOrigen() {
            return origen;
        }

        public String getNombreOrigen() {
            return nombreOrigen;
        }

        public String getResumen() {
            return resumen;
        }

        public String getDetalle() {
            return detalle;
        }

        public String getDetalleCompleto() {
            StringBuilder sb = new StringBuilder();

            sb.append("Registro #").append(id).append("\n");
            sb.append("Fecha: ").append(getFechaFormateada()).append("\n");
            sb.append("Tipo: ").append(tipo).append("\n");
            sb.append("Acción: ").append(accion).append("\n");
            sb.append("Resultado: ").append(resultado).append("\n");
            sb.append("Entidad: ").append(entidad).append("\n");
            sb.append("ID entidad: ").append(idEntidad).append("\n");
            sb.append("Nombre entidad: ").append(nombreEntidad).append("\n");
            sb.append("Origen: ").append(origen).append("\n");
            sb.append("Nombre origen: ").append(nombreOrigen).append("\n");
            sb.append("Resumen: ").append(resumen).append("\n\n");
            sb.append(detalle);

            return sb.toString();
        }

        @Override
        public String toString() {
            return getFechaFormateada()
                    + " | " + tipo
                    + " | " + accion
                    + " | " + resultado
                    + " | " + resumen;
        }
    }

    private final List<RegistroHistorial> registros;
    private int siguienteId;

    public GestorHistorial() {
        registros = new ArrayList<>();
        siguienteId = 1;
    }

    public RegistroHistorial registrar(
            Tipo tipo,
            String accion,
            Resultado resultado,
            String entidad,
            String idEntidad,
            String nombreEntidad,
            Origen origen,
            String nombreOrigen,
            String resumen,
            String detalle
    ) {
        Objects.requireNonNull(tipo, "El tipo del historial no puede ser null.");
        Objects.requireNonNull(resultado, "El resultado del historial no puede ser null.");

        RegistroHistorial registro = new RegistroHistorial(
                siguienteId++,
                LocalDateTime.now(),
                tipo,
                textoSeguro(accion),
                resultado,
                textoSeguro(entidad),
                textoSeguro(idEntidad),
                textoSeguro(nombreEntidad),
                origen == null ? Origen.DESCONOCIDO : origen,
                textoSeguro(nombreOrigen),
                textoSeguro(resumen),
                textoSeguro(detalle)
        );

        registros.add(registro);
        return registro;
    }

    public RegistroHistorial registrarCarga(
            int recibidas,
            int insertadas,
            int duplicadas,
            int invalidas,
            int fallidas,
            int totalBiblioteca,
            int totalABB,
            int totalAVL,
            int totalHash,
            long tiempoABB,
            long tiempoAVL,
            long tiempoTotal
    ) {
        Resultado resultado = resultadoCarga(recibidas, insertadas, duplicadas, invalidas, fallidas);
        String resumen = resumenCarga(recibidas, insertadas, duplicadas, invalidas, fallidas);

        StringBuilder detalle = new StringBuilder();

        detalle.append("Músicas recibidas: ").append(recibidas).append("\n");
        detalle.append("Músicas insertadas en esta carga: ").append(insertadas).append("\n");
        detalle.append("Músicas duplicadas: ").append(duplicadas).append("\n");
        detalle.append("Músicas inválidas: ").append(invalidas).append("\n");
        detalle.append("Músicas fallidas: ").append(fallidas).append("\n\n");

        detalle.append("Total en lista general: ").append(totalBiblioteca).append("\n");
        detalle.append("Total en ABB: ").append(totalABB).append("\n");
        detalle.append("Total en AVL: ").append(totalAVL).append("\n");
        detalle.append("Total en tabla hash: ").append(totalHash).append("\n\n");

        detalle.append("Tiempo de inserción ABB: ").append(formatearMs(tiempoABB)).append(" ms\n");
        detalle.append("Tiempo de inserción AVL: ").append(formatearMs(tiempoAVL)).append(" ms\n");
        detalle.append("Tiempo total de carga: ").append(formatearMs(tiempoTotal)).append(" ms");

        return registrar(
                Tipo.CARGA,
                "Cargar músicas",
                resultado,
                "Biblioteca",
                "",
                "Biblioteca general",
                Origen.ARCHIVO,
                "Carga de archivos",
                resumen,
                detalle.toString()
        );
    }

    public RegistroHistorial registrarReproduccion(Musica musica, Origen origen, String nombreOrigen) {
        if (musica == null) {
            return registrar(
                    Tipo.REPRODUCCION,
                    "Reproducir música",
                    Resultado.ERROR,
                    "Música",
                    "",
                    "No encontrada",
                    origen,
                    nombreOrigen,
                    "No se pudo reproducir la música porque no existe.",
                    "La música recibida por el historial era null."
            );
        }

        Origen origenFinal = origen == null ? Origen.DESCONOCIDO : origen;
        String nombreOrigenFinal = textoSeguro(nombreOrigen);

        if (nombreOrigenFinal.isEmpty()) {
            nombreOrigenFinal = nombreLegibleOrigen(origenFinal);
        }

        String resumen = "Se reprodujo \"" + musica.getNombre() + "\" desde " + nombreOrigenFinal + ".";

        StringBuilder detalle = new StringBuilder();
        detalle.append(detalleMusica(musica));
        detalle.append("\nOrigen: ").append(origenFinal).append("\n");
        detalle.append("Nombre origen: ").append(nombreOrigenFinal);

        return registrar(
                Tipo.REPRODUCCION,
                "Reproducir música",
                Resultado.CORRECTO,
                "Música",
                String.valueOf(musica.getId()),
                musica.getNombre(),
                origenFinal,
                nombreOrigenFinal,
                resumen,
                detalle.toString()
        );
    }

    public RegistroHistorial registrarCreacionPlaylist(
            int idPlaylist,
            String nombrePlaylist,
            boolean creada,
            String mensaje
    ) {
        Resultado resultado = creada ? Resultado.CORRECTO : Resultado.ADVERTENCIA;
        String nombre = textoSeguro(nombrePlaylist);

        String resumen = creada
                ? "Se creó la playlist \"" + nombre + "\"."
                : "No se pudo crear la playlist \"" + nombre + "\".";

        String detalle = "ID playlist: " + idPlaylist + "\n"
                + "Nombre: " + nombre + "\n"
                + "Creada: " + (creada ? "Sí" : "No") + "\n"
                + "Mensaje: " + textoSeguro(mensaje);

        return registrar(
                Tipo.PLAYLIST,
                "Crear playlist",
                resultado,
                "Playlist",
                String.valueOf(idPlaylist),
                nombre,
                Origen.PLAYLIST,
                nombre,
                resumen,
                detalle
        );
    }

    public RegistroHistorial registrarEdicionPlaylist(
            int idPlaylist,
            String nombreAnterior,
            String nombreNuevo,
            boolean editada,
            String mensaje
    ) {
        Resultado resultado = editada ? Resultado.CORRECTO : Resultado.ADVERTENCIA;

        String resumen = editada
                ? "Se cambió el nombre de la playlist \"" + textoSeguro(nombreAnterior)
                + "\" a \"" + textoSeguro(nombreNuevo) + "\"."
                : "No se pudo editar la playlist \"" + textoSeguro(nombreAnterior) + "\".";

        String detalle = "ID playlist: " + idPlaylist + "\n"
                + "Nombre anterior: " + textoSeguro(nombreAnterior) + "\n"
                + "Nombre nuevo: " + textoSeguro(nombreNuevo) + "\n"
                + "Editada: " + (editada ? "Sí" : "No") + "\n"
                + "Mensaje: " + textoSeguro(mensaje);

        return registrar(
                Tipo.PLAYLIST,
                "Editar playlist",
                resultado,
                "Playlist",
                String.valueOf(idPlaylist),
                textoSeguro(nombreNuevo),
                Origen.PLAYLIST,
                textoSeguro(nombreAnterior),
                resumen,
                detalle
        );
    }

    public RegistroHistorial registrarEliminacionPlaylist(
            int idPlaylist,
            String nombrePlaylist,
            int cantidadMusicas,
            boolean eliminada,
            String mensaje
    ) {
        Resultado resultado = eliminada ? Resultado.CORRECTO : Resultado.ADVERTENCIA;
        String nombre = textoSeguro(nombrePlaylist);

        String resumen = eliminada
                ? "Se eliminó la playlist \"" + nombre + "\"."
                : "No se pudo eliminar la playlist \"" + nombre + "\".";

        String detalle = "ID playlist: " + idPlaylist + "\n"
                + "Nombre: " + nombre + "\n"
                + "Músicas que tenía: " + cantidadMusicas + "\n"
                + "Eliminada: " + (eliminada ? "Sí" : "No") + "\n"
                + "Mensaje: " + textoSeguro(mensaje);

        return registrar(
                Tipo.PLAYLIST,
                "Eliminar playlist",
                resultado,
                "Playlist",
                String.valueOf(idPlaylist),
                nombre,
                Origen.PLAYLIST,
                nombre,
                resumen,
                detalle
        );
    }

    public RegistroHistorial registrarMusicasAgregadasAPlaylist(
            int idPlaylist,
            String nombrePlaylist,
            List<Musica> musicas,
            int agregadas,
            int duplicadas,
            int invalidas,
            boolean procesada,
            String mensaje
    ) {
        int recibidas = musicas == null ? 0 : musicas.size();
        Resultado resultado = resultadoOperacionConAdvertencias(procesada, duplicadas, invalidas, 0);
        String nombre = textoSeguro(nombrePlaylist);

        String resumen = "Se agregaron " + agregadas + " de " + recibidas
                + " música(s) a la playlist \"" + nombre + "\".";

        StringBuilder detalle = new StringBuilder();

        detalle.append("ID playlist: ").append(idPlaylist).append("\n");
        detalle.append("Nombre playlist: ").append(nombre).append("\n");
        detalle.append("Procesada: ").append(procesada ? "Sí" : "No").append("\n");
        detalle.append("Mensaje: ").append(textoSeguro(mensaje)).append("\n\n");

        detalle.append("Músicas recibidas: ").append(recibidas).append("\n");
        detalle.append("Músicas agregadas: ").append(agregadas).append("\n");
        detalle.append("Músicas duplicadas: ").append(duplicadas).append("\n");
        detalle.append("Músicas inválidas: ").append(invalidas).append("\n\n");

        detalle.append("Listado recibido:\n");
        detalle.append(listaMusicas(musicas));

        return registrar(
                Tipo.PLAYLIST,
                "Agregar músicas a playlist",
                resultado,
                "Playlist",
                String.valueOf(idPlaylist),
                nombre,
                Origen.PLAYLIST,
                nombre,
                resumen,
                detalle.toString()
        );
    }

    public RegistroHistorial registrarMusicasQuitadasDePlaylist(
            int idPlaylist,
            String nombrePlaylist,
            List<Musica> musicas,
            int quitadas,
            int noEncontradas,
            boolean procesada,
            String mensaje
    ) {
        int recibidas = musicas == null ? 0 : musicas.size();
        Resultado resultado = resultadoOperacionConAdvertencias(procesada, 0, noEncontradas, 0);
        String nombre = textoSeguro(nombrePlaylist);

        String resumen = "Se quitaron " + quitadas + " de " + recibidas
                + " música(s) de la playlist \"" + nombre + "\".";

        StringBuilder detalle = new StringBuilder();

        detalle.append("ID playlist: ").append(idPlaylist).append("\n");
        detalle.append("Nombre playlist: ").append(nombre).append("\n");
        detalle.append("Procesada: ").append(procesada ? "Sí" : "No").append("\n");
        detalle.append("Mensaje: ").append(textoSeguro(mensaje)).append("\n\n");

        detalle.append("Músicas recibidas: ").append(recibidas).append("\n");
        detalle.append("Músicas quitadas: ").append(quitadas).append("\n");
        detalle.append("Músicas no encontradas: ").append(noEncontradas).append("\n\n");

        detalle.append("Listado recibido:\n");
        detalle.append(listaMusicas(musicas));

        return registrar(
                Tipo.PLAYLIST,
                "Quitar músicas de playlist",
                resultado,
                "Playlist",
                String.valueOf(idPlaylist),
                nombre,
                Origen.PLAYLIST,
                nombre,
                resumen,
                detalle.toString()
        );
    }

    public RegistroHistorial registrarEliminacionMusica(
            int idMusica,
            Musica musica,
            boolean eliminada,
            long tiempoABB,
            long tiempoAVL,
            int playlistsAfectadas,
            String mensaje
    ) {
        Resultado resultado = eliminada ? Resultado.CORRECTO : Resultado.ADVERTENCIA;
        String nombre = musica == null ? "No encontrada" : textoSeguro(musica.getNombre());

        String resumen = eliminada
                ? "Se eliminó la música \"" + nombre + "\" de la biblioteca."
                : "No se pudo eliminar la música con ID " + idMusica + ".";

        StringBuilder detalle = new StringBuilder();

        detalle.append("ID música solicitado: ").append(idMusica).append("\n\n");

        if (musica != null) {
            detalle.append(detalleMusica(musica)).append("\n\n");
        }

        detalle.append("Eliminada: ").append(eliminada ? "Sí" : "No").append("\n");
        detalle.append("Playlists afectadas: ").append(playlistsAfectadas).append("\n");
        detalle.append("Tiempo de eliminación ABB: ").append(formatearMs(tiempoABB)).append(" ms\n");
        detalle.append("Tiempo de eliminación AVL: ").append(formatearMs(tiempoAVL)).append(" ms\n");
        detalle.append("Mensaje: ").append(textoSeguro(mensaje));

        return registrar(
                Tipo.ELIMINACION,
                "Eliminar música",
                resultado,
                "Música",
                String.valueOf(idMusica),
                nombre,
                Origen.BIBLIOTECA,
                "Biblioteca general",
                resumen,
                detalle.toString()
        );
    }

    public RegistroHistorial registrarBusquedaNombre(
            String nombre,
            List<Musica> encontradas,
            List<Musica> encontradasABB,
            List<Musica> encontradasAVL,
            String estructuraMasRapida,
            long tiempoABB,
            long tiempoAVL
    ) {
        int totalEncontradas = encontradas == null ? 0 : encontradas.size();
        int totalABB = encontradasABB == null ? 0 : encontradasABB.size();
        int totalAVL = encontradasAVL == null ? 0 : encontradasAVL.size();

        return registrarBusqueda(
                nombre,
                "Búsqueda por nombre",
                totalEncontradas,
                totalABB,
                totalAVL,
                estructuraMasRapida,
                tiempoABB,
                tiempoAVL
        );
    }

    public RegistroHistorial registrarBusqueda(
            String textoBuscado,
            String tipoBusqueda,
            int encontradas,
            int encontradasABB,
            int encontradasAVL,
            String estructuraMasRapida,
            long tiempoABB,
            long tiempoAVL
    ) {
        Resultado resultado = encontradas > 0 ? Resultado.CORRECTO : Resultado.INFORMATIVO;
        String texto = textoSeguro(textoBuscado);
        String accion = textoSeguro(tipoBusqueda).isEmpty() ? "Buscar música" : textoSeguro(tipoBusqueda);

        String resumen = encontradas > 0
                ? "Se encontraron " + encontradas + " resultado(s) para \"" + texto + "\"."
                : "No se encontraron resultados para \"" + texto + "\".";

        StringBuilder detalle = new StringBuilder();

        detalle.append("Texto buscado: ").append(texto).append("\n");
        detalle.append("Tipo de búsqueda: ").append(accion).append("\n");
        detalle.append("Total encontrado: ").append(encontradas).append("\n");
        detalle.append("Encontradas en ABB: ").append(encontradasABB).append("\n");
        detalle.append("Encontradas en AVL: ").append(encontradasAVL).append("\n");
        detalle.append("Estructura más rápida: ").append(textoSeguro(estructuraMasRapida)).append("\n\n");

        detalle.append("Tiempo de búsqueda ABB: ").append(formatearMs(tiempoABB)).append(" ms\n");
        detalle.append("Tiempo de búsqueda AVL: ").append(formatearMs(tiempoAVL)).append(" ms");

        return registrar(
                Tipo.BUSQUEDA,
                accion,
                resultado,
                "Búsqueda",
                "",
                texto,
                Origen.BUSQUEDA,
                accion,
                resumen,
                detalle.toString()
        );
    }

    public RegistroHistorial registrarExportacionPlaylist(
            int idPlaylist,
            String nombrePlaylist,
            String rutaArchivo,
            String formato,
            int cantidadCanciones,
            boolean encriptada,
            boolean exportada,
            String mensaje
    ) {
        Resultado resultado = exportada ? Resultado.CORRECTO : Resultado.ERROR;
        String nombre = textoSeguro(nombrePlaylist);

        String resumen = exportada
                ? "Se exportó la playlist \"" + nombre + "\" con " + cantidadCanciones + " música(s)."
                : "No se pudo exportar la playlist \"" + nombre + "\".";

        String detalle = "ID playlist: " + idPlaylist + "\n"
                + "Nombre playlist: " + nombre + "\n"
                + "Ruta archivo: " + textoSeguro(rutaArchivo) + "\n"
                + "Formato: " + textoSeguro(formato) + "\n"
                + "Cantidad de músicas: " + cantidadCanciones + "\n"
                + "Encriptada: " + (encriptada ? "Sí" : "No") + "\n"
                + "Exportada: " + (exportada ? "Sí" : "No") + "\n"
                + "Mensaje: " + textoSeguro(mensaje);

        return registrar(
                Tipo.ARCHIVO,
                "Exportar playlist",
                resultado,
                "Playlist",
                String.valueOf(idPlaylist),
                nombre,
                Origen.ARCHIVO,
                textoSeguro(rutaArchivo),
                resumen,
                detalle
        );
    }

    public RegistroHistorial registrarImportacionPlaylist(
            String rutaArchivo,
            String nombrePlaylist,
            int leidas,
            int agregadas,
            int duplicadas,
            int faltantes,
            boolean encriptada,
            boolean importada,
            String mensaje
    ) {
        Resultado resultado = resultadoOperacionConAdvertencias(importada, duplicadas, faltantes, 0);
        String nombre = textoSeguro(nombrePlaylist);

        String resumen = importada
                ? "Se importó la playlist \"" + nombre + "\" con " + agregadas + " música(s) agregada(s)."
                : "No se pudo importar la playlist desde el archivo indicado.";

        String detalle = "Ruta archivo: " + textoSeguro(rutaArchivo) + "\n"
                + "Nombre playlist: " + nombre + "\n"
                + "Músicas leídas: " + leidas + "\n"
                + "Músicas agregadas: " + agregadas + "\n"
                + "Músicas duplicadas: " + duplicadas + "\n"
                + "Músicas faltantes: " + faltantes + "\n"
                + "Venía encriptada: " + (encriptada ? "Sí" : "No") + "\n"
                + "Importada: " + (importada ? "Sí" : "No") + "\n"
                + "Mensaje: " + textoSeguro(mensaje);

        return registrar(
                Tipo.ARCHIVO,
                "Importar playlist",
                resultado,
                "Playlist",
                "",
                nombre,
                Origen.ARCHIVO,
                textoSeguro(rutaArchivo),
                resumen,
                detalle
        );
    }

    public RegistroHistorial registrarEncriptacionPlaylist(
            int idPlaylist,
            String nombrePlaylist,
            String rutaDestino,
            String algoritmo,
            boolean encriptada,
            String mensaje
    ) {
        Resultado resultado = encriptada ? Resultado.CORRECTO : Resultado.ERROR;
        String nombre = textoSeguro(nombrePlaylist);

        String resumen = encriptada
                ? "Se encriptó la playlist \"" + nombre + "\"."
                : "No se pudo encriptar la playlist \"" + nombre + "\".";

        String detalle = "ID playlist: " + idPlaylist + "\n"
                + "Nombre playlist: " + nombre + "\n"
                + "Ruta destino: " + textoSeguro(rutaDestino) + "\n"
                + "Algoritmo: " + textoSeguro(algoritmo) + "\n"
                + "Encriptada: " + (encriptada ? "Sí" : "No") + "\n"
                + "Mensaje: " + textoSeguro(mensaje) + "\n\n"
                + "Nota: no se guarda ninguna clave o contraseña en el historial.";

        return registrar(
                Tipo.SEGURIDAD,
                "Encriptar playlist",
                resultado,
                "Playlist",
                String.valueOf(idPlaylist),
                nombre,
                Origen.SEGURIDAD,
                textoSeguro(rutaDestino),
                resumen,
                detalle
        );
    }

    public RegistroHistorial registrarDesencriptacionPlaylist(
            String rutaArchivo,
            String nombrePlaylist,
            String algoritmo,
            boolean desencriptada,
            String mensaje
    ) {
        Resultado resultado = desencriptada ? Resultado.CORRECTO : Resultado.ERROR;
        String nombre = textoSeguro(nombrePlaylist);

        String resumen = desencriptada
                ? "Se desencriptó la playlist \"" + nombre + "\"."
                : "No se pudo desencriptar la playlist.";

        String detalle = "Ruta archivo: " + textoSeguro(rutaArchivo) + "\n"
                + "Nombre playlist: " + nombre + "\n"
                + "Algoritmo: " + textoSeguro(algoritmo) + "\n"
                + "Desencriptada: " + (desencriptada ? "Sí" : "No") + "\n"
                + "Mensaje: " + textoSeguro(mensaje) + "\n\n"
                + "Nota: no se guarda ninguna clave o contraseña en el historial.";

        return registrar(
                Tipo.SEGURIDAD,
                "Desencriptar playlist",
                resultado,
                "Playlist",
                "",
                nombre,
                Origen.SEGURIDAD,
                textoSeguro(rutaArchivo),
                resumen,
                detalle
        );
    }

    public List<RegistroHistorial> getRegistros() {
        return Collections.unmodifiableList(new ArrayList<>(registros));
    }

    public List<RegistroHistorial> getRegistrosRecientes() {
        List<RegistroHistorial> copia = new ArrayList<>(registros);
        copia.sort(Comparator.comparing(RegistroHistorial::getFechaHora).reversed());
        return Collections.unmodifiableList(copia);
    }

    public List<RegistroHistorial> getRegistrosPorTipo(Tipo tipo) {
        if (tipo == null) {
            return getRegistros();
        }

        List<RegistroHistorial> filtrados = registros.stream()
                .filter(registro -> registro.getTipo() == tipo)
                .collect(Collectors.toList());

        return Collections.unmodifiableList(filtrados);
    }

    public List<RegistroHistorial> getRegistrosPorResultado(Resultado resultado) {
        if (resultado == null) {
            return getRegistros();
        }

        List<RegistroHistorial> filtrados = registros.stream()
                .filter(registro -> registro.getResultado() == resultado)
                .collect(Collectors.toList());

        return Collections.unmodifiableList(filtrados);
    }

    public List<RegistroHistorial> buscar(String texto) {
        String filtro = textoSeguro(texto).toLowerCase();

        if (filtro.isEmpty()) {
            return getRegistros();
        }

        List<RegistroHistorial> filtrados = registros.stream()
                .filter(registro -> contiene(registro, filtro))
                .collect(Collectors.toList());

        return Collections.unmodifiableList(filtrados);
    }

    public RegistroHistorial getUltimoRegistro() {
        if (registros.isEmpty()) {
            return null;
        }

        return registros.get(registros.size() - 1);
    }

    public boolean estaVacio() {
        return registros.isEmpty();
    }

    public int getTotalRegistros() {
        return registros.size();
    }

    public int contarPorTipo(Tipo tipo) {
        if (tipo == null) {
            return getTotalRegistros();
        }

        int total = 0;

        for (RegistroHistorial registro : registros) {
            if (registro.getTipo() == tipo) {
                total++;
            }
        }

        return total;
    }

    public int contarPorResultado(Resultado resultado) {
        if (resultado == null) {
            return getTotalRegistros();
        }

        int total = 0;

        for (RegistroHistorial registro : registros) {
            if (registro.getResultado() == resultado) {
                total++;
            }
        }

        return total;
    }

    public String getResumenGeneral() {
        StringBuilder sb = new StringBuilder();

        sb.append("Total de registros: ").append(getTotalRegistros()).append("\n");
        sb.append("Cargas: ").append(contarPorTipo(Tipo.CARGA)).append("\n");
        sb.append("Reproducciones: ").append(contarPorTipo(Tipo.REPRODUCCION)).append("\n");
        sb.append("Playlists: ").append(contarPorTipo(Tipo.PLAYLIST)).append("\n");
        sb.append("Eliminaciones: ").append(contarPorTipo(Tipo.ELIMINACION)).append("\n");
        sb.append("Búsquedas: ").append(contarPorTipo(Tipo.BUSQUEDA)).append("\n");
        sb.append("Archivos: ").append(contarPorTipo(Tipo.ARCHIVO)).append("\n");
        sb.append("Seguridad: ").append(contarPorTipo(Tipo.SEGURIDAD)).append("\n\n");

        sb.append("Correctos: ").append(contarPorResultado(Resultado.CORRECTO)).append("\n");
        sb.append("Advertencias: ").append(contarPorResultado(Resultado.ADVERTENCIA)).append("\n");
        sb.append("Errores: ").append(contarPorResultado(Resultado.ERROR)).append("\n");
        sb.append("Informativos: ").append(contarPorResultado(Resultado.INFORMATIVO));

        return sb.toString();
    }

    public void limpiar() {
        registros.clear();
        siguienteId = 1;
    }

    public int limpiarPorTipo(Tipo tipo) {
        if (tipo == null) {
            int total = registros.size();
            limpiar();
            return total;
        }

        int antes = registros.size();
        registros.removeIf(registro -> registro.getTipo() == tipo);
        return antes - registros.size();
    }

    public String getHistorialTexto() {
        return getHistorialTexto(registros);
    }

    public String getHistorialTexto(Tipo tipo) {
        return getHistorialTexto(getRegistrosPorTipo(tipo));
    }

    private String getHistorialTexto(List<RegistroHistorial> lista) {
        if (lista == null || lista.isEmpty()) {
            return "No hay registros en el historial.";
        }

        StringBuilder sb = new StringBuilder();

        for (RegistroHistorial registro : lista) {
            sb.append(registro.getDetalleCompleto()).append("\n\n");
            sb.append("----------------------------------------\n\n");
        }

        return sb.toString();
    }

    private boolean contiene(RegistroHistorial registro, String filtro) {
        return registro.getFechaFormateada().toLowerCase().contains(filtro)
                || registro.getTipo().name().toLowerCase().contains(filtro)
                || registro.getAccion().toLowerCase().contains(filtro)
                || registro.getResultado().name().toLowerCase().contains(filtro)
                || registro.getEntidad().toLowerCase().contains(filtro)
                || registro.getIdEntidad().toLowerCase().contains(filtro)
                || registro.getNombreEntidad().toLowerCase().contains(filtro)
                || registro.getOrigen().name().toLowerCase().contains(filtro)
                || registro.getNombreOrigen().toLowerCase().contains(filtro)
                || registro.getResumen().toLowerCase().contains(filtro)
                || registro.getDetalle().toLowerCase().contains(filtro);
    }

    private Resultado resultadoCarga(
            int recibidas,
            int insertadas,
            int duplicadas,
            int invalidas,
            int fallidas
    ) {
        if (recibidas <= 0) {
            return Resultado.INFORMATIVO;
        }

        if (insertadas == 0 && (invalidas > 0 || fallidas > 0)) {
            return Resultado.ERROR;
        }

        if (duplicadas > 0 || invalidas > 0 || fallidas > 0) {
            return Resultado.ADVERTENCIA;
        }

        return Resultado.CORRECTO;
    }

    private Resultado resultadoOperacionConAdvertencias(
            boolean procesada,
            int duplicadas,
            int invalidas,
            int fallidas
    ) {
        if (!procesada) {
            return Resultado.ERROR;
        }

        if (duplicadas > 0 || invalidas > 0 || fallidas > 0) {
            return Resultado.ADVERTENCIA;
        }

        return Resultado.CORRECTO;
    }

    private String resumenCarga(
            int recibidas,
            int insertadas,
            int duplicadas,
            int invalidas,
            int fallidas
    ) {
        if (recibidas <= 0) {
            return "No se recibieron músicas para cargar.";
        }

        if (insertadas == recibidas && duplicadas == 0 && invalidas == 0 && fallidas == 0) {
            return "Se cargaron " + insertadas + " música(s) correctamente.";
        }

        return "Se insertaron " + insertadas + " de " + recibidas + " música(s). "
                + "Duplicadas: " + duplicadas
                + ", inválidas: " + invalidas
                + ", fallidas: " + fallidas + ".";
    }

    private String detalleMusica(Musica musica) {
        if (musica == null) {
            return "Música: no encontrada";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("ID música: ").append(musica.getId()).append("\n");
        sb.append("Nombre: ").append(textoSeguro(musica.getNombre())).append("\n");
        sb.append("Artista: ").append(textoSeguro(musica.getArtista())).append("\n");
        sb.append("Álbum: ").append(textoSeguro(musica.getAlbum())).append("\n");
        sb.append("Género: ").append(textoSeguro(musica.getGenero())).append("\n");
        sb.append("Duración: ").append(musica.formatearDuracion()).append("\n");
        sb.append("Año: ").append(musica.anioReal()).append("\n");
        sb.append("Ruta: ").append(textoSeguro(musica.getRuta()));

        return sb.toString();
    }

    private String listaMusicas(List<Musica> musicas) {
        if (musicas == null || musicas.isEmpty()) {
            return "No se recibieron músicas.\n";
        }

        StringBuilder sb = new StringBuilder();

        for (Musica musica : musicas) {
            if (musica == null) {
                sb.append("- Música inválida\n");
            } else {
                sb.append("- ID: ").append(musica.getId())
                        .append(" | ").append(textoSeguro(musica.getNombre()))
                        .append(" - ").append(textoSeguro(musica.getArtista()))
                        .append("\n");
            }
        }

        return sb.toString();
    }

    private String nombreLegibleOrigen(Origen origen) {
        if (origen == null) {
            return "Origen desconocido";
        }

        switch (origen) {
            case BIBLIOTECA:
                return "Biblioteca general";
            case PLAYLIST:
                return "Playlist";
            case COLA:
                return "Cola de reproducción";
            case BUSQUEDA:
                return "Resultados de búsqueda";
            case RECORRIDO_ARBOL:
                return "Recorrido de árbol";
            case ARCHIVO:
                return "Archivo";
            case SEGURIDAD:
                return "Seguridad";
            default:
                return "Origen desconocido";
        }
    }

    private String formatearMs(long nanosegundos) {
        return String.format("%.4f", nanosegundos / 1_000_000.0);
    }

    private String textoSeguro(String texto) {
        return texto == null ? "" : texto.trim();
    }
}