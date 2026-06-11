package estructuras;

import java.util.ArrayList;
import java.util.List;
import modelos.Musica;

public class ColaReproduccion {
    
    private static final class NMusica {
        Musica musica;
        NMusica siguiente;
        
        NMusica(Musica musica) {
            this.musica = musica;
            this.siguiente = null;
        }
    }
    
    private NMusica frente;
    private NMusica fin;
    private int tamanio;
    
    public ColaReproduccion() {
        frente = null;
        fin = null;
        tamanio = 0;
    }
    
    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }
    
    public boolean encolar(Musica musica) {
        if (musica == null) return false;

        NMusica nueva = new NMusica(musica);
        if (estaVacia()) {
            frente = nueva;
            fin = nueva;
        } else {
            fin.siguiente = nueva;
            fin = nueva;
        }
        tamanio++;
        return true;
    }
    
    public Musica desencolar() {
        if (estaVacia()) return null;
        Musica musica = frente.musica;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return musica;
    }
    
    public Musica peek() {
        return estaVacia() ? null : frente.musica;
    }

    public boolean eliminarEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= tamanio || estaVacia()) {
            return false;
        }
        if (posicion == 0) {
            desencolar();
            return true;
        }
        NMusica anterior = frente;
        for (int i = 0; i < posicion - 1; i++) {
            anterior = anterior.siguiente;
        }
        NMusica eliminar = anterior.siguiente;
        anterior.siguiente = eliminar.siguiente;
        if (eliminar == fin) {
            fin = anterior;
        }
        eliminar.siguiente = null;
        tamanio--;
        return true;
    }

    public List<Musica> listaMusicas() {
        List<Musica> musicas = new ArrayList<>();
        NMusica aux = frente;
        while (aux != null) {
            musicas.add(aux.musica);
            aux = aux.siguiente;
        }
        return musicas;
    }
    
    public void limpiar() {
        frente = null;
        fin = null;
        tamanio = 0;
    }
}