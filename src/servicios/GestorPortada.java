package servicios;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import modelos.Musica;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

public class GestorPortada {

    private static ImageIcon portadaPorDefectoGrande;

    private GestorPortada() {
    }

    public static ImageIcon obtenerPortadaGrande(Musica musica, int ancho, int alto) {
        if (musica == null || musica.getRuta() == null || musica.getRuta().isBlank()) {
            return obtenerPortadaPorDefecto(ancho, alto);
        }

        try {
            File archivo = new File(musica.getRuta());

            if (!archivo.exists()) {
                return obtenerPortadaPorDefecto(ancho, alto);
            }

            AudioFile audioFile = AudioFileIO.read(archivo);
            Tag tag = audioFile.getTag();

            if (tag == null) {
                return obtenerPortadaPorDefecto(ancho, alto);
            }

            Artwork artwork = tag.getFirstArtwork();

            if (artwork == null || artwork.getBinaryData() == null) {
                return obtenerPortadaPorDefecto(ancho, alto);
            }

            BufferedImage imagen = ImageIO.read(
                    new ByteArrayInputStream(artwork.getBinaryData())
            );

            if (imagen == null) {
                return obtenerPortadaPorDefecto(ancho, alto);
            }

            BufferedImage imagenEscalada = escalarImagen(imagen, ancho, alto);
            imagen.flush();

            return new ImageIcon(imagenEscalada);

        } catch (Exception e) {
            return obtenerPortadaPorDefecto(ancho, alto);
        }
    }

    private static ImageIcon obtenerPortadaPorDefecto(int ancho, int alto) {
        if (portadaPorDefectoGrande != null) {
            return portadaPorDefectoGrande;
        }

        try {
            BufferedImage imagen = ImageIO.read(
                    GestorPortada.class.getResource("/recursos/SmartPlayerLogo.png")
            );

            if (imagen != null) {
                BufferedImage imagenEscalada = escalarImagen(imagen, ancho, alto);
                imagen.flush();

                portadaPorDefectoGrande = new ImageIcon(imagenEscalada);
                return portadaPorDefectoGrande;
            }

        } catch (Exception e) {
        }

        portadaPorDefectoGrande = new ImageIcon();
        return portadaPorDefectoGrande;
    }

    private static BufferedImage escalarImagen(BufferedImage imagenOriginal, int ancho, int alto) {
        BufferedImage imagenEscalada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = imagenEscalada.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(imagenOriginal, 0, 0, ancho, alto, null);
        g2d.dispose();

        return imagenEscalada;
    }
}