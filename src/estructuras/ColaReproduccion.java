package estructuras;

import modelos.Musica;

public class ColaReproduccion {
    
    private static class NMusica {
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
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }
    
    public int getTamanio() {
        return tamanio;
    }
    
    public boolean estaVacia() {
        return frente == null;
    }
    
    public void encolar(Musica musica) {
        NMusica nueva = new NMusica(musica);
        if (estaVacia()) {
            frente = nueva;
            fin = nueva;
        }else {
            fin.siguiente = nueva;
            fin = nueva;
        }
        tamanio++;
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
    
    public void limpiar() {
        frente = null;
        fin = null;
        tamanio = 0;
    }
}
