package clase;

import java.util.List;

public class Playlist {
    private final int id;
    private String nombre;
    private final ListaMusicas playlist = new ListaMusicas();

    public Playlist(int id, String nombre) {
        this.id = id;
        setNombre(nombre);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public ListaMusicas getPlaylist() {
        return playlist;
    }

    public final void setNombre(String nombre) {
        this.nombre = (nombre == null || nombre.isBlank())
                ? "Sin nombre"
                : nombre.trim();
    }

    public int size() {
        return playlist.size();
    }

   
    public boolean agregarSiNoExiste(Musica musica) {
        if (musica == null) {
            return false;
        }
        if (playlist.tieneMusica(musica)) {
            return false;
        }
        playlist.agregarMusica(musica);
        return true;
    }

    public List<Musica> toList() {
        return playlist.toListAdelante();
    }

    @Override
    public String toString() {
        return nombre;
    }
}