package servicios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import modelos.Musica;
import modelos.Playlist;

public class GestorHistorial {

    private final List<String> cargas = new ArrayList<>();
    private final List<String> busquedas = new ArrayList<>();
    private final List<String> eliminaciones = new ArrayList<>();
    private final List<String> playlists = new ArrayList<>();

    public String registrarCarga(
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
        String reporte =
                "Carga guardada\n\n"
                + "Fecha: " + fechaActual() + "\n\n"
                + "Recibidas: " + recibidas + "\n"
                + "Insertadas: " + insertadas + "\n"
                + "Duplicadas: " + duplicadas + "\n"
                + "Inválidas: " + invalidas + "\n"
                + "Fallidas: " + fallidas + "\n\n"
                + "Total biblioteca: " + totalBiblioteca + "\n"
                + "Total ABB: " + totalABB + "\n"
                + "Total AVL: " + totalAVL + "\n"
                + "Total Hash: " + totalHash + "\n\n"
                + "Tiempo ABB inserción: " + convertirMs(tiempoABB) + " ms\n"
                + "Tiempo AVL inserción: " + convertirMs(tiempoAVL) + " ms\n"
                + "Tiempo total: " + convertirMs(tiempoTotal) + " ms";

        cargas.add(reporte);
        return reporte;
    }

    public String registrarBusquedaNombre(String nombre, List<Musica> encontradas,List<Musica> encontradasABB,
            List<Musica> encontradasAVL, String estructuraMasRapida, long tiempoABB, long tiempoAVL) {
        int totalEncontradas = encontradas == null ? 0 : encontradas.size();

        String resultado = totalEncontradas == 0
                ? "No encontrada"
                : totalEncontradas + " resultado(s) encontrado(s)";

        String reporte =
                "Búsqueda por nombre\n\n"
                + "Fecha: " + fechaActual() + "\n"
                + "Nombre buscado: " + textoSeguro(nombre) + "\n"
                + "Resultado: " + resultado + "\n"
                + "Encontradas en ABB: " + (encontradasABB == null ? 0 : encontradasABB.size()) + "\n"
                + "Encontradas en AVL: " + (encontradasAVL == null ? 0 : encontradasAVL.size()) + "\n"
                + "Más rápido: " + estructuraMasRapida + "\n\n"
                + "Tiempo ABB búsqueda: " + convertirMs(tiempoABB) + " ms\n"
                + "Tiempo AVL búsqueda: " + convertirMs(tiempoAVL) + " ms";

        busquedas.add(reporte);
        return reporte;
    }

    public String registrarEliminacion(
            int id,
            Musica musica,
            boolean eliminada,
            long tiempoABB,
            long tiempoAVL
    ) {
        String nombre = musica == null ? "No encontrada" : musica.getNombre();

        String reporte =
                "Eliminación de música\n\n"
                + "Fecha: " + fechaActual() + "\n"
                + "ID: " + id + "\n"
                + "Nombre: " + nombre + "\n"
                + "Eliminada: " + (eliminada ? "Sí" : "No") + "\n\n"
                + "Tiempo ABB eliminación: " + convertirMs(tiempoABB) + " ms\n"
                + "Tiempo AVL eliminación: " + convertirMs(tiempoAVL) + " ms";

        eliminaciones.add(reporte);
        return reporte;
    }
    
    public String registrarCreacionPlaylist(
            Playlist playlist,
            boolean creada,
            String mensaje
    ) {
        String nombre = playlist == null ? "No creada" : playlist.getNombre();
        int id = playlist == null ? 0 : playlist.getId();

        String reporte =
                "Creación de playlist\n\n"
                + "Fecha: " + fechaActual() + "\n"
                + "ID: " + id + "\n"
                + "Nombre: " + nombre + "\n"
                + "Creada: " + (creada ? "Sí" : "No") + "\n"
                + "Mensaje: " + textoSeguro(mensaje);

        playlists.add(reporte);
        return reporte;
    }
    
