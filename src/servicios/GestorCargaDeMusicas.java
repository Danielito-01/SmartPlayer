package servicios;

import modelos.Musica;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class GestorCargaDeMusicas {

    public static List<File> seleccionarArchivos(java.awt.Component parent) {
        JFileChooser explorador = new JFileChooser();
        explorador.setMultiSelectionEnabled(true);
        explorador.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        explorador.setAcceptAllFileFilterUsed(true);

        explorador.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File archivo) {
                return archivo.isDirectory() || esMp3(archivo);
            }

            @Override
            public String getDescription() {
                return "Archivos MP3 (*.mp3)";
            }
        });

        int resultado = explorador.showOpenDialog(parent);
        if (resultado != JFileChooser.APPROVE_OPTION) {
            return new ArrayList<>();
        }

        Set<String> rutasUnicas = new LinkedHashSet<>();
        File[] archivosSeleccionados = explorador.getSelectedFiles();
        for (File archivo : archivosSeleccionados) {
            if (archivo == null) {
                continue;
            }
            if (archivo.isDirectory()) {
                buscarEnCarpetas(archivo, rutasUnicas);
            } else if (archivo.isFile() && esMp3(archivo)) {
                agregarRutaUnica(archivo, rutasUnicas);
            }
        }

        List<File> archivosMp3 = new ArrayList<>();
        for (String ruta : rutasUnicas) {
            archivosMp3.add(new File(ruta));
        }
        return archivosMp3;
    }
    
    private static void buscarEnCarpetas(File directorio, Set<String> rutasUnicas) {
        File[] archivos = directorio.listFiles(); // Obtiene el contenido de la carpeta
     
        if (archivos == null) return;

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
        }catch (IOException e) {
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
                int duracion = 0;
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

                    String anioS = audio.getFirst(FieldKey.YEAR);
                    anio = extraerAnio(anioS);
                }
                Musica musica = new Musica(0, nombre, artista, album, genero, duracion, tamanio, rutaCanonica, anio); // Crea objeto audio
                musicas.add(musica);// Agrega a lista
            }catch (CannotReadException |
                   IOException |
                   TagException |
                   ReadOnlyFileException |
                   InvalidAudioFrameException e) {
            }
        }
        return musicas;
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
    
    private static int extraerAnio(String textoAnio) {
        if (textoAnio == null || textoAnio.isBlank()) {
            return 0;
        }

        String soloNumeros = textoAnio.replaceAll("[^0-9]", "");
        if (soloNumeros.length() < 4) {
            return 0;
        }
        try {
            return Integer.parseInt(soloNumeros.substring(0, 4));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}