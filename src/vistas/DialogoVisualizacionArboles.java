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

        scpInOrden = new javax.swing.JScrollPane();
        jtxtInOrden = new javax.swing.JTextArea();
        lblArboles = new javax.swing.JLabel();
        scpABB = new javax.swing.JScrollPane();
        panABB = new javax.swing.JPanel();
        scpAVL = new javax.swing.JScrollPane();
        panAVL = new javax.swing.JPanel();
        sldZoom = new javax.swing.JSlider();
        lblZoom = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtxtInOrden.setColumns(20);
        jtxtInOrden.setRows(5);
        scpInOrden.setViewportView(jtxtInOrden);

        lblArboles.setFont(new java.awt.Font("Segoe UI Black", 3, 36)); // NOI18N
        lblArboles.setForeground(new java.awt.Color(51, 51, 51));
        lblArboles.setText("ARBOLES");

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
        sldZoom.setValue(100);

        lblZoom.setText("Zoom");

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 3, 18)); // NOI18N
        jLabel1.setText("InOrden");

        jLabel2.setText("Raiz:");

        jLabel3.setText("Cantidad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scpInOrden)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scpABB, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scpAVL, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(lblZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sldZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(306, 306, 306)
                                .addComponent(lblArboles, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(455, 455, 455))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblArboles, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scpABB, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scpAVL, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblZoom))
                    .addComponent(sldZoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpInOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void inicializar() {
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

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudieron generar los árboles.\n\n" + e.getMessage()
            );
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextArea jtxtInOrden;
    private javax.swing.JLabel lblArboles;
    private javax.swing.JLabel lblZoom;
    private javax.swing.JPanel panABB;
    private javax.swing.JPanel panAVL;
    private javax.swing.JScrollPane scpABB;
    private javax.swing.JScrollPane scpAVL;
    private javax.swing.JScrollPane scpInOrden;
    private javax.swing.JSlider sldZoom;
    // End of variables declaration//GEN-END:variables
}
