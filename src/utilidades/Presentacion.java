package utilidades;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.lang.reflect.Field;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class Presentacion {

    private static final Color FONDO = new Color(241, 247, 255);

    private static final Color PANEL = new Color(252, 254, 255);
    private static final Color PANEL_SUAVE = new Color(235, 244, 255);
    private static final Color PANEL_REPRODUCTOR = new Color(228, 240, 255);
    private static final Color PANEL_INTERNO = new Color(255, 255, 255);

    private static final Color FILA_ALTERNA = new Color(245, 250, 255);

    private static final Color AZUL = new Color(74, 144, 245);
    private static final Color AZUL_HOVER = new Color(52, 120, 225);
    private static final Color AZUL_PRESS = new Color(34, 96, 196);
    private static final Color AZUL_OSCURO = new Color(30, 78, 160);

    private static final Color AZUL_SUAVE = new Color(224, 239, 255);
    private static final Color AZUL_SUAVE_HOVER = new Color(207, 228, 252);
    private static final Color AZUL_SUAVE_PRESS = new Color(188, 214, 245);

    private static final Color CONTROL = new Color(242, 248, 255);
    private static final Color CONTROL_HOVER = new Color(226, 239, 254);
    private static final Color CONTROL_PRESS = new Color(208, 226, 248);

    private static final Color TEXTO = new Color(20, 33, 55);
    private static final Color TEXTO_SUAVE = new Color(88, 104, 128);
    private static final Color TEXTO_BLANCO = Color.WHITE;

    private static final Color BORDE = new Color(190, 216, 248);
    private static final Color BORDE_SUAVE = new Color(219, 232, 247);

    private static final Color SLIDER_FONDO = new Color(210, 221, 235);

    private Presentacion() {
    }

    public static void aplicarVentanaPrincipal(JFrame ventana) {
        asignarNombresDesdeVariables(ventana);

        ventana.setTitle("SmartPlayer");
        ventana.setLocationRelativeTo(null);
        ventana.getContentPane().setBackground(FONDO);

        recorrerComponentes(ventana.getContentPane());

        if (ventana.getJMenuBar() != null) {
            estilizarMenu(ventana.getJMenuBar());
        }

        ventana.repaint();
    }

    public static void aplicarCargaMusicas(JDialog dialogo) {
        aplicarDialogo(dialogo, "Cargar músicas");
    }

    public static void aplicarNuevaPlaylist(JDialog dialogo) {
        aplicarDialogo(dialogo, "Nueva Playlist");
    }
    
    public static void aplicarPilaHistorial(JDialog dialogo) {
        aplicarDialogo(dialogo, "PilaHistorial");
    }
    
    public static void aplicarVisualizacionArboles(JDialog dialogo) {
        aplicarDialogo(dialogo, "Visualizacion de arboles");
    }
    
    public static void aplicarRecorridos(JDialog dialogo) {
        aplicarDialogo(dialogo, "Recorrido");
    }

    public static void refrescarToggle(JToggleButton toggle) {
        if (toggle.isSelected()) {
            toggle.setBackground(AZUL);
            toggle.setForeground(TEXTO_BLANCO);
            toggle.setBorder(new LineBorder(AZUL_OSCURO, 1, true));
        } else {
            toggle.setBackground(AZUL_SUAVE);
            toggle.setForeground(AZUL_OSCURO);
            toggle.setBorder(new LineBorder(BORDE, 1, true));
        }
    }

    private static void aplicarDialogo(JDialog dialogo, String titulo) {
        asignarNombresDesdeVariables(dialogo);

        if (titulo != null && !titulo.isBlank()) {
            dialogo.setTitle(titulo);
        }

        dialogo.setLocationRelativeTo(null);
        dialogo.getContentPane().setBackground(FONDO);

        recorrerComponentes(dialogo.getContentPane());
        dialogo.repaint();
    }

    private static void asignarNombresDesdeVariables(Object vista) {
        Field[] campos = vista.getClass().getDeclaredFields();

        for (Field campo : campos) {
            try {
                campo.setAccessible(true);
                Object valor = campo.get(vista);

                if (valor instanceof Component componente) {
                    if (componente.getName() == null || componente.getName().isBlank()) {
                        componente.setName(campo.getName());
                    }
                }
            } catch (IllegalAccessException | SecurityException e) {
            }
        }
    }

    private static void recorrerComponentes(Component componente) {
        if (componente instanceof JPanel panel) {
            estilizarPanel(panel);
        }

        if (componente instanceof JToggleButton toggle) {
            estilizarToggle(toggle);
        } else if (componente instanceof JButton boton) {
            estilizarBoton(boton);
        }

        if (componente instanceof JLabel label) {
            estilizarLabel(label);
        }

        if (componente instanceof JTextField campoTexto) {
            estilizarCampoTexto(campoTexto);
        }

        if (componente instanceof JTable tabla) {
            estilizarTabla(tabla);
        }

        if (componente instanceof JScrollPane scrollPane) {
            estilizarScrollPane(scrollPane);
        }

        if (componente instanceof JSlider slider) {
            estilizarSlider(slider);
        }

        if (componente instanceof Container contenedor) {
            for (Component hijo : contenedor.getComponents()) {
                recorrerComponentes(hijo);
            }
        }
    }

    private static void estilizarPanel(JPanel panel) {
        panel.setOpaque(true);

        if (es(panel, "panReproduccion")) {
            panel.setBackground(PANEL_REPRODUCTOR);
            panel.setBorder(new LineBorder(BORDE, 1, true));
            return;
        }

        if (es(panel, "panCanciones", "panPlaylist")) {
            panel.setBackground(PANEL_SUAVE);
            panel.setBorder(new LineBorder(BORDE, 1, true));
            return;
        }

        panel.setBackground(PANEL);
        panel.setBorder(new LineBorder(BORDE_SUAVE, 1, true));
    }

    private static void estilizarBoton(JButton boton) {
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setRolloverEnabled(true);
        boton.putClientProperty("JButton.buttonType", "roundRect");

        if (es(boton,
                "btnCargar",
                "btnGuardar",
                "btnAgregar",
                "btnAceptar",
                "btnBiblioteca",
                "btnReproducirLista",
                "btnPlayPausa")
                || nombreContiene(boton, "cargar")
                || nombreContiene(boton, "guardar")
                || nombreContiene(boton, "agregar")
                || nombreContiene(boton, "aceptar")) {

            aplicarEstiloBoton(
                    boton,
                    AZUL,
                    AZUL_HOVER,
                    AZUL_PRESS,
                    TEXTO_BLANCO,
                    AZUL_OSCURO
            );
            return;
        }

        if (es(boton, "btnBuscar")
                || nombreContiene(boton, "buscar")) {

            aplicarEstiloBoton(
                    boton,
                    AZUL_SUAVE,
                    AZUL_SUAVE_HOVER,
                    AZUL_SUAVE_PRESS,
                    AZUL_OSCURO,
                    BORDE
            );
            return;
        }

        if (es(boton, "btnAnterior", "btnSiguiente")) {
            aplicarEstiloBoton(
                    boton,
                    AZUL_SUAVE,
                    AZUL_SUAVE_HOVER,
                    AZUL_SUAVE_PRESS,
                    AZUL_OSCURO,
                    BORDE
            );
            return;
        }

        if (es(boton, "btnCancelar", "btnCerrar", "btnVolver")
                || nombreContiene(boton, "cancelar")
                || nombreContiene(boton, "cerrar")
                || nombreContiene(boton, "volver")) {

            aplicarEstiloBoton(
                    boton,
                    CONTROL,
                    CONTROL_HOVER,
                    CONTROL_PRESS,
                    TEXTO,
                    BORDE
            );
            return;
        }

        aplicarEstiloBoton(
                boton,
                CONTROL,
                CONTROL_HOVER,
                CONTROL_PRESS,
                TEXTO,
                BORDE
        );
    }

    private static void aplicarEstiloBoton(
            JButton boton,
            Color normal,
            Color hover,
            Color press,
            Color texto,
            Color borde
    ) {
        boton.setForeground(texto);
        boton.setBackground(normal);

        boton.putClientProperty("presentacion.normal", normal);
        boton.putClientProperty("presentacion.hover", hover);
        boton.putClientProperty("presentacion.press", press);
        boton.putClientProperty("presentacion.borde", borde);

        boton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                AbstractButton b = (AbstractButton) c;
                ButtonModel modelo = b.getModel();

                Color colorNormal = (Color) b.getClientProperty("presentacion.normal");
                Color colorHover = (Color) b.getClientProperty("presentacion.hover");
                Color colorPress = (Color) b.getClientProperty("presentacion.press");
                Color colorBorde = (Color) b.getClientProperty("presentacion.borde");

                Color fondo = colorNormal;

                if (!b.isEnabled()) {
                    fondo = new Color(226, 232, 240);
                    colorBorde = new Color(203, 213, 225);
                } else if (modelo.isPressed()) {
                    fondo = colorPress;
                } else if (modelo.isRollover()) {
                    fondo = colorHover;
                }

                Graphics2D g2d = (Graphics2D) g.create();

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                int ancho = c.getWidth();
                int alto = c.getHeight();
                int arco = Math.min(18, Math.max(10, alto));

                g2d.setColor(fondo);
                g2d.fillRoundRect(0, 0, ancho - 1, alto - 1, arco, arco);

                g2d.setColor(colorBorde);
                g2d.drawRoundRect(0, 0, ancho - 1, alto - 1, arco, arco);

                g2d.dispose();

                super.paint(g, c);
            }
        });
    }

    private static void estilizarToggle(JToggleButton toggle) {
        toggle.setFocusPainted(false);
        toggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggle.setOpaque(true);
        toggle.setBorderPainted(true);
        toggle.putClientProperty("JButton.buttonType", "roundRect");

        refrescarToggle(toggle);
    }

    private static void estilizarLabel(JLabel label) {
        label.setForeground(TEXTO);

        if (es(label, "lblTituloLista", "lblCola")) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            return;
        }

        if (es(label, "lblNombreMusica")) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.TOP);
            return;
        }

        if (es(label, "lblNombrePlaylist", "lblTituloPlaylist", "lblNuevaPlaylist", "lblPilaHistorial", "lblCargandoMusicas")
                || nombreContiene(label, "nombreplaylist")
                || nombreContiene(label, "nuevaplaylist")) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            return;
        }

        if (nombreEmpieza(label, "lblTxt")) {
            label.setForeground(TEXTO_SUAVE);
            label.setVerticalAlignment(SwingConstants.CENTER);
            return;
        }

        if (es(label, "lblTiempoActual", "lblDuracion")) {
            label.setForeground(TEXTO_SUAVE);
            label.setVerticalAlignment(SwingConstants.CENTER);
            return;
        }

        if (es(label, "lblPortada")) {
            label.setOpaque(true);
            label.setBackground(PANEL_INTERNO);
            label.setBorder(new LineBorder(BORDE_SUAVE, 1, true));
        }
    }

    private static void estilizarCampoTexto(JTextField campoTexto) {
        campoTexto.setBackground(PANEL_INTERNO);
        campoTexto.setForeground(TEXTO);
        campoTexto.setCaretColor(AZUL);
        campoTexto.setBorder(new LineBorder(BORDE, 1, true));

        if (es(campoTexto, "txtNombre", "txtNombrePlaylist", "txtPlaylist")
                || nombreContiene(campoTexto, "nombreplaylist")
                || nombreContiene(campoTexto, "playlist")) {
            campoTexto.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private static void estilizarTabla(JTable tabla) {
        tabla.setBackground(PANEL_INTERNO);
        tabla.setForeground(TEXTO);
        tabla.setSelectionBackground(AZUL);
        tabla.setSelectionForeground(TEXTO_BLANCO);
        tabla.setGridColor(BORDE_SUAVE);
        tabla.setShowGrid(false);
        tabla.setFillsViewportHeight(true);

        JTableHeader header = tabla.getTableHeader();

        if (header != null) {
            header.setBackground(PANEL_SUAVE);
            header.setForeground(TEXTO);
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

                if (isSelected) {
                    componente.setBackground(AZUL);
                    componente.setForeground(TEXTO_BLANCO);
                } else {
                    componente.setBackground(row % 2 == 0 ? PANEL_INTERNO : FILA_ALTERNA);
                    componente.setForeground(TEXTO);
                }

                return componente;
            }
        });
    }

    private static void estilizarScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(new LineBorder(BORDE, 1, true));
        scrollPane.getViewport().setBackground(PANEL_INTERNO);
    }

    private static void estilizarSlider(JSlider slider) {
        slider.setBackground(SLIDER_FONDO);
        slider.setForeground(AZUL);
        slider.setCursor(new Cursor(Cursor.HAND_CURSOR));
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);
        slider.setFocusable(false);
    }

    private static void estilizarMenu(JMenuBar menuBar) {
        menuBar.setBackground(PANEL);
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDE));

        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);

            if (menu != null) {
                menu.setOpaque(true);
                menu.setBackground(PANEL);
                menu.setForeground(TEXTO);

                for (int j = 0; j < menu.getItemCount(); j++) {
                    JMenuItem item = menu.getItem(j);

                    if (item != null) {
                        item.setOpaque(true);
                        item.setBackground(PANEL_INTERNO);
                        item.setForeground(TEXTO);
                    }
                }
            }
        }
    }

    private static boolean es(Component componente, String... nombres) {
        String nombre = componente.getName();

        if (nombre == null) {
            return false;
        }

        for (String n : nombres) {
            if (nombre.equals(n)) {
                return true;
            }
        }

        return false;
    }

    private static boolean nombreEmpieza(Component componente, String inicio) {
        String nombre = componente.getName();
        return nombre != null && nombre.startsWith(inicio);
    }

    private static boolean nombreContiene(Component componente, String texto) {
        String nombre = componente.getName();

        if (nombre == null || texto == null) {
            return false;
        }

        return nombre.toLowerCase().contains(texto.toLowerCase());
    }
}