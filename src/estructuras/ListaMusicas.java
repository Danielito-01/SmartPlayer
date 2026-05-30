package estructuras;

import java.util.ArrayList;
import java.util.List;
import modelos.Musica;

public class ListaMusicas {

    private static class NMusica {
        Musica musica;
        NMusica siguiente;
        NMusica anterior;

        NMusica(Musica musica) {
            this.musica = musica;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    private NMusica primera;
    private NMusica ultima;
    private NMusica actual;
    private boolean circular;
    private int tamanio;

    public ListaMusicas() {
        primera = null;
        ultima = null;
        circular = false;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return primera == null;
    }

    public int getTamanio() {
        return tamanio;
    }
    
    public void agregarMusica(Musica musica) {
        if (musica == null) return;

        NMusica nuevo = new NMusica(musica);
        if (estaVacia()) {
            primera = nuevo;
            ultima = nuevo;
        } else {
            if (circular) {
                ultima.siguiente = null;
                primera.anterior = null;
            }
            ultima.siguiente = nuevo;
            nuevo.anterior = ultima;
            ultima = nuevo;
            if (circular) {
                primera.anterior = ultima;
                ultima.siguiente = primera;
            }
        }
        tamanio++;
    }
    
    public Musica buscarPorId(int id) {
        NMusica aux = primera;
        for (int i = 0; i < tamanio; i++) {
            if (aux.musica.getId() == id) {
                return aux.musica;
            }
            aux = aux.siguiente;
        }
        return null;
    }
 
    public boolean tieneMusica(Musica musica) {
        if (musica == null) return false;

        NMusica aux = primera;
        for (int i = 0; i < tamanio; i++) {
            Musica musicaActual = aux.musica;
            if (musicaActual.getId() > 0 && musica.getId() > 0 && musicaActual.getId() == musica.getId()) {
                return true;
            }
            if (musicaActual.getRuta() != null && musica.getRuta() != null
                    && musicaActual.getRuta().trim().equalsIgnoreCase(musica.getRuta().trim())) {
                return true;
            }
            aux = aux.siguiente;
        }
        return false;
    }
    
    public List<Musica> toListAdelante() {
        List<Musica> resultado = new ArrayList<>();
        if (estaVacia()) {
            return resultado;
        }
        NMusica aux = primera;
        if (circular) {
            do {
            resultado.add(aux.musica);
            aux = aux.siguiente;
            } while (aux != null && aux != primera); // se detiene al volver al inicio
        } else {
            while (aux != null) {
            resultado.add(aux.musica);
            aux = aux.siguiente;
            }            
        }
        return resultado;
    }

    public List<Musica> toListAtras() {
        List<Musica> resultado = new ArrayList<>();
        if (estaVacia()) {
            return resultado;
        }
        
        NMusica aux = ultima;
        if (circular) {
            do {
            resultado.add(aux.musica);
            aux = aux.anterior;
            } while (aux != null && aux != ultima);  // se detiene al volver al final
        } else {
            while (aux != null) {
            resultado.add(aux.musica);
            aux = aux.anterior;
            }
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
    
    public boolean tieneSiguiente() {
        return actual != null && actual.siguiente != null;
    }
    
    public boolean tieneAnterior() {
        return actual != null && actual.anterior != null;
    }
    
    public Musica seleccionar(Musica musica) {
        if (musica == null) return null;

        NMusica aux = primera;
        for (int i = 0; i < tamanio; i++) {
            if (aux.musica.getId() == musica.getId()) {
                actual = aux;
                return actual.musica;
            }
            aux = aux.siguiente;
        }
        return null;
    }
    
    public void setCircular(boolean estado) {
        if (estaVacia()) return;

        this.circular = estado;
        if (estado) {
            primera.anterior = ultima;
            ultima.siguiente = primera;
        } else {
            primera.anterior = null;
            ultima.siguiente = null;
        }
    }
    
    public Musica getActual() {
        if (actual == null) {
            return null;
        }
        return actual.musica;
    }
}