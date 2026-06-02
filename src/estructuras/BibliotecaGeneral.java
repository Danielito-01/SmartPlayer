package estructuras;

import modelos.Playlist;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelos.Musica;
import modelos.ReporteCargaMusicas;

public final class BibliotecaGeneral {
    private static final BibliotecaGeneral INSTANCE = new BibliotecaGeneral();
    
    private final ArbolABB abb = new ArbolABB();
    private final ArbolAVL avl = new ArbolAVL();
    private final TablaHashMusicas hash = new TablaHashMusicas();

    private int idMusica = 1;
    private final ListaMusicas biblioteca = new ListaMusicas();
    private final Set<String> rutas = new HashSet<>();

    private int idPlaylist = 1;
    private final List<Playlist> playlists = new ArrayList<>();
    private final Set<String> nombresPlaylists = new HashSet<>();

    private BibliotecaGeneral() {}

    public static BibliotecaGeneral getInstance() {
        return INSTANCE;
    }

    public ListaMusicas getBiblioteca() {
        return biblioteca;
    }

    public ArbolABB getAbb() {
        return abb;
    }

    public ArbolAVL getAvl() {
        return avl;
    }

    public TablaHashMusicas getHash() {
        return hash;
    }
    
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    // AGREGA EN LISTA + ABB + AVL + HASH
    public int agregarMusicas(List<Musica> nuevas) {
        return agregarMusicasConReporte(nuevas).getIngresadasBiblioteca();
    }

    public ReporteCargaMusicas agregarMusicasConReporte(List<Musica> nuevas) {
        ReporteCargaMusicas reporte = new ReporteCargaMusicas();

        long inicioTotal = System.nanoTime();
        if (nuevas == null) {
            reporte.setTotalBiblioteca(biblioteca.getCantidad());
            reporte.setTotalABB(abb.getCantidad());
            reporte.setTotalAVL(avl.getCantidad());
            reporte.setTiempoTotalNs(System.nanoTime() - inicioTotal);
            return reporte;
        }
        reporte.setRecibidas(nuevas.size());

        for (Musica musica : nuevas) {
            if (musica == null || musica.getRuta() == null || musica.getRuta().isBlank()) {
                reporte.incrementarOmitidasInvalidas();
                continue;
            }

            String key = normalizarRuta(musica.getRuta());
            if (!rutas.add(key)) {
                reporte.incrementarOmitidasDuplicadas();
                continue;
            }

            if (musica.getId() <= 0) {
                musica.setId(idMusica++);
            } else if (musica.getId() >= idMusica) {
                idMusica = musica.getId() + 1;
            }

            long inicioBiblioteca = System.nanoTime();
            biblioteca.agregarMusica(musica);
            reporte.sumarTiempoBibliotecaNs(System.nanoTime() - inicioBiblioteca);
            reporte.incrementarIngresadasBiblioteca();
            long inicioABB = System.nanoTime();
            boolean ingresoABB = abb.insertar(musica);
            reporte.sumarTiempoABBNs(System.nanoTime() - inicioABB);
            if (ingresoABB) {
                reporte.incrementarIngresadasABB();
            }
            long inicioAVL = System.nanoTime();
            boolean ingresoAVL = avl.insertar(musica);
            reporte.sumarTiempoAVLNs(System.nanoTime() - inicioAVL);
            if (ingresoAVL) {
                reporte.incrementarIngresadasAVL();
            }

            hash.insertar(musica);
        }
        reporte.setTotalBiblioteca(biblioteca.getCantidad());
        reporte.setTotalABB(abb.getCantidad());
        reporte.setTotalAVL(avl.getCantidad());
        reporte.setTiempoTotalNs(System.nanoTime() - inicioTotal);
        return reporte;
    }

    // ELIMINA DE LISTA + ABB + AVL + PLAYLISTS
    public boolean eliminarMusica(Musica musica) {
        if (musica == null) return false;

        boolean eliminadaLista = biblioteca.eliminarMusica(musica);
        boolean eliminadaAbb = abb.eliminar(musica);
        boolean eliminadaAvl = avl.eliminar(musica);
        boolean eliminadaHash = hash.buscarPorId(musica.getId()) != null;

        hash.eliminar(musica);

        for (Playlist playlist : playlists) {
            playlist.eliminarMusica(musica);
        }

        if (musica.getRuta() != null) {
            rutas.remove(normalizarRuta(musica.getRuta()));
        }

        return eliminadaLista || eliminadaAbb || eliminadaAvl || eliminadaHash;
    }

    // MODIFICA LA MISMA MÚSICA EN TODAS LAS ESTRUCTURAS
    public boolean modificarMusica(Musica musica, Musica datosNuevos) {
        if (musica == null || datosNuevos == null) return false;

        int idOriginal = musica.getId();
        String nombreAnterior = musica.getNombre();
        String rutaAnterior = musica.getRuta();

        String rutaNueva = datosNuevos.getRuta();

        if (rutaNueva == null || rutaNueva.isBlank()) {
            rutaNueva = rutaAnterior;
        }

        String keyAnterior = rutaAnterior == null ? "" : normalizarRuta(rutaAnterior);
        String keyNueva = rutaNueva == null ? "" : normalizarRuta(rutaNueva);

        if (!keyNueva.isBlank()
                && !keyNueva.equals(keyAnterior)
                && rutas.contains(keyNueva)) {
            return false;
        }

        // Sacar de las estructuras que dependen de claves
        abb.eliminarPorClave(nombreAnterior, idOriginal);
        avl.eliminarPorClave(nombreAnterior, idOriginal);
        hash.eliminar(musica);

        if (!keyAnterior.isBlank()) {
            rutas.remove(keyAnterior);
        }

        // Modificar el mismo objeto Musica
        musica.setNombre(datosNuevos.getNombre());
        musica.setArtista(datosNuevos.getArtista());
        musica.setAlbum(datosNuevos.getAlbum());
        musica.setGenero(datosNuevos.getGenero());
        musica.setDuracion(datosNuevos.getDuracion());
        musica.setTamanio(datosNuevos.getTamanio());
        musica.setRuta(rutaNueva);
        musica.setAnio(datosNuevos.getAnio());
        musica.setPortada(datosNuevos.getPortada());
        musica.setReproducciones(datosNuevos.getReproducciones());

        // El ID se conserva
        musica.setId(idOriginal);

        if (!keyNueva.isBlank()) {
            rutas.add(keyNueva);
        }

        // Volver a insertar con los datos nuevos
        abb.insertar(musica);
        avl.insertar(musica);
        hash.insertar(musica);

        return true;
    }

    public Playlist crearPlaylist(String nombre) {
        String nombreLimpio = nombre == null ? "" : nombre.trim();
        String key = normalizarNombrePlaylist(nombreLimpio);

        if (key.isBlank()) {
            return null;
        }

        if (!nombresPlaylists.add(key)) {
            return null;
        }

        Playlist playlist = new Playlist(idPlaylist++, nombreLimpio);
        playlists.add(playlist);

        return playlist;
    }

    public boolean existePlaylist(String nombre) {
        return nombresPlaylists.contains(normalizarNombrePlaylist(nombre));
    }

    public Playlist buscarPlaylistPorId(int id) {
        for (Playlist playlist : playlists) {
            if (playlist.getId() == id) {
                return playlist;
            }
        }

        return null;
    }

    private String normalizarRuta(String ruta) {
        return ruta == null ? "" : ruta.trim().toLowerCase();
    }

    private String normalizarNombrePlaylist(String nombre) {
        return nombre == null ? "" : nombre.trim().toLowerCase();
    }
}