    public String registrarMusicasAgregadasAPlaylist(
            Playlist playlist,
            List<Musica> musicas,
            int agregadas,
            int duplicadas,
            int invalidas,
            boolean procesada,
            String mensaje
    ) {
        String nombrePlaylist = playlist == null ? "No encontrada" : playlist.getNombre();
        int idPlaylist = playlist == null ? 0 : playlist.getId();
        int recibidas = musicas == null ? 0 : musicas.size();

        StringBuilder detalleMusicas = new StringBuilder();

        if (musicas != null && !musicas.isEmpty()) {
            for (Musica musica : musicas) {
                if (musica != null) {
                    detalleMusicas.append("- ")
                            .append(musica.getNombre())
                            .append(" - ")
                            .append(musica.getArtista())
                            .append(" | ID: ")
                            .append(musica.getId())
                            .append("\n");
                }
            }
        } else {
            detalleMusicas.append("No se recibieron músicas.\n");
        }

        String reporte =
                "Agregar músicas a playlist\n\n"
                + "Fecha: " + fechaActual() + "\n"
                + "Playlist: " + nombrePlaylist + "\n"
                + "ID Playlist: " + idPlaylist + "\n"
                + "Procesada: " + (procesada ? "Sí" : "No") + "\n"
                + "Mensaje: " + textoSeguro(mensaje) + "\n\n"
                + "Recibidas: " + recibidas + "\n"
                + "Agregadas: " + agregadas + "\n"
                + "Duplicadas: " + duplicadas + "\n"
                + "Inválidas: " + invalidas + "\n\n"
                + "Músicas recibidas:\n"
                + detalleMusicas;

        playlists.add(reporte);
        return reporte;
    }

    public List<String> getCargas() {
        return new ArrayList<>(cargas);
    }

    public List<String> getBusquedas() {
        return new ArrayList<>(busquedas);
    }

    public List<String> getEliminaciones() {
        return new ArrayList<>(eliminaciones);
    }

    public String getUltimaCarga() {
        if (cargas.isEmpty()) return "No hay cargas registradas.";

        return cargas.get(cargas.size() - 1);
    }

    public String getUltimaBusqueda() {
        if (busquedas.isEmpty()) return "No hay búsquedas registradas.";

        return busquedas.get(busquedas.size() - 1);
    }

    public String getUltimaEliminacion() {
        if (eliminaciones.isEmpty()) return "No hay eliminaciones registradas.";

        return eliminaciones.get(eliminaciones.size() - 1);
    }

    public String getHistorialCargas() {
        return unirReportes(cargas, "No hay cargas registradas.");
    }

    public String getHistorialBusquedas() {
        return unirReportes(busquedas, "No hay búsquedas registradas.");
    }

    public String getHistorialEliminaciones() {
        return unirReportes(eliminaciones, "No hay eliminaciones registradas.");
    }
    
    public List<String> getPlaylists() {
        return new ArrayList<>(playlists);
    }

    public String getUltimaAccionPlaylist() {
        if (playlists.isEmpty()) {
            return "No hay acciones de playlist registradas.";
        }

        return playlists.get(playlists.size() - 1);
    }

    public String getHistorialPlaylists() {
        return unirReportes(playlists, "No hay acciones de playlist registradas.");
    }

    public void limpiar() {
        cargas.clear();
        busquedas.clear();
        eliminaciones.clear();
        playlists.clear();
    }

    private String unirReportes(List<String> reportes, String mensajeVacio) {
        if (reportes.isEmpty()) return mensajeVacio;

        StringBuilder historial = new StringBuilder();

        for (int i = 0; i < reportes.size(); i++) {
            historial.append("Registro ")
                    .append(i + 1)
                    .append("\n")
                    .append(reportes.get(i))
                    .append("\n\n");
        }

        return historial.toString();
    }

    private String fechaActual() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formato);
    }

    private double convertirMs(long nanosegundos) {
        return nanosegundos / 1_000_000.0;
    }

    private String textoSeguro(String texto) {
        if (texto == null) return "";

        return texto.trim();
    }
}