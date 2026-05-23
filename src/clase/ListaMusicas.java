package clase;

import java.util.ArrayList;
import java.util.List;

public class ListaMusicas {

    private static class NMusica {
        Musica dato;
        NMusica siguiente;
        NMusica anterior;

        NMusica(Musica dato) {
            this.dato = dato;
        }
    }

    private NMusica primera;
    private NMusica ultima;
    private int size;

    public ListaMusicas() {
        primera = null;
        ultima = null;
        size = 0;
    }

    public boolean estaVacia() {
        return primera == null;
    }

    public int size() {
        return size;
    }
    
    public void agregar(Musica musica) {// Agrega al final (cola)
        if (musica == null) return;

        NMusica nuevo = new NMusica(musica);

        if (estaVacia()) {
            primera = nuevo;
            ultima = nuevo;
        } else {
            ultima.siguiente = nuevo;
            nuevo.anterior = ultima;
            ultima = nuevo;
        }
        size++;
    }
 
    public List<Musica> toListAdelante() {   // Recorrer hacia adelante: devuelve una lista (para mostrar en tablas fácil)
        List<Musica> resultado = new ArrayList<>();
        NMusica actual = primera;
        while (actual != null) {
            resultado.add(actual.dato);
            actual = actual.siguiente;
        }
        return resultado;
    }

    // Recorrer hacia atrás: devuelve una lista desde el final
    public List<Musica> toListAtras() {
        List<Musica> resultado = new ArrayList<>();
        NMusica actual = ultima;
        while (actual != null) {
            resultado.add(actual.dato);
            actual = actual.anterior;
        }
        return resultado;
    }
}