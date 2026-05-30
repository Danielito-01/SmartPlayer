package utilidades;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicSliderUI;

public class SliderHelper {

    public static void configurar(JSlider slider, JPanel panel) {
        slider.setOpaque(true);
        slider.setBackground(panel.getBackground());
        slider.setFocusable(false);
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);
        slider.setBorder(null);

        slider.setUI(new BasicSliderUI(slider) {
            @Override
            public void paintFocus(Graphics g) {
            }
            @Override
            public void setThumbLocation(int x, int y) {
                super.setThumbLocation(x, y);
                slider.repaint();
            }
            @Override
            public void paintTrack(Graphics g) {

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                int y = trackRect.y + trackRect.height / 2 - 2;
                int alto = 4;
                g2d.setColor(new java.awt.Color(220, 220, 220));
                g2d.fillRoundRect(
                        trackRect.x,
                        y,
                        trackRect.width,
                        alto,
                        8,
                        8
                );

                int valorMinimo = slider.getMinimum();
                int valorMaximo = slider.getMaximum();
                int valorActual = slider.getValue();
                int anchoProgreso = 0;

                if (valorMaximo > valorMinimo) {
                    double porcentaje =
                            (double) (valorActual - valorMinimo)
                            / (valorMaximo - valorMinimo);
                    anchoProgreso =
                            (int) (trackRect.width * porcentaje);
                }

                g2d.setColor(new java.awt.Color(0, 122, 255));
                g2d.fillRoundRect(
                        trackRect.x,
                        y,
                        anchoProgreso,
                        alto,
                        8,
                        8
                );
            }
            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );
                g2d.setColor(new java.awt.Color(0, 122, 255));

                int diametro = 16;
                int x =
                        thumbRect.x
                        + (thumbRect.width - diametro) / 2;
                int y =
                        thumbRect.y
                        + (thumbRect.height - diametro) / 2;
                g2d.fillOval(
                        x,
                        y,
                        diametro,
                        diametro
                );
            }
        });
    }
}