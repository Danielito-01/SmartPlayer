package clase;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Musica {
    private int id;
    private String nombre;
    private String artista;
    private String album;
    private String genero;
    private long duracion;
    private long tamanio;
    private String ruta;
    private int anio;
    private ImageIcon portada;

    public Musica() {
    }

    public Musica(int id, String nombre, String artista, String album, String genero, long duracion, long tamanio, String ruta, int anio, ImageIcon portada) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.duracion = duracion;
        this.tamanio = tamanio;
        this.ruta = ruta;
        this.anio = anio;
        this.portada = portada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return nombre + " - " + artista;
    }
    
    public String formatearDuracion() {
        long minutos = duracion / 60;
        long segundosRestantes = duracion % 60;
        return String.format(
                "%02d:%02d",
                minutos,
                segundosRestantes
        );
    }
    
    public String formatearTamanio() {
        double kb = tamanio / 1024.0;
        double mb = kb / 1024.0;
        double gb = mb / 1024.0;
        if (gb >= 1) {
            return String.format("%.2f GB", gb);
        }
        if (mb >= 1) {
            return String.format("%.2f MB", mb);
        }
        if (kb >= 1) {
            return String.format("%.2f KB", kb);
        }
        return tamanio + " B";
    }
    
    public String anioReal(){
        if (anio <= 0) {
            return "Desconocido";
        }else {
            String anioReal = String.valueOf(anio);
            return anioReal;
        }
    }
    
    public ImageIcon getPortadaEscalada(int ancho, int alto) {
        Image img = portada.getImage();
        BufferedImage buffered = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = buffered.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(img, 0, 0, ancho, alto, null);
        g2d.dispose();

        return new ImageIcon(buffered);
    }
}
