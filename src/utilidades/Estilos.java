package utilidades;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class Estilos {

    private static final Color FONDO = new Color(245, 248, 255);
    private static final Color PANEL = new Color(255, 255, 255);
    private static final Color PANEL_AZUL = new Color(235, 243, 255);
    private static final Color FILA_ALTERNA = new Color(242, 247, 255);
    private static final Color CONTROL = new Color(225, 237, 255);

    private static final Color AZUL_PRINCIPAL = new Color(37, 99, 235);
    private static final Color AZUL_SECUNDARIO = new Color(0, 122, 255);

    private static final Color TEXTO = new Color(20, 33, 61);
    private static final Color TEXTO_SUAVE = new Color(86, 99, 120);
    private static final Color BORDE = new Color(205, 220, 245);

    private Estilos() {
    }

    public static void aplicar(JFrame ventana) {
        ventana.setTitle("SmartPlayer");
        ventana.setLocationRelativeTo(null);
        ventana.setMinimumSize(new Dimension(980, 690));
        ventana.getContentPane().setBackground(FONDO);

        recorrerComponentes(ventana.getContentPane());

        if (ventana.getJMenuBar() != null) {
            estilizarMenu(ventana.getJMenuBar());
        }

        SwingUtilities.updateComponentTreeUI(ventana);
        ventana.revalidate();
        ventana.repaint();
    }

    public static void aplicar(JDialog dialogo) {
        dialogo.setTitle("Cargar músicas");
        dialogo.setLocationRelativeTo(null);
        dialogo.getContentPane().setBackground(FONDO);

        recorrerComponentes(dialogo.getContentPane());

        SwingUtilities.updateComponentTreeUI(dialogo);
        dialogo.revalidate();
        dialogo.repaint();
    }

    /*
     * Este método evita que el panel del reproductor "brinque" o se mueva
     * cuando cambian los textos de los JLabel o la imagen de portada.
     */
    public static void fijarTamaniosDelPanel(Component panel) {
        SwingUtilities.invokeLater(() -> {
            congelarTamanios(panel);

            if (panel instanceof JComponent componente) {
                componente.revalidate();
                componente.repaint();
            }
        });
    }

    private static void congelarTamanios(Component componente) {
        if (componente instanceof JComponent jComponent) {
            Dimension dimension = obtenerDimensionActual(jComponent);

            if (dimension.width > 0 && dimension.height > 0) {
                jComponent.setMinimumSize(dimension);
                jComponent.setPreferredSize(dimension);
                jComponent.setMaximumSize(dimension);
            }
        }

        if (componente instanceof Container contenedor) {
            for (Component hijo : contenedor.getComponents()) {
                congelarTamanios(hijo);
            }
        }
    }

    private static Dimension obtenerDimensionActual(JComponent componente) {
        int ancho = componente.getWidth();
        int alto = componente.getHeight();

        if (ancho <= 0 || alto <= 0) {
            Dimension preferido = componente.getPreferredSize();

            if (preferido != null) {
                ancho = preferido.width;
                alto = preferido.height;
            }
        }

        if (ancho <= 0 || alto <= 0) {
            Dimension minimo = componente.getMinimumSize();

            if (minimo != null) {
                ancho = minimo.width;
                alto = minimo.height;
            }
        }

        return new Dimension(ancho, alto);
    }

    private static void recorrerComponentes(Component componente) {
        if (componente instanceof JPanel) {
            estilizarPanel((JPanel) componente);
        }

        if (componente instanceof JToggleButton) {
            estilizarToggle((JToggleButton) componente);
        } else if (componente instanceof JButton) {
            estilizarBoton((JButton) componente);
        }

        if (componente instanceof JLabel) {
            estilizarLabel((JLabel) componente);
        }

        if (componente instanceof JTable) {
            estilizarTabla((JTable) componente);
        }

        if (componente instanceof JScrollPane) {
            estilizarScrollPane((JScrollPane) componente);
        }

        if (componente instanceof JSlider) {
            estilizarSlider((JSlider) componente);
        }

        if (componente instanceof Container) {
            Container contenedor = (Container) componente;

            for (Component hijo : contenedor.getComponents()) {
                recorrerComponentes(hijo);
            }
        }
    }

    private static void estilizarPanel(JPanel panel) {
        panel.setBackground(PANEL);
        panel.setBorder(BorderFactory.createEmptyBorder());
    }

    private static void estilizarBoton(JButton boton) {
        String texto = boton.getText() == null ? "" : boton.getText().trim();

        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(new EmptyBorder(8, 14, 8, 14));
        boton.setForeground(TEXTO);
        boton.setBackground(CONTROL);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.putClientProperty("JButton.buttonType", "roundRect");

        if (texto.equalsIgnoreCase("Biblioteca")) {
            boton.setBackground(AZUL_PRINCIPAL);
            boton.setForeground(Color.WHITE);
            boton.setFont(new Font("Segoe UI", Font.BOLD, 20));
            return;
        }

        if (texto.contains("Reproducir lista")) {
            boton.setBackground(AZUL_SECUNDARIO);
            boton.setForeground(Color.WHITE);
            boton.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
            return;
        }

        if (texto.equals("▶") || texto.equals("⏸") || texto.equals("⏸️")) {
            boton.setBackground(AZUL_PRINCIPAL);
            boton.setForeground(Color.WHITE);
            boton.setFont(new Font("Segoe UI Symbol", Font.BOLD, 26));
            return;
        }

        if (texto.equals("⏮") || texto.equals("⏭")) {
            boton.setBackground(CONTROL);
            boton.setForeground(AZUL_PRINCIPAL);
            boton.setFont(new Font("Segoe UI Symbol", Font.BOLD, 22));
        }
    }

    private static void estilizarToggle(JToggleButton toggle) {
        toggle.setFocusPainted(false);
        toggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggle.setBorder(new EmptyBorder(6, 10, 6, 10));
        toggle.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
        toggle.putClientProperty("JButton.buttonType", "roundRect");

        refrescarToggle(toggle);
    }

    public static void refrescarToggle(JToggleButton toggle) {
        if (toggle.isSelected()) {
            toggle.setBackground(AZUL_PRINCIPAL);
            toggle.setForeground(Color.WHITE);
        } else {
            toggle.setBackground(CONTROL);
            toggle.setForeground(AZUL_PRINCIPAL);
        }
    }

    private static void estilizarLabel(JLabel label) {
        String texto = label.getText() == null ? "" : label.getText();
        label.setForeground(TEXTO);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        label.setVerticalAlignment(SwingConstants.TOP);

        if (texto.equalsIgnoreCase("BIBLIOTECA")
                || texto.equalsIgnoreCase("COLA")
                || texto.startsWith("Playlist:")) {
            label.setForeground(TEXTO);
            label.setFont(new Font("Segoe UI", Font.BOLD, 24));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            return;
        }

        if (texto.contains("Nombre Musica")) {
            label.setForeground(TEXTO);
            label.setFont(new Font("Segoe UI", Font.BOLD, 26));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.TOP);
            return;
        }

        if (texto.contains("00:00")) {
            label.setForeground(TEXTO);
            label.setFont(new Font("Segoe UI", Font.BOLD, 10));
            label.setVerticalAlignment(SwingConstants.CENTER);
            return;
        }

        if (texto.endsWith(":")) {
            label.setForeground(TEXTO_SUAVE);
            label.setFont(new Font("Segoe UI", Font.BOLD, 18));
            label.setVerticalAlignment(SwingConstants.CENTER);
        }
    }

    private static void estilizarTabla(JTable tabla) {
        tabla.setBackground(PANEL);
        tabla.setForeground(TEXTO);
        tabla.setSelectionBackground(AZUL_PRINCIPAL);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setGridColor(PANEL);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setRowHeight(34);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setFillsViewportHeight(true);

        JTableHeader header = tabla.getTableHeader();

        if (header != null) {
            header.setBackground(PANEL_AZUL);
            header.setForeground(TEXTO);
            header.setFont(new Font("Segoe UI", Font.BOLD, 12));
            header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDE));
            header.setReorderingAllowed(false);
        }

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column
            ) {
                Component componente = super.getTableCellRendererComponent(
                        table,
                        value,
                        isSelected,
                        hasFocus,
                        row,
                        column
                );

                setBorder(new EmptyBorder(0, 10, 0, 10));

                if (isSelected) {
                    componente.setBackground(AZUL_PRINCIPAL);
                    componente.setForeground(Color.WHITE);
                } else {
                    if (row % 2 == 0) {
                        componente.setBackground(PANEL);
                    } else {
                        componente.setBackground(FILA_ALTERNA);
                    }

                    componente.setForeground(TEXTO);
                }

                return componente;
            }
        });
    }

    private static void estilizarScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDE, 1));
        scrollPane.getViewport().setBackground(PANEL);

        estilizarScrollBar(scrollPane.getVerticalScrollBar());
        estilizarScrollBar(scrollPane.getHorizontalScrollBar());
    }

    private static void estilizarScrollBar(JScrollBar scrollBar) {
        if (scrollBar == null) {
            return;
        }

        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(160, 190, 235);
                this.trackColor = PANEL_AZUL;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return crearBotonInvisible();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return crearBotonInvisible();
            }

            private JButton crearBotonInvisible() {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(0, 0));
                boton.setMinimumSize(new Dimension(0, 0));
                boton.setMaximumSize(new Dimension(0, 0));
                return boton;
            }
        });
    }

    private static void estilizarSlider(JSlider slider) {
        slider.setBackground(PANEL);
        slider.setForeground(AZUL_PRINCIPAL);
        slider.setCursor(new Cursor(Cursor.HAND_CURSOR));
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);
    }

    private static void estilizarMenu(JMenuBar menuBar) {
        menuBar.setBackground(PANEL);
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDE));

        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);

            if (menu != null) {
                menu.setForeground(TEXTO);
                menu.setFont(new Font("Segoe UI", Font.BOLD, 13));

                for (int j = 0; j < menu.getItemCount(); j++) {
                    JMenuItem item = menu.getItem(j);

                    if (item != null) {
                        item.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    }
                }
            }
        }
    }
    
    public static void fijarTamaniosDelPanelDespuesDeMostrar(JFrame ventana, Component panel) {
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                Timer timer = new Timer(250, evento -> {
                    fijarTamaniosDelPanel(panel);
                });

                timer.setRepeats(false);
                timer.start();
            }
        });
    }
}