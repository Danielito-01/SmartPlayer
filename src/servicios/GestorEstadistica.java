package servicios;

import estructuras.BibliotecaGeneral;
import estructuras.ListaMusicas;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelos.Musica;
import modelos.Playlist;

public class GestorEstadistica {

    private final BibliotecaGeneral bibliotecaGeneral;

    public GestorEstadistica() {
        bibliotecaGeneral = BibliotecaGeneral.getInstance();
    }

    public Musica getCancionMasReproducida() {
        List<Musica> musicas = getMusicasBiblioteca();

        if (musicas.isEmpty()) {
            return null;
        }

        Musica mayor = musicas.get(0);

        for (Musica musica : musicas) {
            if (musica.getReproducciones() > mayor.getReproducciones()) {
                mayor = musica;
            }
        }

        return mayor;
    }

    public ResultadoTextoNumero getArtistaMasEscuchado() {
        List<Musica> musicas = getMusicasBiblioteca();

        if (musicas.isEmpty()) {
            return null;
        }

        Map<String, Integer> reproduccionesPorArtista = new HashMap<>();

        for (Musica musica : musicas) {
            String artista = musica.getArtista();
            int reproducciones = musica.getReproducciones();

            reproduccionesPorArtista.put(
                    artista,
                    reproduccionesPorArtista.getOrDefault(artista, 0) + reproducciones
            );
        }

        return getMayorValor(reproduccionesPorArtista);
    }

    public ResultadoTextoNumero getGeneroMasFrecuente() {
        List<Musica> musicas = getMusicasBiblioteca();

        if (musicas.isEmpty()) {
            return null;
        }

        Map<String, Integer> cancionesPorGenero = new HashMap<>();

        for (Musica musica : musicas) {
            String genero = musica.getGenero();

            cancionesPorGenero.put(
                    genero,
                    cancionesPorGenero.getOrDefault(genero, 0) + 1
            );
        }

        return getMayorValor(cancionesPorGenero);
    }

    public ResultadoPlaylist getPlaylistMasGrande() {
        List<Playlist> playlists = bibliotecaGeneral.getPlaylists();

        if (playlists.isEmpty()) {
            return null;
        }

        Playlist mayor = playlists.get(0);

        for (Playlist playlist : playlists) {
            if (playlist.getCantidad() > mayor.getCantidad()) {
                mayor = playlist;
            }
        }

        return crearResultadoPlaylist(mayor);
    }

    public ResultadoPlaylist getPlaylistMasLarga() {
        List<Playlist> playlists = bibliotecaGeneral.getPlaylists();

        if (playlists.isEmpty()) {
            return null;
        }

        Playlist mayor = playlists.get(0);

        for (Playlist playlist : playlists) {
            int duracionActual = playlist.getPlaylist().getDuracionTotal();
            int duracionMayor = mayor.getPlaylist().getDuracionTotal();

            if (duracionActual > duracionMayor) {
                mayor = playlist;
            }
        }

        return crearResultadoPlaylist(mayor);
    }

    public ResultadoPlaylist getPlaylistMasPesada() {
        List<Playlist> playlists = bibliotecaGeneral.getPlaylists();

        if (playlists.isEmpty()) {
            return null;
        }

        Playlist mayor = playlists.get(0);

        for (Playlist playlist : playlists) {
            long tamanioActual = playlist.getPlaylist().getTamanioTotal();
            long tamanioMayor = mayor.getPlaylist().getTamanioTotal();

            if (tamanioActual > tamanioMayor) {
                mayor = playlist;
            }
        }

        return crearResultadoPlaylist(mayor);
    }

    public int getTotalCanciones() {
        return getListaBiblioteca().getCantidad();
    }

    public int getDuracionTotalBiblioteca() {
        return getListaBiblioteca().getDuracionTotal();
    }

    public String getDuracionTotalBibliotecaFormateada() {
        return getListaBiblioteca().getDuracionTotalFormateada();
    }

    public long getTamanioTotalBiblioteca() {
        return getListaBiblioteca().getTamanioTotal();
    }

    public String getTamanioTotalBibliotecaFormateado() {
        return getListaBiblioteca().getTamanioTotalFormateado();
    }

    public double getPromedioDuracionBiblioteca() {
        return getListaBiblioteca().getPromedioDuracion();
    }

    public String getPromedioDuracionBibliotecaFormateado() {
        return getListaBiblioteca().getPromedioDuracionFormateado();
    }

    public ResumenBiblioteca getResumenBiblioteca() {
        ListaMusicas biblioteca = getListaBiblioteca();

        return new ResumenBiblioteca(
                biblioteca.getCantidad(),
                biblioteca.getDuracionTotal(),
                biblioteca.getDuracionTotalFormateada(),
                biblioteca.getTamanioTotal(),
                biblioteca.getTamanioTotalFormateado(),
                biblioteca.getPromedioDuracion(),
                biblioteca.getPromedioDuracionFormateado()
        );
    }

