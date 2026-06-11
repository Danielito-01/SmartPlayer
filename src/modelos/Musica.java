package modelos;

public class Musica {
    private int id;
    private String nombre;
    private String artista;
    private String album;
    private String genero;
    private int duracion;
    private long tamanio;
    private String ruta;
    private int anio;
    private int reproducciones;

    public Musica(int id, String nombre, String artista, String album, String genero, int duracion, long tamanio, String ruta, int anio) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.duracion = duracion;
        this.tamanio = tamanio;
        this.ruta = ruta;
        this.anio = anio;
        this.reproducciones = 0;
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
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

    public int getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(int reproducciones) {
        this.reproducciones = reproducciones;
    }
    
    public void aumentarReproducciones() {
        reproducciones++;
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
    
    public String anioReal() {
        if (anio <= 0) {
            return "Desconocido";
        }
        return String.valueOf(anio);
    }
}