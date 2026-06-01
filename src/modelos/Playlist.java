package modelos;

import estructuras.ListaMusicas;
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

    public int getCantidad() {
        return playlist.getCantidad();
    }

    public final void setNombre(String nombre) {
        this.nombre = (nombre == null || nombre.isBlank())
                ? "Sin nombre"
                : nombre.trim();
    }

    public boolean agregarMusica(Musica musica) {
        if (musica == null) {
            return false;
        }

        if (tieneMusica(musica)) {
            return false;
        }

        playlist.agregarMusica(musica);
        return true;
    }

    public boolean eliminarMusica(Musica musica) {
        return playlist.eliminarMusica(musica);
    }

    public boolean tieneMusica(Musica musica) {
        return playlist.tieneMusica(musica);
    }

    public List<Musica> toListAdelante() {
        return playlist.toListAdelante();
    }

    @Override
    public String toString() {
        return nombre;
    }
}