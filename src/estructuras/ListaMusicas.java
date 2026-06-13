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
    
    public Musica seleccionarPrimera() {
        if (estaVacia()) return null;
        actual = primera;
        return actual.musica;
    }

    public Musica getActual() {
        return actual == null ? null : actual.musica;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }

    public boolean agregarMusica(Musica musica) {
        if (musica == null) return false;

        NMusica nueva = new NMusica(musica);
        if (estaVacia()) {
            primera = nueva;
            ultima = nueva;
            actual = nueva;
        } else {
            ultima.siguiente = nueva;
            nueva.anterior = ultima;
            ultima = nueva;
        }
        cantidad++;
        aplicarModoCircular();
        return true;
    }
    
    public List<Musica> listaMusicas() {
        List<Musica> musicas = new ArrayList<>();
        if (estaVacia()) return musicas;

        NMusica aux = primera;
        for (int i = 0; i < cantidad; i++) {
            musicas.add(aux.musica);
            aux = aux.siguiente;
        }
        return musicas;
    }

    public Musica seleccionarMusica(Musica musica) {
        NMusica encontrada = buscarNMusica(musica);
        if (encontrada == null) return null;
        actual = encontrada;
        return actual.musica;
    }

    public boolean puedeAvanzar() {
        return !estaVacia()
                && actual != null
                && cantidad > 1
                && actual.siguiente != null;
    }

    public Musica avanzar() {
        if (!puedeAvanzar()) return null;
        actual = actual.siguiente;
        return actual.musica;
    }

    public boolean puedeRetroceder() {
        return !estaVacia()
                && actual != null
                && cantidad > 1
                && actual.anterior != null;
    }

    public Musica retroceder() {
        if (!puedeRetroceder()) return null;
        actual = actual.anterior;
        return actual.musica;
    }

    public void setCircular(boolean estado) {
        circular = estado;
        aplicarModoCircular();
    }

    public boolean eliminarMusica(Musica musica) {
        NMusica eliminar = buscarNMusica(musica);
        if (eliminar == null) return false;
        if (cantidad == 1) {
            primera = null;
            ultima = null;
            actual = null;
            cantidad = 0;
            return true;
        }

        NMusica anterior = eliminar.anterior;
        NMusica siguiente = eliminar.siguiente;
        if (eliminar == primera) {
            primera = siguiente;
        }
        if (eliminar == ultima) {
            ultima = anterior;
        }
        if (anterior != null) {
            anterior.siguiente = siguiente;
        }
        if (siguiente != null) {
            siguiente.anterior = anterior;
        }
        if (actual == eliminar) {
            if (siguiente != null && siguiente != eliminar) {
                actual = siguiente;
            } else {
                actual = null;
            }
        }
        cantidad--;
        eliminar.siguiente = null;
        eliminar.anterior = null;
        aplicarModoCircular();
        return true;
    }

    private void aplicarModoCircular() {
        if (estaVacia()) return;
        if (circular) {
            primera.anterior = ultima;
            ultima.siguiente = primera;
        } else {
            primera.anterior = null;
            ultima.siguiente = null;
        }
    }

    private NMusica buscarNMusica(Musica musica) {
        if (musica == null || estaVacia()) return null;
        NMusica aux = primera;
        int idBuscado = musica.getId();
        for (int i = 0; i < cantidad; i++) {
            if (aux.musica.getId() == idBuscado) {
                return aux;
            }
            aux = aux.siguiente;
        }
        return null;
    }
    
    public List<Musica> buscarMusicas(String texto) {
        List<Musica> encontradas = new ArrayList<>();

        if (estaVacia()) return encontradas;

        if (texto == null || texto.isBlank()) {
            return listaMusicas();
        }

        String busqueda = normalizar(texto);

        NMusica aux = primera;
        for (int i = 0; i < cantidad; i++) {
            Musica musica = aux.musica;
            if (coincideBusqueda(musica, busqueda)) {
                encontradas.add(musica);
            }
            aux = aux.siguiente;
        }
        return encontradas;
    }
    
    private boolean coincideBusqueda(Musica musica, String busqueda) {
        if (musica == null) {
            return false;
        }

        if (empiezaCon(musica.getNombre(), busqueda)) {
            return true;
        }
        if (empiezaCon(musica.getArtista(), busqueda)) {
            return true;
        }
        if (empiezaCon(musica.getAlbum(), busqueda)) {
            return true;
        }
        if (empiezaCon(musica.getGenero(), busqueda)) {
            return true;
        }
        return false;
    }

    private boolean empiezaCon(String valor, String busqueda) {
        return valor != null && normalizar(valor).startsWith(busqueda);
    }

    private String normalizar(String texto) {
        return texto == null ? "" : texto.trim().toLowerCase();
    }
}