package modelos;

import estructuras.ListaMusicas;
import java.util.List;

public class Playlist {

    private final int id;
    private String nombre;
    private final ListaMusicas playlist;

    public Playlist(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.playlist = new ListaMusicas();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        }
    }

    public ListaMusicas getPlaylist() {
        return playlist;
    }

    public int getCantidad() {
        return playlist.getCantidad();
    }

    public boolean estaVacia() {
        return playlist.estaVacia();
    }

    public List<Musica> getMusicas() {
        return playlist.listaMusicas();
    }

    public boolean contieneMusica(Musica musica) {
        if (musica == null) {
            return false;
        }
        return contieneId(musica.getId());
    }

    private boolean contieneId(int idMusica) {
        if (idMusica <= 0) {
            return false;
        }
        for (Musica musica : playlist.listaMusicas()) {
            if (musica.getId() == idMusica) {
                return true;
            }
        }
        return false;
    }

    public boolean agregarMusica(Musica musica) {
        if (musica == null || musica.getId() <= 0) {
            return false;
        }
        if (contieneMusica(musica)) {
            return false;
        }
        return playlist.agregarMusica(musica);
    }

    public boolean eliminarMusica(Musica musica) {
        if (musica == null) {
            return false;
        }
        return playlist.eliminarMusica(musica);
    }

    @Override
    public String toString() {
        return nombre;
    }
}