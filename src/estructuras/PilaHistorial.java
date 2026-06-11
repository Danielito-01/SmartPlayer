package estructuras;

import java.util.ArrayList;
import java.util.List;
import modelos.Musica;

public class PilaHistorial {
    
    private static final class NMusica {
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
        cima = null;
        tamanio = 0;
    }
   
    public boolean estaVacia() {
        return cima == null;
    }

    public int getTamanio() {
        return tamanio;
    }
    
    public boolean push(Musica musica) {
        if (musica == null) return false;

        NMusica nueva = new NMusica(musica);
        nueva.siguiente = cima;
        cima = nueva;
        tamanio++;
        return true;
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

    public List<Musica> listaMusicas() {
        List<Musica> musicas = new ArrayList<>();

        NMusica aux = cima;

        while (aux != null) {
            musicas.add(aux.musica);
            aux = aux.siguiente;
        }

        return musicas;
    }
    
    public void limpiar() {
        cima = null;
        tamanio = 0;
    }
}