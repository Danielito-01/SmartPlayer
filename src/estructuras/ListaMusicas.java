package estructuras;

import java.util.ArrayList;
import java.util.List;
import modelos.Musica;

public class ListaMusicas {

    private static final class NMusica {
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
    private boolean circular;
    private int cantidad;

    public ListaMusicas() {
        primera = null;
        ultima = null;
        actual = null;
        circular = false;
        cantidad = 0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean estaVacia() {
        return primera == null;
    }

    public Musica getMusicaActual() {
        return actual == null ? null : actual.musica;
    }

    public boolean tieneMusica(Musica musica) {
        if (musica == null) return false;
        
        NMusica aux = primera;
        for (int i = 0; i < cantidad; i++) {
            if (mismaMusica(aux.musica, musica)) {
                return true;
            }
            aux = aux.siguiente;
        }
        return false;
    }

    public void agregarMusica(Musica musica) {
        if (musica == null) return;

        NMusica nuevo = new NMusica(musica);
        if (estaVacia()) {
            primera = nuevo;
            ultima = nuevo;
            actual = nuevo;
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
        cantidad++;
    }

    public boolean eliminarMusica(Musica musica) {
        if (musica == null || estaVacia()) return false;

        NMusica aux = primera;
        for (int i = 0; i < cantidad; i++) {
            if (mismaMusica(aux.musica, musica)) {
                if (cantidad == 1) {
                    primera = null;
                    ultima = null;
                    actual = null;
                    cantidad = 0;
                    return true;
                }
                NMusica anterior = aux.anterior;
                NMusica siguiente = aux.siguiente;
                if (aux == primera) {
                    primera = siguiente;
                }
                if (aux == ultima) {
                    ultima = anterior;
                }
                if (anterior != null) {
                    anterior.siguiente = siguiente;
                }
                if (siguiente != null) {
                    siguiente.anterior = anterior;
                }
                if (actual == aux) {
                    actual = primera;
                }
                cantidad--;
                if (circular && !estaVacia()) {
                    primera.anterior = ultima;
                    ultima.siguiente = primera;
                } else if (!estaVacia()) {
                    primera.anterior = null;
                    ultima.siguiente = null;
                }
                return true;
            }
            aux = aux.siguiente;
        }
        return false;
    }

    public List<Musica> toListAdelante() {
        List<Musica> resultado = new ArrayList<>();
        if (estaVacia()) return resultado;
        
        NMusica aux = primera;
        for (int i = 0; i < cantidad; i++) {
            resultado.add(aux.musica);
            aux = aux.siguiente;
        }
        return resultado;
    }

    public Musica seleccionarMusica(Musica musica) {
        if (musica == null) return null;
       
        NMusica aux = primera;
        for (int i = 0; i < cantidad; i++) {
            if (mismaMusica(aux.musica, musica)) {
                actual = aux;
                return actual.musica;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    public Musica primerMusica() {
        if (estaVacia()) return null;
        actual = primera;
        return actual.musica;
    }

    public Musica ultimaMusica() {
        if (estaVacia()) return null;
        actual = ultima;
        return actual.musica;
    }

    public Musica siguienteMusica() {
        if (actual == null || actual.siguiente == null) return null;
        actual = actual.siguiente;
        return actual.musica;
    }

    public Musica musicaAnterior() {
        if (actual == null || actual.anterior == null) return null;
        actual = actual.anterior;
        return actual.musica;
    }

    public boolean tieneMusicaSiguiente() {
        return actual != null && actual.siguiente != null;
    }

    public boolean tieneMusicaAnterior() {
        return actual != null && actual.anterior != null;
    }

    public void setCircular(boolean estado) {
        if (estaVacia()) return;

        circular = estado;
        if (estado) {
            primera.anterior = ultima;
            ultima.siguiente = primera;
        } else {
            primera.anterior = null;
            ultima.siguiente = null;
        }
    }

    private boolean mismaMusica(Musica a, Musica b) {
        if (a == null || b == null) return false;
        if (a.getId() > 0 && b.getId() > 0) {
            return a.getId() == b.getId();
        }
        if (a.getRuta() != null && b.getRuta() != null) {
            return a.getRuta().trim().equalsIgnoreCase(b.getRuta().trim());
        }
        return false;
    }
}