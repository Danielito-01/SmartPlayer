package clase;

import java.util.List;

public class Playlist {
    private final int id;
    private String nombre;
    private final ListaMusicas canciones = new ListaMusicas();

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

    public final void setNombre(String nombre) {
        this.nombre = (nombre == null || nombre.isBlank())
                ? "Sin nombre"
                : nombre.trim();
    }

    public int size() {
        return canciones.size();
    }

   
    public boolean agregarSiNoExiste(Musica m) { /** Agrega una canción solo si NO existe ya en esta playlist (por id, o por ruta como respaldo). */
        if (m == null) return false;
        List<Musica> actual = canciones.toListAdelante();
        
        for (Musica x : actual) {
            if (x == null) continue;

            if (x.getId() > 0 && m.getId() > 0 && x.getId() == m.getId()) {// Regla principal: mismo id => duplicado
                return false;
            }
            
            if (x.getRuta() != null && m.getRuta() != null// Respaldo: misma ruta => duplicado
                    && x.getRuta().trim().equalsIgnoreCase(m.getRuta().trim())) {
                return false;
            }
        }
        canciones.agregar(m);
        return true;
    }

    public List<Musica> toList() {
        return canciones.toListAdelante();
    }

    @Override
    public String toString() {
        return nombre;
    }
}