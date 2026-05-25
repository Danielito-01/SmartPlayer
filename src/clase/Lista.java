package clase;

import java.util.ArrayList;
import java.util.List;

public class Lista {

    private static class NMusica {
        Musica musica;
        NMusica siguiente;
        NMusica anterior;

        NMusica(Musica musica) {
            this.musica = musica;
        }
    }

    private NMusica primera;
    private NMusica ultima;
    private NMusica actual;
    private int size;

    public Lista() {
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
    
    public void agregarMusica(Musica musica) {// Agrega al final (cola)
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
 
    public boolean tieneMusica(Musica musica) {
        if (musica == null) {
            return false;
        }
        NMusica aux = primera;
        while (aux != null) {
            Musica actual = aux.musica;
           
            if (actual.getId() > 0 && musica.getId() > 0 && actual.getId() == musica.getId()) {  // Comparar por ID
                return true;
            }  
            if (actual.getRuta() != null && musica.getRuta() != null && actual.getRuta()
                    .trim().equalsIgnoreCase(musica.getRuta().trim())) { // Comparar por ruta
                return true;
            }
            aux = aux.siguiente;
        }
        return false;
    }
    
    public List<Musica> toListAdelante() {   // Recorrer hacia adelante: devuelve una lista (para mostrar en tablas fácil)
        List<Musica> resultado = new ArrayList<>();
        NMusica actual = primera;
        while (actual != null) {
            resultado.add(actual.musica);
            actual = actual.siguiente;
        }
        return resultado;
    }

    // Recorrer hacia atrás: devuelve una lista desde el final
    public List<Musica> toListAtras() {
        List<Musica> resultado = new ArrayList<>();
        NMusica actual = ultima;
        while (actual != null) {
            resultado.add(actual.musica);
            actual = actual.anterior;
        }
        return resultado;
    }
    
    public Musica primera() {
        if (estaVacia()) {
            return null;
        }
        actual = primera;
        return actual.musica;
    }
    
    public Musica ultima() {
        if (estaVacia()) {
            return null;
        }
        actual = ultima;
        return actual.musica;
    }
    
    public Musica siguiente() {
        if (actual == null) {
            return null;
        }
        if (actual.siguiente != null) {
            actual = actual.siguiente;
            return actual.musica;
        }
        return null;
    }
    
    public Musica anterior() {
        if (actual == null) {
            return null;
        }
        if (actual.anterior != null) {
            actual = actual.anterior;
            return actual.musica;
        }
        return null;
    }
    
    public Musica seleccionar(Musica musica) {
        if (musica == null) {
        return null;
        }
        NMusica aux = primera;
        while (aux != null) {
            if (aux.musica.getId() == musica.getId()) {
                actual = aux;
                return actual.musica;
            }
            aux = aux.siguiente;
        }
        return null;
    } 
    
    public Musica getActual() {
        if (actual == null) {
            return null;
        }
        return actual.musica;
    }
}