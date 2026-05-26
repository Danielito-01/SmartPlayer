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
        JFileChooser explorador = new JFileChooser(); // Crea el explorador
        explorador.setDialogTitle("Selecciona archivos MP3 o carpetas"); // Titulo
        explorador.setMultiSelectionEnabled(true); // Permite seleccionar varios
        explorador.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // Acepta carpetas y archivos
        explorador.setAcceptAllFileFilterUsed(false); // Oculta "Todos los archivos"
        explorador.setFileFilter(new FileFilter() {// Filtro personalizado

            @Override
            public boolean accept(File archivo) {
                return archivo.isDirectory() || esMp3(archivo);
            }

            @Override
            public String getDescription() {
                return "Archivos MP3 y carpetas";
            }
        });

        int resultado = explorador.showOpenDialog(parent);// Abre la ventana
        if (resultado != JFileChooser.APPROVE_OPTION) { // Si cancela
            return new ArrayList<>();
        }

        Set<String> rutasUnicas = new LinkedHashSet<>(); // Evita duplicados
        File[] archivosSeleccionados = explorador.getSelectedFiles(); // Archivos archivosSeleccionados

        for (File archivo : archivosSeleccionados) {// Recorre todo lo seleccionado
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

    private static void agregarRutaUnica(File archivo, Set<String> rutas) {
        try {
            String rutaCanonica = archivo.getCanonicalPath();// Ruta real del archivo
            rutas.add(rutaCanonica);
        } catch (IOException e) {
            rutas.add(archivo.getAbsolutePath());// Si falla usa absoluta
        }
    }

    private static boolean esMp3(File archivo) {
        return archivo != null
                && archivo.getName().toLowerCase().endsWith(".mp3");
    }

    public static List<Musica> extraerDatosDeMusicas(List<File> archivos) {
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
                
                AudioFile datosMusica = AudioFileIO.read(archivo);// Lee el archivo mp3
                Tag audio = datosMusica.getTag();// Obtiene metadata
                
                String nombre = quitarExtension(archivo.getName());// Valores por defecto
                String artista = "Desconocido";
                String album = "Desconocido";
                String genero = "Desconocido";
                int anio = 0;
                ImageIcon portada = null;
                long duracion = 0;
                long tamanio = archivo.length();

                if (datosMusica.getAudioHeader() != null) {// Obtiene duracion
                    duracion = datosMusica.getAudioHeader().getTrackLength();
                }

                if (audio != null) {// Si tiene metadata
                    // Obtiene campos
                    nombre = valorSeguro(audio.getFirst(FieldKey.TITLE), nombre);
                    artista = valorSeguro(audio.getFirst(FieldKey.ARTIST), "Desconocido");
                    album = valorSeguro(audio.getFirst(FieldKey.ALBUM), "Desconocido");
                    genero = valorSeguro(audio.getFirst(FieldKey.GENRE), "Desconocido");

                    String anioS = audio.getFirst(FieldKey.YEAR);// Obtiene año

                    if (anioS != null && !anioS.isBlank()) {
                        try {
                            anioS = anioS.replaceAll("[^0-9]", "");// Limpia caracteres raros
                            if (!anioS.isBlank()) {// Convierte a entero
                                anio = Integer.parseInt(anioS);
                            }
                        }catch (NumberFormatException e) {
                            anio = 0;
                        }
                    }
                    portada = extraerPortada(audio);// Obtiene portada
                }
                
                Musica musica = new Musica(0, nombre, artista, album, genero, duracion, tamanio, rutaCanonica, anio, portada); // Crea objeto audio
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
            Artwork imageBytes = tag.getFirstArtwork();
            if (imageBytes == null || imageBytes.getBinaryData() == null) {
                return obtenerPortadaPorDefecto();
            }

            BufferedImage imagen = ImageIO.read(// Convierte bytes a imagen
                    new ByteArrayInputStream(imageBytes.getBinaryData())
            );

            if (imagen == null) {
                return obtenerPortadaPorDefecto();
            }

            Image imagenEscalada = imagen.getScaledInstance(
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
            BufferedImage imagen = ImageIO.read(
                    Administrador.class.getResource("/imagen/SmartPlayerLogo.png")
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
        DefaultTableModel tablaMusicas = (DefaultTableModel) tabla.getModel();
        tablaMusicas.setRowCount(0);
        tabla.setRowHeight(100);

        for (Musica musica : musicas) {
            tablaMusicas.addRow(new Object[]{
                no++,
                musica.getNombre(),
                musica.getArtista(),
                musica.getAlbum(),
                musica.getGenero(),
                musica.formatearDuracion(), // 03:45
                musica.formatearTamanio(), // 5.2 MB
                musica.getRuta(),
                musica.anioReal(),
                musica.getPortada() // ImageIcon real
            });
        }
    }

    private static String valorSeguro(String valor, String defecto) {
        if (valor == null || valor.isBlank()) {// Si viene null o vacio
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
}
