package servicios;

import estructuras.ListaMusicas;
import modelos.Musica;

public class GestorReproduccion {

    private final Reproductor reproductor;

    private ListaMusicas listaActualReproduciendo;
    private Musica musicaActualReproduciendo;

    private int duracionActualSegundos;
    private int tiempoActualSegundos;

    public GestorReproduccion(Reproductor reproductor) {
        this.reproductor = reproductor;
    }

    public ListaMusicas getListaActualReproduciendo() {
        return listaActualReproduciendo;
    }

    public Musica getMusicaActualReproduciendo() {
        return musicaActualReproduciendo;
    }

    public int getDuracionActualSegundos() {
        return duracionActualSegundos;
    }

    public int getTiempoActualSegundos() {
        return tiempoActualSegundos;
    }

    public void prepararNuevaMusica(Musica musica) {
        musicaActualReproduciendo = musica;
        duracionActualSegundos = (int) musica.getDuracion();
        tiempoActualSegundos = 0;
    }

    public void reproducir(Musica musica) {
        if (musica == null) return;

        prepararNuevaMusica(musica);
        reproductor.reproducir(musica);
    }

    public void pausar() {
        reproductor.pausar();
    }

    public void reanudar() {
        reproductor.reanudar();
    }

    public void detener() {
        reproductor.detener();
    }

    public boolean estaReproduciendo() {
        return reproductor.estaReproduciendo();
    }

    public boolean estaPausado() {
        return reproductor.estaPausado();
    }

    public boolean estaFinalizada() {
        return reproductor.estaFinalizada();
    }

    public boolean tieneListaReproduciendo() {
        return listaActualReproduciendo != null;
    }

    public Musica siguiente() {
        if (listaActualReproduciendo == null) {
            return null;
        }

        if (listaActualReproduciendo.tieneSiguiente()) {
            return listaActualReproduciendo.siguiente();
        }

        return null;
    }

    public Musica anterior() {
        if (listaActualReproduciendo == null) {
            return null;
        }

        if (listaActualReproduciendo.tieneAnterior()) {
            return listaActualReproduciendo.anterior();
        }

        return null;
    }

    public Musica iniciarLista(ListaMusicas lista) {
        if (lista == null || lista.estaVacia()) {
            return null;
        }

        listaActualReproduciendo = lista;
        return listaActualReproduciendo.primera();
    }

    public Musica seleccionarMusicaEnLista(ListaMusicas lista, Musica musica) {
        if (lista == null || musica == null) {
            return null;
        }

        listaActualReproduciendo = lista;
        return listaActualReproduciendo.seleccionar(musica);
    }

    public void avanzarTiempo() {
        tiempoActualSegundos++;

        if (tiempoActualSegundos > duracionActualSegundos) {
            tiempoActualSegundos = duracionActualSegundos;
        }
    }

    public void marcarComoTerminada() {
        tiempoActualSegundos = duracionActualSegundos;
    }

    public void setCircular(boolean estado) {
        if (listaActualReproduciendo != null) {
            listaActualReproduciendo.setCircular(estado);
        }
    }
}