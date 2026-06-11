package servicios;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class GestorGraphviz {

    private GestorGraphviz() {
    }

    public static File generarSvgDesdeDot(String dot, String nombreArchivo) throws Exception {
        if (dot == null || dot.isBlank()) {
            throw new IllegalArgumentException("El contenido DOT está vacío.");
        }

        File carpeta = new File("graphviz");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        File archivoSvg = new File(carpeta, nombreArchivo + ".svg");

        ByteArrayInputStream entrada = new ByteArrayInputStream(
                dot.getBytes(StandardCharsets.UTF_8)
        );

        MutableGraph grafo = new Parser().read(entrada);

        Graphviz.fromGraph(grafo)
                .totalMemory(536_870_912)
                .render(Format.SVG)
                .toFile(archivoSvg);
        
        limpiarSvgParaSwing(archivoSvg);
        return archivoSvg;
    }
    
    private static void limpiarSvgParaSwing(File archivoSvg) {
        try {
            String contenido = java.nio.file.Files.readString(
                    archivoSvg.toPath(),
                    java.nio.charset.StandardCharsets.UTF_8
            );

            contenido = contenido.replace("transparent", "none");

            java.nio.file.Files.writeString(
                    archivoSvg.toPath(),
                    contenido,
                    java.nio.charset.StandardCharsets.UTF_8
            );

        } catch (Exception e) {
            // Si no se puede limpiar, igual dejamos el SVG original.
        }
    }
}