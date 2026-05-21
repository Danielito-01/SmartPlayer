package clase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class Administrador {
    
    public static List<File> seleccionarArchivos(java.awt.Component parent) {
        JFileChooser chooser = new JFileChooser(); // Crea el selector de archivos
        chooser.setDialogTitle("Selecciona archivos MP3 o carpetas"); // Titulo de la ventana
        chooser.setMultiSelectionEnabled(true); // Permite seleccionar varios elementos
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // Acepta archivos y carpetas
        chooser.setAcceptAllFileFilterUsed(false); // Evita mostrar todo sin filtro

        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || esMp3(f); // Muestra carpetas o archivos mp3
            }

            @Override
            public String getDescription() {
                return "Archivos MP3 y carpetas"; // Texto del filtro
            }
        });

        int result = chooser.showOpenDialog(parent); // Abre la ventana
        if (result != JFileChooser.APPROVE_OPTION) {
            return new ArrayList<>(); // Si cancelo, devuelve lista vacía
        }

        Set<String> rutasUnicas = new LinkedHashSet<>(); // Guarda rutas sin duplicados
        File[] seleccionados = chooser.getSelectedFiles(); // Obtiene lo que eligio el usuario

        for (File f : seleccionados) {
            if (f.isDirectory()) {
                buscarEnCarpetas(f, rutasUnicas); // Si es carpeta, busca dentro recursivamente
            } else if (f.isFile() && esMp3(f)) {
                agregarRutaUnica(f, rutasUnicas); // Si es mp3, lo agrega directo
            }
        }

        List<File> mp3s = new ArrayList<>(); // Lista final para devolver
        for (String ruta : rutasUnicas) {
            mp3s.add(new File(ruta)); // Convierte cada ruta a File
        }

        return mp3s; // Devuelve todos los mp3 encontrados
    }

    private static void buscarEnCarpetas(File directorio, Set<String> rutasUnicas) {
        File[] archivos = directorio.listFiles(); // Lista el contenido de la carpeta
        if (archivos == null) return; // Si no puede leerla, sale

        for (File f : archivos) {
            if (f.isDirectory()) {
                buscarEnCarpetas(f, rutasUnicas); // Vuelve a entrar si es subcarpeta
            } else if (f.isFile() && esMp3(f)) {
                agregarRutaUnica(f, rutasUnicas); // Agrega solo archivos .mp3
            }
        }
    }

    private static void agregarRutaUnica(File file, Set<String> rutasUnicas) {
        try {
            rutasUnicas.add(file.getCanonicalPath()); // Usa ruta canonica para evitar duplicados
            System.out.println(rutasUnicas);
        } catch (IOException e) {
            rutasUnicas.add(file.getAbsolutePath()); // Si falla, usa la absoluta
        }
    }

    private static boolean esMp3(File f) {
        return f != null && f.isFile() && f.getName().toLowerCase().endsWith(".mp3"); // Verifica extensión
    }  
    
    
}
