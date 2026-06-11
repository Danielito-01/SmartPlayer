package utilidades;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.net.URI;
import javax.swing.JPanel;

public class PanelSvgZoom extends JPanel {

    private final SVGUniverse universo;
    private SVGDiagram diagrama;
    private double escala;

    public PanelSvgZoom() {
        this.universo = new SVGUniverse();
        this.diagrama = null;
        this.escala = 1.0;
        setOpaque(true);
    }

    public void setSvg(File archivoSvg) throws Exception {
        if (archivoSvg == null || !archivoSvg.exists()) {
            throw new IllegalArgumentException("El archivo SVG no existe.");
        }

        URI uri = universo.loadSVG(archivoSvg.toURI().toURL().openStream(), archivoSvg.getName());
        diagrama = universo.getDiagram(uri);

        if (diagrama == null) {
            throw new IllegalStateException("No se pudo cargar el diagrama SVG.");
        }

        diagrama.setIgnoringClipHeuristic(true);
        escala = 1.0;
        actualizarTamanio();
        repaint();
    }

    public void setEscala(double escala) {
        if (escala < 0.001) {
            escala = 0.001;
        }
        if (escala > 5.0) {
            escala = 5.0;
        }

        this.escala = escala;
        actualizarTamanio();
        repaint();
    }

    public double getEscala() {
        return escala;
    }

    private void actualizarTamanio() {
        if (diagrama == null) {
            setPreferredSize(new Dimension(300, 300));
        } else {
            int ancho = Math.max(1, (int) Math.round(diagrama.getWidth() * escala));
            int alto = Math.max(1, (int) Math.round(diagrama.getHeight() * escala));
            setPreferredSize(new Dimension(ancho, alto));
        }

        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (diagrama == null) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );
        g2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC
        );

        g2.scale(escala, escala);

        try {
            diagrama.render(g2);
        } catch (SVGException e) {
            e.printStackTrace();
        }

        g2.dispose();
    }
    
    public double getAnchoOriginal() {
        if (diagrama == null) {
            return 0;
        }

        return diagrama.getWidth();
    }

    public double getAltoOriginal() {
        if (diagrama == null) {
            return 0;
        }

        return diagrama.getHeight();
    }
}