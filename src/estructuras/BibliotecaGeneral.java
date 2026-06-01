package estructuras;

import modelos.Playlist;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelos.Musica;

public final class BibliotecaGeneral {
    private static final BibliotecaGeneral INSTANCE = new BibliotecaGeneral();
    
    private final ArbolABB abb = new ArbolABB();
    private final ArbolAVL avl = new ArbolAVL();
    private final HashMusicas hash = new HashMusicas();

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

    public HashMusicas getHash() {
        return hash;
    }
    
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    // AGREGA EN LISTA + ABB + AVL
    public int agregarMusicas(List<Musica> nuevas) {
        if (nuevas == null) return 0;

        int agregadas = 0;

        for (Musica musica : nuevas) {
            if (musica == null || musica.getRuta() == null || musica.getRuta().isBlank()) {
                continue;
            }

            String key = normalizarRuta(musica.getRuta());

            if (!rutas.add(key)) {
                continue;
            }

            if (musica.getId() <= 0) {
                musica.setId(idMusica++);
            } else if (musica.getId() >= idMusica) {
                idMusica = musica.getId() + 1;
            }

            biblioteca.agregarMusica(musica);
            abb.insertar(musica);
            avl.insertar(musica);
            hash.insertar(musica);

            agregadas++;
        }

        return agregadas;
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