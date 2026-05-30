package estructuras;

import modelos.Musica;
import modelos.TipoBusqueda;

public class ArbolAVL {

    private class NodoAVL {
        String clave;
        ListaMusicas musicas;
        NodoAVL izquierda;
        NodoAVL derecha;
        int altura;

        NodoAVL(String clave, Musica musica) {
            this.clave = clave;
            this.musicas = new ListaMusicas();
            this.musicas.agregarMusica(musica);
            this.izquierda = null;
            this.derecha = null;
            this.altura = 1;
        }
    }

    private NodoAVL raiz;
    private TipoBusqueda tipoBusqueda;

    public ArbolAVL(TipoBusqueda tipoBusqueda) {
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

    private int altura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private int maximo(int a, int b) {
        return a > b ? a : b;
    }

    private int obtenerBalance(NodoAVL nodo) {
        if (nodo == null) return 0;
       
        return altura(nodo.izquierda) - altura(nodo.derecha);
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierda;
        NodoAVL temp = x.derecha;

        x.derecha = y;
        y.izquierda = temp;

        y.altura = maximo(altura(y.izquierda), altura(y.derecha)) + 1;
        x.altura = maximo(altura(x.izquierda), altura(x.derecha)) + 1;

        return x;
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecha;
        NodoAVL temp = y.izquierda;

        y.izquierda = x;
        x.derecha = temp;

        x.altura = maximo(altura(x.izquierda), altura(x.derecha)) + 1;
        y.altura = maximo(altura(y.izquierda), altura(y.derecha)) + 1;

        return y;
    }

    public void insertar(Musica musica) {
        if (musica == null) return;

        String clave = validarClave(obtenerClave(musica));
        raiz = insertarRecursivo(raiz, clave, musica);
    }

    private NodoAVL insertarRecursivo(NodoAVL nodo, String clave, Musica musica) {
        if (nodo == null) {
            return new NodoAVL(clave, musica);
        }

        int comparacion = clave.compareToIgnoreCase(nodo.clave);

        if (comparacion < 0) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, clave, musica);
        } else if (comparacion > 0) {
            nodo.derecha = insertarRecursivo(nodo.derecha, clave, musica);
        } else {
            nodo.musicas.agregarMusica(musica);
            return nodo;
        }

        nodo.altura = 1 + maximo(altura(nodo.izquierda), altura(nodo.derecha));

        int balance = obtenerBalance(nodo);
        // Caso RD: izquierda - izquierda
        if (balance > 1 && clave.compareToIgnoreCase(nodo.izquierda.clave) < 0) {
            return rotacionDerecha(nodo);
        }
        // Caso RI: derecha - derecha
        if (balance < -1 && clave.compareToIgnoreCase(nodo.derecha.clave) > 0) {
            return rotacionIzquierda(nodo);
        }
        // Caso RID: izquierda - derecha
        if (balance > 1 && clave.compareToIgnoreCase(nodo.izquierda.clave) > 0) {
            nodo.izquierda = rotacionIzquierda(nodo.izquierda);
            return rotacionDerecha(nodo);
        }
        // Caso RDI: derecha - izquierda
        if (balance < -1 && clave.compareToIgnoreCase(nodo.derecha.clave) < 0) {
            nodo.derecha = rotacionDerecha(nodo.derecha);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }

    public ListaMusicas buscar(String clave) {
        if (clave == null || clave.trim().isEmpty()) {
            return null;
        }

        NodoAVL nodo = buscarRecursivo(raiz, clave.trim());
        if (nodo == null) {
            return null;
        }
        return nodo.musicas;
    }

    private NodoAVL buscarRecursivo(NodoAVL actual, String clave) {
        if (actual == null) {
            return null;
        }

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

    private void inOrdenRecursivo(NodoAVL actual) {
        if (actual == null) return;

        inOrdenRecursivo(actual.izquierda);
        System.out.println(actual.clave);
        inOrdenRecursivo(actual.derecha);
    }

    public void preOrden() {
        preOrdenRecursivo(raiz);
    }

    private void preOrdenRecursivo(NodoAVL actual) {
        if (actual == null) return;

        System.out.println(actual.clave);
        preOrdenRecursivo(actual.izquierda);
        preOrdenRecursivo(actual.derecha);
    }

    public void postOrden() {
        postOrdenRecursivo(raiz);
    }

    private void postOrdenRecursivo(NodoAVL actual) {
        if (actual == null) return;

        postOrdenRecursivo(actual.izquierda);
        postOrdenRecursivo(actual.derecha);
        System.out.println(actual.clave);
    }
}