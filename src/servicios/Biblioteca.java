package servicios;

import estructuras.ListaMusicas;
import modelos.Playlist;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelos.Musica;

public final class Biblioteca {
    private static final Biblioteca INSTANCE = new Biblioteca();
    
    private int nextId = 1;
    private final ListaMusicas biblioteca = new ListaMusicas();
    private final Set<String> rutas = new HashSet<>();
    
    private final GestorBusqueda gestorBusqueda = new GestorBusqueda();

    private int nextPlaylistId = 1;
    private final List<Playlist> playlists = new ArrayList<>();
    private final Set<String> nombresPlaylists = new HashSet<>();

    private Biblioteca() {}

    public static Biblioteca getInstance() {
        return INSTANCE;
    }

    public ListaMusicas getBiblioteca() {
        return biblioteca;
    }
    
    public GestorBusqueda getGestorBusqueda() {
        return gestorBusqueda;
    }
    
    public List<Playlist> getPlaylists() {
        return playlists;
    }
    
    public String getNombre() {
        return "Biblioteca";
    }
 
    public int agregarSinRepetir(List<Musica> nuevas) {
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
                musica.setId(nextId++);
            } else if (musica.getId() >= nextId) {
                nextId = musica.getId() + 1;
            }
            biblioteca.agregarMusica(musica);
            gestorBusqueda.insertar(musica);
            agregadas++;
        }
        return agregadas;
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
        Playlist p = new Playlist(nextPlaylistId++, nombreLimpio);
        playlists.add(p);
        return p;
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

    public boolean renombrarPlaylist(Playlist playlist, String nuevoNombre) {
        if (playlist == null) return false;

        String nombreLimpio = nuevoNombre == null ? "" : nuevoNombre.trim();
        String nuevoKey = normalizarNombrePlaylist(nombreLimpio);
       
        if (nuevoKey.isBlank()) return false;

        String actualKey = normalizarNombrePlaylist(playlist.getNombre());

        if (actualKey.equals(nuevoKey)) return true;

        if (nombresPlaylists.contains(nuevoKey)) return false;

        nombresPlaylists.remove(actualKey);
        nombresPlaylists.add(nuevoKey);
        playlist.setNombre(nombreLimpio);
        return true;
    }

    private String normalizarRuta(String ruta) {
        return ruta.trim().toLowerCase();
    }

    private String normalizarNombrePlaylist(String nombre) {
        if (nombre == null) return "";
        return nombre.trim().toLowerCase();
    }
}