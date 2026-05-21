package clase;

import javax.swing.ImageIcon;

public class Musica {
    private String nombre;
    private String artista;
    private String album;
    private String genero;
    private long duracion;
    private long tamanio;
    private String ruta;
    private int anio;
    private ImageIcon portada;
    private Musica siguiente;
    private Musica anterior;
    
    public Musica() {
        
    }

    public Musica(String nombre, String artista, String album, String genero, long duracion, long tamanio, String ruta, int anio, ImageIcon portada) {
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.duracion = duracion;
        this.tamanio = tamanio;
        this.ruta = ruta;
        this.anio = anio;
        this.portada = portada;
        this.siguiente = null;
        this.anterior = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public long getDuracion() {
        return duracion;
    }

    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }

    public long getTamanio() {
        return tamanio;
    }

    public void setTamanio(long tamanio) {
        this.tamanio = tamanio;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public ImageIcon getPortada() {
        return portada;
    }

    public void setPortada(ImageIcon portada) {
        this.portada = portada;
    }

    public Musica getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Musica siguiente) {
        this.siguiente = siguiente;
    }

    public Musica getAnterior() {
        return anterior;
    }

    public void setAnterior(Musica anterior) {
        this.anterior = anterior;
    }
    
    @Override
    public String toString() {
        return nombre + " - " + artista;
    }
}
