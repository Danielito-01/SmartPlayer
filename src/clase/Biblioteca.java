package clase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Biblioteca {
    private static final Biblioteca INSTANCE = new Biblioteca();
    
    private int nextId = 1;
    private final Lista biblioteca = new Lista();
    private final Set<String> rutas = new HashSet<>();

    private int nextPlaylistId = 1;
    private final List<Playlist> playlists = new ArrayList<>();
    private final Set<String> nombresPlaylists = new HashSet<>();

    private Biblioteca() {}

    public static Biblioteca getInstance() {
        return INSTANCE;
    }

    public Lista getBiblioteca() {
        return biblioteca;
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
            if (musica == null || musica.getRuta() == null || musica.getRuta().isBlank()) continue;
            String key = normalizarRuta(musica.getRuta());
            if (!rutas.add(key)) continue; // ya existe en biblioteca general
            if (musica.getId() <= 0) {
                musica.setId(nextId++);
            }
            biblioteca.agregarMusica(musica);
            agregadas++;
        }
        return agregadas;
    } 

    public Playlist crearPlaylist(String nombre) {
        String key = normalizarNombrePlaylist(nombre);
        if (key.isBlank()) return null;
        if (!nombresPlaylists.add(key)) {
            return null; // nombre duplicado
        }
        Playlist p = new Playlist(nextPlaylistId++, nombre);
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

        String nuevoKey = normalizarNombrePlaylist(nuevoNombre);
        if (nuevoKey.isBlank()) return false;
        String actualKey = normalizarNombrePlaylist(playlist.getNombre());
        if (actualKey.equals(nuevoKey)) return true; 
        if (nombresPlaylists.contains(nuevoKey)) return false; 
        nombresPlaylists.remove(actualKey);
        nombresPlaylists.add(nuevoKey);
        playlist.setNombre(nuevoNombre);
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