package estructuras;

import java.util.ArrayList;
import java.util.List;
import modelos.Musica;

public class ArbolAVL {

    private static final class NMusica {
        Musica musica;
        NMusica izquierdo;
        NMusica derecho;
        int altura;

        NMusica(Musica musica) {
            this.musica = musica;
            this.altura = 1;
        }
    }

    private NMusica raiz;
    private int cantidad;
    
    public int getCantidad() {
        return cantidad;
    }

    public int getAltura() {
        return altura(raiz);
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
        } else {
            return nodo;
        }
        actualizarAltura(nodo);
        return balancear(nodo);
    }
   
    // BÚSQUEDA
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
        actualizarAltura(nodo);
        return balancear(nodo);
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
        actualizarAltura(nodo);
        return balancear(nodo);
    }

    // BALANCEO AUTOMÁTICO
    private NMusica balancear(NMusica nodo) {
        int balance = factorBalance(nodo);

        // RD
        if (balance > 1 && factorBalance(nodo.izquierdo) >= 0) {
            return rotacionDerecha(nodo);
        }

        // RID
        if (balance > 1 && factorBalance(nodo.izquierdo) < 0) {
            return rotacionIzquierdaDerecha(nodo);
        }

        // RI
        if (balance < -1 && factorBalance(nodo.derecho) <= 0) {
            return rotacionIzquierda(nodo);
        }

        // RDI
        if (balance < -1 && factorBalance(nodo.derecho) > 0) {
            return rotacionDerechaIzquierda(nodo);
        }
        return nodo;
    }

    // RI: Rotación izquierda
    private NMusica rotacionIzquierda(NMusica x) {
        NMusica y = x.derecho;
        NMusica temp = y.izquierdo;

        y.izquierdo = x;
        x.derecho = temp;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    // RD: Rotación derecha
    private NMusica rotacionDerecha(NMusica y) {
        NMusica x = y.izquierdo;
        NMusica temp = x.derecho;

        x.derecho = y;
        y.izquierdo = temp;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    // RID: Rotación izquierda-derecha
    private NMusica rotacionIzquierdaDerecha(NMusica nodo) {
        nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
        return rotacionDerecha(nodo);
    }

    // RDI: Rotación derecha-izquierda
    private NMusica rotacionDerechaIzquierda(NMusica nodo) {
        nodo.derecho = rotacionDerecha(nodo.derecho);
        return rotacionIzquierda(nodo);
    }

    private int altura(NMusica nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private void actualizarAltura(NMusica nodo) {
        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
    }

    private int factorBalance(NMusica nodo) {
        return nodo == null ? 0 : altura(nodo.izquierdo) - altura(nodo.derecho);
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