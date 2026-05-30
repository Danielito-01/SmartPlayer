package estructuras;

import modelos.Musica;

public class PilaHistorial {
    
    public static class NMusica {
        Musica musica;
        NMusica siguiente;
        
        NMusica(Musica musica) {
            this.musica = musica;
            this.siguiente = null;
        }
    }
            
    private NMusica cima;
    private int tamanio;
    
    public PilaHistorial() {
        this.cima = null;
        this.tamanio = 0;
    }
   
    public int getTamanio() {
        return tamanio;
    }
   
    public boolean estaVacia() {
        return cima == null;
    }
    
    public void push(Musica musica) {
        NMusica nueva = new NMusica(musica);
        nueva.siguiente = cima;
        cima = nueva;
        tamanio++;
    }
    
    public Musica pop() {
        if (estaVacia()) return null;
       
        Musica musica = cima.musica;
        cima = cima.siguiente;
        tamanio--;
        return musica;
    }
    
    public Musica peek() {
        return estaVacia() ? null : cima.musica;
    }
    
    public void limpiar() {
        cima = null;
        tamanio = 0;
    }
}
