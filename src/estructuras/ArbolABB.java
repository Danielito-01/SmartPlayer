package estructuras;

import java.util.ArrayList;
import java.util.List;
import modelos.Musica;

public class ArbolABB {

    private static final class NMusica {
        Musica musica;
        NMusica izquierdo;
        NMusica derecho;

        NMusica(Musica musica) {
            this.musica = musica;
        }
    }

    private NMusica raiz;
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // INSERCIÓN
    public boolean insertar(Musica musica) {
        if (musica == null || musica.getId() <= 0) return false;
        boolean[] insertado = {false};
        raiz = insertarRec(raiz, musica, insertado);
        if (insertado[0]) {
            cantidad++;
        }
        return insertado[0];
    }

    private NMusica insertarRec(NMusica nodo, Musica musica, boolean[] insertado) {
        if (nodo == null) {
            insertado[0] = true;
            return new NMusica(musica);
        }
        int comparacion = comparar(musica, nodo.musica);
        if (comparacion < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, musica, insertado);
        } else if (comparacion > 0) {
            nodo.derecho = insertarRec(nodo.derecho, musica, insertado);
        }
        return nodo;
    }

    // BÚSQUEDA POR NOMBRE
    public List<Musica> buscarPorNombre(String nombre) {
        List<Musica> resultado = new ArrayList<>();
        buscarPorNombreRec(raiz, limpiar(nombre), resultado);
        return resultado;
    }

    private void buscarPorNombreRec(NMusica nodo, String nombre, List<Musica> resultado) {
        if (nodo == null) return;

        int comparacion = nombre.compareTo(limpiar(nodo.musica.getNombre()));

        if (comparacion < 0) {
            buscarPorNombreRec(nodo.izquierdo, nombre, resultado);
        } else if (comparacion > 0) {
            buscarPorNombreRec(nodo.derecho, nombre, resultado);
        } else {
            resultado.add(nodo.musica);

            buscarPorNombreRec(nodo.izquierdo, nombre, resultado);
            buscarPorNombreRec(nodo.derecho, nombre, resultado);
        }
    }

    public Musica buscarExacta(String nombre, int id) {
        NMusica aux = raiz;

        while (aux != null) {
            int comparacion = comparar(nombre, id, aux.musica);

            if (comparacion == 0) {
                return aux.musica;
            } else if (comparacion < 0) {
                aux = aux.izquierdo;
            } else {
                aux = aux.derecho;
            }
        }
        return null;
    }

    // MODIFICACIÓN
    public boolean modificar(String nombreActual, int id, Musica datosNuevos) {
        Musica musica = buscarExacta(nombreActual, id);

        if (musica == null || datosNuevos == null) {
            return false;
        }

        eliminarPorClave(nombreActual, id);

        musica.setNombre(datosNuevos.getNombre());
        musica.setArtista(datosNuevos.getArtista());
        musica.setAlbum(datosNuevos.getAlbum());
        musica.setGenero(datosNuevos.getGenero());
        musica.setDuracion(datosNuevos.getDuracion());
        musica.setTamanio(datosNuevos.getTamanio());
        musica.setRuta(datosNuevos.getRuta());
        musica.setAnio(datosNuevos.getAnio());
        musica.setPortada(datosNuevos.getPortada());
        musica.setReproducciones(datosNuevos.getReproducciones());

        musica.setId(id);

        insertar(musica);

        return true;
    }

    // ELIMINACIÓN
    public boolean eliminar(Musica musica) {
        if (musica == null) return false;

        return eliminarPorClave(musica.getNombre(), musica.getId());
    }

    public boolean eliminarPorClave(String nombre, int id) {
        boolean[] eliminado = {false};

        raiz = eliminarRec(raiz, nombre, id, eliminado);

        if (eliminado[0]) {
            cantidad--;
        }

        return eliminado[0];
    }

    private NMusica eliminarRec(NMusica nodo, String nombre, int id, boolean[] eliminado) {
        if (nodo == null) return null;

        int comparacion = comparar(nombre, id, nodo.musica);

        if (comparacion < 0) {
            nodo.izquierdo = eliminarRec(nodo.izquierdo, nombre, id, eliminado);
        } else if (comparacion > 0) {
            nodo.derecho = eliminarRec(nodo.derecho, nombre, id, eliminado);
        } else {
            eliminado[0] = true;

            if (nodo.izquierdo == null) {
                return nodo.derecho;
            }

            if (nodo.derecho == null) {
                return nodo.izquierdo;
            }

            NMusica sucesor = buscarMenor(nodo.derecho);
            nodo.musica = sucesor.musica;
            nodo.derecho = eliminarMenor(nodo.derecho);
        }

        return nodo;
    }

    private NMusica buscarMenor(NMusica nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }

        return nodo;
    }

    private NMusica eliminarMenor(NMusica nodo) {
        if (nodo.izquierdo == null) {
            return nodo.derecho;
        }

        nodo.izquierdo = eliminarMenor(nodo.izquierdo);
        return nodo;
    }

    // RECORRIDOS
    public List<Musica> inOrden() {
        List<Musica> lista = new ArrayList<>();
        inOrdenRec(raiz, lista);
        return lista;
    }

    private void inOrdenRec(NMusica nodo, List<Musica> lista) {
        if (nodo == null) return;

        inOrdenRec(nodo.izquierdo, lista);
        lista.add(nodo.musica);
        inOrdenRec(nodo.derecho, lista);
    }

    public List<Musica> preOrden() {
        List<Musica> lista = new ArrayList<>();
        preOrdenRec(raiz, lista);
        return lista;
    }

    private void preOrdenRec(NMusica nodo, List<Musica> lista) {
        if (nodo == null) return;

        lista.add(nodo.musica);
        preOrdenRec(nodo.izquierdo, lista);
        preOrdenRec(nodo.derecho, lista);
    }

    public List<Musica> postOrden() {
        List<Musica> lista = new ArrayList<>();
        postOrdenRec(raiz, lista);
        return lista;
    }

    private void postOrdenRec(NMusica nodo, List<Musica> lista) {
        if (nodo == null) return;

        postOrdenRec(nodo.izquierdo, lista);
        postOrdenRec(nodo.derecho, lista);
        lista.add(nodo.musica);
    }

    private int comparar(Musica a, Musica b) {
        return comparar(a.getNombre(), a.getId(), b);
    }

    private int comparar(String nombre, int id, Musica b) {
        int comparacionNombre = limpiar(nombre).compareTo(limpiar(b.getNombre()));

        if (comparacionNombre != 0) {
            return comparacionNombre;
        }

        return Integer.compare(id, b.getId());
    }

    private String limpiar(String texto) {
        return texto == null ? "" : texto.trim().toLowerCase();
    }
}