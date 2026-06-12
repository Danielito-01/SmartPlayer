package vistas;

import estructuras.BibliotecaGeneral;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;
import servicios.GestorGraphviz;
import utilidades.PanelSvgZoom;
import utilidades.Presentacion;


public class DialogoVisualizacionArboles extends javax.swing.JDialog {
    private final BibliotecaGeneral biblioteca = BibliotecaGeneral.getInstance();
    private final PanelSvgZoom panelABB = new PanelSvgZoom();
    private final PanelSvgZoom panelAVL = new PanelSvgZoom();

    public DialogoVisualizacionArboles(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblArboles = new javax.swing.JLabel();
        scpABB = new javax.swing.JScrollPane();
        panABB = new javax.swing.JPanel();
        scpAVL = new javax.swing.JScrollPane();
        panAVL = new javax.swing.JPanel();
        sldZoom = new javax.swing.JSlider();
        lblZoom = new javax.swing.JLabel();
        lblRaizABB = new javax.swing.JLabel();
        lblCantidadABB = new javax.swing.JLabel();
        lblRaizAVL = new javax.swing.JLabel();
        lblCantidadAVL = new javax.swing.JLabel();
        lblArboles1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblArboles.setFont(new java.awt.Font("Segoe UI Black", 3, 36)); // NOI18N
        lblArboles.setForeground(new java.awt.Color(51, 51, 51));
        lblArboles.setText("ABB");

        javax.swing.GroupLayout panABBLayout = new javax.swing.GroupLayout(panABB);
        panABB.setLayout(panABBLayout);
        panABBLayout.setHorizontalGroup(
            panABBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        panABBLayout.setVerticalGroup(
            panABBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        scpABB.setViewportView(panABB);

        javax.swing.GroupLayout panAVLLayout = new javax.swing.GroupLayout(panAVL);
        panAVL.setLayout(panAVLLayout);
        panAVLLayout.setHorizontalGroup(
            panAVLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );
        panAVLLayout.setVerticalGroup(
            panAVLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        scpAVL.setViewportView(panAVL);

        sldZoom.setMaximum(300);
        sldZoom.setMinimum(10);
        sldZoom.setValue(150);

        lblZoom.setText("Zoom");

        lblRaizABB.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblRaizABB.setForeground(new java.awt.Color(51, 51, 51));
        lblRaizABB.setText("Raiz: NULL");

        lblCantidadABB.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblCantidadABB.setForeground(new java.awt.Color(51, 51, 51));
        lblCantidadABB.setText("Cantidad: 0");

        lblRaizAVL.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblRaizAVL.setText("Raiz: NULL");

        lblCantidadAVL.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblCantidadAVL.setText("Cantidad: 0");

        lblArboles1.setFont(new java.awt.Font("Segoe UI Black", 3, 36)); // NOI18N
        lblArboles1.setForeground(new java.awt.Color(51, 51, 51));
        lblArboles1.setText("AVL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRaizABB, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCantidadABB, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scpABB, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRaizAVL, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCantidadAVL, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scpAVL, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(lblZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sldZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(lblArboles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblArboles1)
                .addGap(219, 219, 219))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblArboles1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArboles, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblRaizABB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCantidadABB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRaizAVL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblCantidadAVL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scpABB, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scpAVL, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblZoom)
                    .addComponent(sldZoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void inicializar() {
        Presentacion.aplicarVisualizacionArboles(this);
        setLocationRelativeTo(getParent());
        scpABB.setViewportView(panelABB);
        scpAVL.setViewportView(panelAVL);
        generarYMostrarArboles();
        configurarZoom();
    }
    
    private void configurarZoom() {
        sldZoom.setMinimum(1);      // 0.1%
        sldZoom.setMaximum(3000);   // 300%
        sldZoom.setValue(1000);     // 100%

        lblZoom.setText("Zoom: 100%");

        sldZoom.addChangeListener(e -> {
            double escala = sldZoom.getValue() / 1000.0;

            panelABB.setEscala(escala);
            panelAVL.setEscala(escala);

            lblZoom.setText(String.format("Zoom: %.1f%%", escala * 100));
        });
    }
    
    public void mostrarImagenes(File imagenABB, File imagenAVL) {
        try {
            panelABB.setSvg(imagenABB);
            panelAVL.setSvg(imagenAVL);

            javax.swing.SwingUtilities.invokeLater(() -> {
                ajustarArbolesAlEspacio();
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudieron cargar los SVG.\n" + e.getMessage()
            );
        }
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

        return archivoSvg;
    }
    
    private void generarYMostrarArboles() {
        try {
            String dotABB = biblioteca.getAbb().generarDot();
            String dotAVL = biblioteca.getAvl().generarDot();

            File imagenABB = GestorGraphviz.generarSvgDesdeDot(dotABB, "arbol_abb");
            File imagenAVL = GestorGraphviz.generarSvgDesdeDot(dotAVL, "arbol_avl");

            mostrarImagenes(imagenABB, imagenAVL);
            if (biblioteca.getAvl().getRaiz() !=null && biblioteca.getAbb().getRaiz() != null) {
            lblRaizABB.setText("Raiz: " + biblioteca.getAbb().getRaiz().getNombre());
            lblCantidadABB.setText("Cantidad: " + biblioteca.getAbb().getCantidad());
            lblRaizAVL.setText("Raiz: " + biblioteca.getAvl().getRaiz().getNombre());
            lblCantidadAVL.setText("Cantidad: " + biblioteca.getAvl().getCantidad());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudieron generar los árboles.\n\n" + e.getMessage());
        }
    }
    
    private void ajustarArbolesAlEspacio() {
        double escalaABB = calcularEscalaParaAjustar(panelABB, scpABB);
        double escalaAVL = calcularEscalaParaAjustar(panelAVL, scpAVL);

        double escalaFinal = Math.min(escalaABB, escalaAVL);

        if (escalaFinal <= 0) {
            return;
        }

        int valorSlider = (int) Math.round(escalaFinal * 1000);

        valorSlider = Math.max(sldZoom.getMinimum(), Math.min(sldZoom.getMaximum(), valorSlider));

        sldZoom.setValue(valorSlider);
    }
    
    private double calcularEscalaParaAjustar(PanelSvgZoom panel, javax.swing.JScrollPane scroll) {
        double anchoOriginal = panel.getAnchoOriginal();
        double altoOriginal = panel.getAltoOriginal();

        if (anchoOriginal <= 0 || altoOriginal <= 0) {
            return 0;
        }

        java.awt.Dimension areaVisible = scroll.getViewport().getExtentSize();

        double escalaAncho = areaVisible.getWidth() / anchoOriginal;
        double escalaAlto = areaVisible.getHeight() / altoOriginal;

        return Math.min(escalaAncho, escalaAlto) * 0.95;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblArboles;
    private javax.swing.JLabel lblArboles1;
    private javax.swing.JLabel lblCantidadABB;
    private javax.swing.JLabel lblCantidadAVL;
    private javax.swing.JLabel lblRaizABB;
    private javax.swing.JLabel lblRaizAVL;
    private javax.swing.JLabel lblZoom;
    private javax.swing.JPanel panABB;
    private javax.swing.JPanel panAVL;
    private javax.swing.JScrollPane scpABB;
    private javax.swing.JScrollPane scpAVL;
    private javax.swing.JSlider sldZoom;
    // End of variables declaration//GEN-END:variables
}
