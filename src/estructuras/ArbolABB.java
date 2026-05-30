package estructuras;

import modelos.Musica;
import modelos.TipoBusqueda;

public class ArbolABB {

    private class NodoABB {
        String clave;
        ListaMusicas musicas;
        NodoABB izquierda;
        NodoABB derecha;

        NodoABB(String clave, Musica musica) {
            this.clave = clave;
            this.musicas = new ListaMusicas();
            this.musicas.agregarMusica(musica);
            this.izquierda = null;
            this.derecha = null;
        }
    }

    private NodoABB raiz;
    private TipoBusqueda tipoBusqueda;

    public ArbolABB(TipoBusqueda tipoBusqueda) {
        this.raiz = null;
        this.tipoBusqueda = tipoBusqueda;
    }

    public TipoBusqueda getTipoBusqueda() {
        return tipoBusqueda;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    private String obtenerClave(Musica musica) {
        switch(tipoBusqueda) {
            case NOMBRE:
                return musica.getNombre();
            case ARTISTA:
                return musica.getArtista();
            case ALBUM:
                return musica.getAlbum();
            case GENERO:
                return musica.getGenero();
            default:
                return musica.getNombre();
        }
    }

    private String validarClave(String clave) {
        if (clave == null || clave.trim().isEmpty()) {
            return "Desconocido";
        }
        return clave.trim();
    }

    public void insertar(Musica musica) {
        if (musica == null) return;

        String clave = validarClave(obtenerClave(musica));
        raiz = insertarRecursivo(raiz, clave, musica);
    }

    private NodoABB insertarRecursivo(NodoABB actual, String clave, Musica musica) {
        if (actual == null) return new NodoABB(clave, musica);

        int comparacion = clave.compareToIgnoreCase(actual.clave);

        if (comparacion < 0) {
            actual.izquierda = insertarRecursivo(actual.izquierda, clave, musica);
        } else if (comparacion > 0) {
            actual.derecha = insertarRecursivo(actual.derecha, clave, musica);
        } else {
            actual.musicas.agregarMusica(musica);
        }
        return actual;
    }

    public ListaMusicas buscar(String clave) {
        if (clave == null || clave.trim().isEmpty()) {
            return null;
        }
        
        NodoABB nodo = buscarRecursivo(raiz, clave.trim());
        if (nodo == null) return null;

        return nodo.musicas;
    }

    private NodoABB buscarRecursivo(NodoABB actual, String clave) {
        if (actual == null) return null;

        int comparacion = clave.compareToIgnoreCase(actual.clave);

        if (comparacion == 0) {
            return actual;
        }
        if (comparacion < 0) {
            return buscarRecursivo(actual.izquierda, clave);
        }
        return buscarRecursivo(actual.derecha, clave);
    }

    public boolean existe(String clave) {
        return buscar(clave) != null;
    }

    public void limpiar() {
        raiz = null;
    }

    public void inOrden() {
        inOrdenRecursivo(raiz);
    }

    private void inOrdenRecursivo(NodoABB actual) {
        if (actual == null) return;

        inOrdenRecursivo(actual.izquierda);
        System.out.println(actual.clave);
        inOrdenRecursivo(actual.derecha);
    }

    public void preOrden() {
        preOrdenRecursivo(raiz);
    }

    private void preOrdenRecursivo(NodoABB actual) {
        if (actual == null) return;

        System.out.println(actual.clave);
        preOrdenRecursivo(actual.izquierda);
        preOrdenRecursivo(actual.derecha);
    }

    public void postOrden() {
        postOrdenRecursivo(raiz);
    }

    private void postOrdenRecursivo(NodoABB actual) {
        if (actual == null) return;

        postOrdenRecursivo(actual.izquierda);
        postOrdenRecursivo(actual.derecha);
        System.out.println(actual.clave);
    }

    public boolean eliminar(String clave) {
        if (buscar(clave) == null) {
            return false;
        }

        raiz = eliminarRecursivo(raiz, clave.trim());
        return true;
    }

    private NodoABB eliminarRecursivo(NodoABB actual, String clave) {
        if (actual == null) return null;
       
        int comparacion = clave.compareToIgnoreCase(actual.clave);
        if (comparacion < 0) {
            actual.izquierda = eliminarRecursivo(actual.izquierda, clave);
        } else if (comparacion > 0) {
            actual.derecha = eliminarRecursivo(actual.derecha, clave);
        } else {
            if (actual.izquierda == null && actual.derecha == null) {
                return null;
            }
            if (actual.izquierda == null) {
                return actual.derecha;
            }
            if (actual.derecha == null) {
                return actual.izquierda;
            }
            NodoABB sucesor = encontrarMinimo(actual.derecha);
            actual.clave = sucesor.clave;
            actual.musicas = sucesor.musicas;
            actual.derecha = eliminarRecursivo(actual.derecha, sucesor.clave);
        }
        return actual;
    }

    private NodoABB encontrarMinimo(NodoABB actual) {
        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual;
    }
}