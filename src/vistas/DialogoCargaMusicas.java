package vistas;

import servicios.GestorCargaDeMusicas;
import modelos.Musica;
import java.io.File;
import java.util.List;
import estructuras.BibliotecaGeneral;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import utilidades.Presentacion;
import utilidades.Tabla;

public class DialogoCargaMusicas extends javax.swing.JDialog {
    private final BibliotecaGeneral biblioteca = BibliotecaGeneral.getInstance();
    private List<Musica> musicasCargadas = new ArrayList<>();

    public DialogoCargaMusicas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configurarTabla();
        Presentacion.aplicarCargaMusicas(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scpMusicasCargadas = new javax.swing.JScrollPane();
        tblMusicasCargadas = new javax.swing.JTable();
        btnCargar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        lblCargandoMusicas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setForeground(new java.awt.Color(204, 255, 255));

        tblMusicasCargadas.setBackground(new java.awt.Color(0, 93, 232));
        tblMusicasCargadas.setForeground(new java.awt.Color(255, 255, 255));
        tblMusicasCargadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Nombre", "Artista", "Album", "Genero", "Duracion", "Tamaño", "Ruta", "Año", "Id"
            }
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }
    );
    tblMusicasCargadas.setRowHeight(100);
    tblMusicasCargadas.getColumnModel().getColumn(0).setResizable(false);
    scpMusicasCargadas.setViewportView(tblMusicasCargadas);

    btnCargar.setBackground(new java.awt.Color(250, 249, 249));
    btnCargar.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
    btnCargar.setForeground(new java.awt.Color(0, 0, 153));
    btnCargar.setText("Cargar");
    btnCargar.setBorderPainted(false);
    btnCargar.setOpaque(true);
    btnCargar.addActionListener(this::btnCargarActionPerformed);

    btnGuardar.setBackground(new java.awt.Color(250, 249, 249));
    btnGuardar.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
    btnGuardar.setForeground(new java.awt.Color(0, 0, 153));
    btnGuardar.setText("Guardar");
    btnGuardar.setBorderPainted(false);
    btnGuardar.setOpaque(true);
    btnGuardar.addActionListener(this::btnGuardarActionPerformed);

    lblCargandoMusicas.setFont(new java.awt.Font("Segoe UI Black", 3, 36)); // NOI18N
    lblCargandoMusicas.setForeground(new java.awt.Color(51, 51, 51));
    lblCargandoMusicas.setText("Cargando Musicas");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap(12, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(scpMusicasCargadas, javax.swing.GroupLayout.PREFERRED_SIZE, 1126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnCargar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCargandoMusicas, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnGuardar)))
            .addContainerGap(14, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCargar)
                        .addComponent(btnGuardar))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblCargandoMusicas)
                    .addGap(14, 14, 14)))
            .addComponent(scpMusicasCargadas, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(12, 12, 12))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarTabla() {
        Tabla.ocultarColumna(tblMusicasCargadas, 9);
        Tabla.establecerAnchoMaximo(tblMusicasCargadas, 0, 45);
        Tabla.establecerAnchoMaximo(tblMusicasCargadas, 5, 60);
        Tabla.establecerAnchoMaximo(tblMusicasCargadas, 6, 80);
    }
    
    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        List<File> mp3s = GestorCargaDeMusicas.seleccionarArchivos(this);
        musicasCargadas = GestorCargaDeMusicas.extraerDatosDeMusicas(mp3s);
        Tabla.cargarMusicasConDetalles(tblMusicasCargadas, musicasCargadas);
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (musicasCargadas == null || musicasCargadas.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay músicas cargadas para guardar.",
                    "Biblioteca",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String reporte = biblioteca.insertarMusicas(musicasCargadas);

        JOptionPane.showMessageDialog(
                this,
                reporte,
                "Biblioteca",
                JOptionPane.INFORMATION_MESSAGE
        );

        musicasCargadas.clear();
        Tabla.cargarMusicasConDetalles(tblMusicasCargadas, musicasCargadas);
        dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel lblCargandoMusicas;
    private javax.swing.JScrollPane scpMusicasCargadas;
    private javax.swing.JTable tblMusicasCargadas;
    // End of variables declaration//GEN-END:variables
}