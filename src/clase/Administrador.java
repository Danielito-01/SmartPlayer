package clase;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;

public class Administrador {

    public static List<File> seleccionarArchivos(java.awt.Component parent) {
        JFileChooser chooser = new JFileChooser(); // Crea el explorador
        chooser.setDialogTitle("Selecciona archivos MP3 o carpetas"); // Titulo
        chooser.setMultiSelectionEnabled(true); // Permite seleccionar varios
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // Acepta carpetas y archivos
        chooser.setAcceptAllFileFilterUsed(false); // Oculta "Todos los archivos"
        chooser.setFileFilter(new FileFilter() {// Filtro personalizado

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || esMp3(f); // Muestra carpetas o archivos mp3
            }

            @Override
            public String getDescription() {
                return "Archivos MP3 y carpetas";
            }
        });

        int resultado = chooser.showOpenDialog(parent);// Abre la ventana
        if (resultado != JFileChooser.APPROVE_OPTION) { // Si cancela

            return new ArrayList<>();
        }

        Set<String> rutasUnicas = new LinkedHashSet<>(); // Evita duplicados
        File[] seleccionados = chooser.getSelectedFiles(); // Archivos seleccionados

        for (File archivo : seleccionados) {// Recorre todo lo seleccionado
            if (archivo.isDirectory()) { // Si es carpeta

                buscarEnCarpetas(archivo, rutasUnicas);
            }else if (archivo.isFile() && esMp3(archivo)) {// Si es mp3
                agregarRutaUnica(archivo, rutasUnicas);
            }
        }

