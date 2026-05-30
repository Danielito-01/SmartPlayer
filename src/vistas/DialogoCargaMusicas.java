package vistas;

import servicios.GestorCargaMusicas;
import modelos.Musica;
import java.io.File;
import java.util.List;
import servicios.Biblioteca;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import utilidades.TablaHelper;

public class DialogoCargaMusicas extends javax.swing.JDialog {
    private final Biblioteca biblioteca = Biblioteca.getInstance();
    private List<Musica> musicasCargadas = new ArrayList<>();

    public DialogoCargaMusicas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configurarTabla();
        getContentPane().setBackground(new Color(153,204,255));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scpMusicasCargadas = new javax.swing.JScrollPane();
        tblMusicasCargadas = new javax.swing.JTable();
        btnCargar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setForeground(new java.awt.Color(204, 255, 255));

        tblMusicasCargadas.setBackground(new java.awt.Color(0, 93, 232));
        tblMusicasCargadas.setForeground(new java.awt.Color(255, 255, 255));
        scpMusicasCargadas.getViewport().setBackground(new Color(232,255,255));
        tblMusicasCargadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Nombre", "Artista", "Album", "Genero", "Duracion", "Tamaño", "Ruta", "Año", "Portada"
            }
        ){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 9){
                    return javax.swing.ImageIcon.class;
                }
                return Object.class;
            }

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
                    .addComponent(btnGuardar)))
            .addContainerGap(14, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnCargar)
                .addComponent(btnGuardar))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scpMusicasCargadas, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(15, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarTabla() {
        TablaHelper.establecerAnchoMaximo(tblMusicasCargadas, 0, 30);
        TablaHelper.establecerAnchoMaximo(tblMusicasCargadas, 5, 65);
        TablaHelper.establecerAnchoMaximo(tblMusicasCargadas, 6, 55);
        TablaHelper.establecerAnchoMaximo(tblMusicasCargadas, 8, 80);
        TablaHelper.establecerAnchoMaximo(tblMusicasCargadas, 9, 60);
    }
    
    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        List<File> mp3s = GestorCargaMusicas.seleccionarArchivos(this);
        musicasCargadas = GestorCargaMusicas.extraerDatosDeMusicas(mp3s);
        TablaHelper.cargarMusicasConDetalles(tblMusicasCargadas, musicasCargadas);
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (musicasCargadas.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay musicas cargadas para guardar",
                    "Biblioteca",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int agregadas = biblioteca.agregarSinRepetir(musicasCargadas);
        JOptionPane.showMessageDialog(
                this,
                "Guardadas: " + agregadas,
                "Biblioteca",
                JOptionPane.INFORMATION_MESSAGE
        );
        dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JScrollPane scpMusicasCargadas;
    private javax.swing.JTable tblMusicasCargadas;
    // End of variables declaration//GEN-END:variables
}