    private ResultadoPlaylist crearResultadoPlaylist(Playlist playlist) {
        ListaMusicas lista = playlist.getPlaylist();

        return new ResultadoPlaylist(
                playlist,
                playlist.getNombre(),
                lista.getCantidad(),
                lista.getDuracionTotal(),
                lista.getDuracionTotalFormateada(),
                lista.getTamanioTotal(),
                lista.getTamanioTotalFormateado(),
                lista.getPromedioDuracion(),
                lista.getPromedioDuracionFormateado()
        );
    }

    private ResultadoTextoNumero getMayorValor(Map<String, Integer> mapa) {
        String textoMayor = null;
        int numeroMayor = 0;

        for (Map.Entry<String, Integer> entrada : mapa.entrySet()) {
            if (textoMayor == null || entrada.getValue() > numeroMayor) {
                textoMayor = entrada.getKey();
                numeroMayor = entrada.getValue();
            }
        }

        return new ResultadoTextoNumero(textoMayor, numeroMayor);
    }

    private ListaMusicas getListaBiblioteca() {
        return bibliotecaGeneral.getBiblioteca();
    }

    private List<Musica> getMusicasBiblioteca() {
        return bibliotecaGeneral.getBiblioteca().listaMusicas();
    }

    public static class ResultadoTextoNumero {

        private final String texto;
        private final int numero;

        public ResultadoTextoNumero(String texto, int numero) {
            this.texto = texto;
            this.numero = numero;
        }

        public String getTexto() {
            return texto;
        }

        public int getNumero() {
            return numero;
        }
    }

    public static class ResultadoPlaylist {

        private final Playlist playlist;
        private final String nombre;
        private final int cantidadCanciones;
        private final int duracionTotal;
        private final String duracionTotalFormateada;
        private final long tamanioTotal;
        private final String tamanioTotalFormateado;
        private final double promedioDuracion;
        private final String promedioDuracionFormateado;

        public ResultadoPlaylist(
                Playlist playlist,
                String nombre,
                int cantidadCanciones,
                int duracionTotal,
                String duracionTotalFormateada,
                long tamanioTotal,
                String tamanioTotalFormateado,
                double promedioDuracion,
                String promedioDuracionFormateado
        ) {
            this.playlist = playlist;
            this.nombre = nombre;
            this.cantidadCanciones = cantidadCanciones;
            this.duracionTotal = duracionTotal;
            this.duracionTotalFormateada = duracionTotalFormateada;
            this.tamanioTotal = tamanioTotal;
            this.tamanioTotalFormateado = tamanioTotalFormateado;
            this.promedioDuracion = promedioDuracion;
            this.promedioDuracionFormateado = promedioDuracionFormateado;
        }

        public Playlist getPlaylist() {
            return playlist;
        }

        public String getNombre() {
            return nombre;
        }

        public int getCantidadCanciones() {
            return cantidadCanciones;
        }

        public int getDuracionTotal() {
            return duracionTotal;
        }

        public String getDuracionTotalFormateada() {
            return duracionTotalFormateada;
        }

        public long getTamanioTotal() {
            return tamanioTotal;
        }

        public String getTamanioTotalFormateado() {
            return tamanioTotalFormateado;
        }

        public double getPromedioDuracion() {
            return promedioDuracion;
        }

        public String getPromedioDuracionFormateado() {
            return promedioDuracionFormateado;
        }
    }

    public static class ResumenBiblioteca {

        private final int totalCanciones;
        private final int duracionTotal;
        private final String duracionTotalFormateada;
        private final long tamanioTotal;
        private final String tamanioTotalFormateado;
        private final double promedioDuracion;
        private final String promedioDuracionFormateado;

        public ResumenBiblioteca(
                int totalCanciones,
                int duracionTotal,
                String duracionTotalFormateada,
                long tamanioTotal,
                String tamanioTotalFormateado,
                double promedioDuracion,
                String promedioDuracionFormateado
        ) {
            this.totalCanciones = totalCanciones;
            this.duracionTotal = duracionTotal;
            this.duracionTotalFormateada = duracionTotalFormateada;
            this.tamanioTotal = tamanioTotal;
            this.tamanioTotalFormateado = tamanioTotalFormateado;
            this.promedioDuracion = promedioDuracion;
            this.promedioDuracionFormateado = promedioDuracionFormateado;
        }

        public int getTotalCanciones() {
            return totalCanciones;
        }

        public int getDuracionTotal() {
            return duracionTotal;
        }

        public String getDuracionTotalFormateada() {
            return duracionTotalFormateada;
        }

        public long getTamanioTotal() {
            return tamanioTotal;
        }

        public String getTamanioTotalFormateado() {
            return tamanioTotalFormateado;
        }

        public double getPromedioDuracion() {
            return promedioDuracion;
        }

        public String getPromedioDuracionFormateado() {
            return promedioDuracionFormateado;
        }
    }
}