        List<File> archivosMp3 = new ArrayList<>();// Convierte rutas a File
        for (String ruta : rutasUnicas) {
            archivosMp3.add(new File(ruta));
        }
        return archivosMp3;
    }

    private static void buscarEnCarpetas(File directorio, Set<String> rutasUnicas) {
        File[] archivos = directorio.listFiles(); // Obtiene el contenido de la carpeta
     
        if (archivos == null) {// Si no puede leerla
            return;
        }

        for (File archivo : archivos) {// Recorre contenido

            if (archivo.isDirectory()) {// Si es subcarpeta
                buscarEnCarpetas(archivo, rutasUnicas);
            }else if (archivo.isFile() && esMp3(archivo)) {// Si es archivo mp3
                agregarRutaUnica(archivo, rutasUnicas);
            }
        }
    }

    private static void agregarRutaUnica(File archivo, Set<String> rutasUnicas) {
        try {
            String rutaCanonica = archivo.getCanonicalPath();// Ruta real del archivo
            rutasUnicas.add(rutaCanonica);
        } catch (IOException e) {
            rutasUnicas.add(archivo.getAbsolutePath());// Si falla usa absoluta
        }
    }

    private static boolean esMp3(File archivo) {
        return archivo != null
                && archivo.getName().toLowerCase().endsWith(".mp3");
    }

    public static List<Musica> extraerMusicasTemporales(List<File> archivos) {
        List<Musica> musicas = new ArrayList<>();
        Set<String> rutasUnicas = new LinkedHashSet<>();// Evita repetir canciones
        for (File archivo : archivos) {// Recorre archivos

            if (archivo == null || !archivo.isFile() || !esMp3(archivo)) {// Validaciones
                continue;
            }
            
            try {
                String rutaCanonica = archivo.getCanonicalPath();// Ruta unica
                
                if (!rutasUnicas.add(rutaCanonica)) {// Si ya existe
                    continue;
                }
                
                AudioFile audioFile = AudioFileIO.read(archivo);// Lee el archivo mp3
                Tag tag = audioFile.getTag();// Obtiene metadata
                
                String nombre = quitarExtension(archivo.getName());// Valores por defecto
                String artista = "Desconocido";
                String album = "Desconocido";
                String genero = "Desconocido";
                int anio = 0;
                ImageIcon portada = null;
                long duracion = 0;
                long tamanio = archivo.length();

                if (audioFile.getAudioHeader() != null) {// Obtiene duración
                    duracion = audioFile.getAudioHeader().getTrackLength();
                }

                if (tag != null) {// Si tiene metadata
                    // Obtiene campos
                    nombre = valorSeguro(tag.getFirst(FieldKey.TITLE), nombre);
                    artista = valorSeguro(tag.getFirst(FieldKey.ARTIST), "Desconocido");
                    album = valorSeguro(tag.getFirst(FieldKey.ALBUM), "Desconocido");
                    genero = valorSeguro(tag.getFirst(FieldKey.GENRE), "Desconocido");

                    String anioTexto = tag.getFirst(FieldKey.YEAR);// Obtiene año

                    if (anioTexto != null && !anioTexto.isBlank()) {
                        try {
                            anioTexto = anioTexto.replaceAll("[^0-9]", "");// Limpia caracteres raros
                            if (!anioTexto.isBlank()) {// Convierte a entero
                                anio = Integer.parseInt(anioTexto);
                            }
                        }catch (NumberFormatException e) {
                            anio = 0;
                        }
                    }
                    portada = extraerPortada(tag);// Obtiene portada
                }
                
                Musica musica = new Musica(0, nombre, artista, album, genero, duracion, tamanio, rutaCanonica, anio, portada); // Crea objeto musica
                musicas.add(musica);// Agrega a lista
            }catch (CannotReadException |
                   IOException |
                   TagException |
                   ReadOnlyFileException |
                   InvalidAudioFrameException e) {
                System.err.println("No se pudo leer: " + archivo.getAbsolutePath());
            }
        }
        return musicas;
    }

    private static ImageIcon extraerPortada(Tag tag) {
        try {
            Artwork artwork = tag.getFirstArtwork();
            
            if (artwork == null || artwork.getBinaryData() == null) {
                return obtenerPortadaPorDefecto();
            }

            BufferedImage bufferedImage = ImageIO.read(// Convierte bytes a imagen
                    new ByteArrayInputStream(artwork.getBinaryData())
            );

            if (bufferedImage == null) {
                return obtenerPortadaPorDefecto();
            }

            Image imagenEscalada = bufferedImage.getScaledInstance(
                    100,
                    100,
                    Image.SCALE_SMOOTH
            );
            return new ImageIcon(imagenEscalada);
        }
        catch (Exception e) {
            return obtenerPortadaPorDefecto();
        }
    }
    
    private static ImageIcon obtenerPortadaPorDefecto() {
        try {
            BufferedImage imagen = ImageIO.read(// Carga imagen desde resources
                    Administrador.class.getResource("/imagenes/SmartPlayerLogo.png")
            );
        
            Image imagenEscalada = imagen.getScaledInstance(
                    100,
                    100,
                    Image.SCALE_SMOOTH
            );
            return new ImageIcon(imagenEscalada);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static void mostrarMusicasEnTabla(List<Musica> musicas, JTable tabla) {
        int no = 1;
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        tabla.setRowHeight(100);

        for (Musica musica : musicas) {
            modelo.addRow(new Object[]{
                no++,
                musica.getNombre(),
                musica.getArtista(),
                musica.getAlbum(),
                musica.getGenero(),
                formatearDuracion(musica.getDuracion()), // 03:45
                formatearTamanio(musica.getTamanio()), // 5.2 MB
                musica.getRuta(),
                anioDesconocido(musica.getAnio()),
                musica.getPortada() // ImageIcon real
            });
        }
    }

    private static String valorSeguro(String valor, String defecto) {
        if (valor == null || valor.isBlank()) {// Si viene null o vacío
            return defecto;
        }
        return valor.trim();
    }

    private static String quitarExtension(String nombreArchivo) {       
        int punto = nombreArchivo.lastIndexOf('.');// Busca ultimo punto

        if (punto > 0) {// Si existe punto
            return nombreArchivo.substring(0, punto);
        }
        return nombreArchivo;
    }
    
    private static String formatearDuracion(long segundos) {
        long minutos = segundos / 60;
        long segundosRestantes = segundos % 60;
        return String.format(
                "%02d:%02d",
                minutos,
                segundosRestantes
        );
    }
    
    private static String formatearTamanio(long bytes) {
        double kb = bytes / 1024.0;
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
        return bytes + " B";
    }
    
    public static String anioDesconocido(int anio){
        if (anio == 0) {
            return "Desconocido";
        }else {
            String anioReal= String.valueOf(anio);
            return anioReal;
        }
    }
}
