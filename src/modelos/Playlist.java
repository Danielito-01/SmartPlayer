package modelos;

import estructuras.ListaMusicas;
import java.util.List;
import modelos.Musica;

public class Playlist {
    private final int id;
    private String nombre;
    private final ListaMusicas musicas = new ListaMusicas();

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
    
    public ListaMusicas getMusicas() {
        return musicas;
    }

    public final void setNombre(String nombre) {
        this.nombre = (nombre == null || nombre.isBlank())
                ? "Sin nombre"
                : nombre.trim();
    }

    public int getTamanio() {
        return musicas.getTamanio();
    }

   
    public boolean agregarMusica(Musica musica) {
        if (musica == null) {
            return false;
        }
        if (tieneMusica(musica)) {
            return false;
        }
        musicas.agregarMusica(musica);
        return true;
    }
    
    public boolean tieneMusica(Musica musica) {
        return musicas.tieneMusica(musica);
    }

    public List<Musica> toList() {
        return musicas.toListAdelante();
    }

    @Override
    public String toString() {
        return nombre;
    }
}