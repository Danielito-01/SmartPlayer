package vistas;

import estructuras.BibliotecaGeneral;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Musica;
import modelos.Playlist;
import utilidades.Presentacion;
import utilidades.Tabla;

public class DialogoNuevaPlaylist extends javax.swing.JDialog {
    private final BibliotecaGeneral biblioteca = BibliotecaGeneral.getInstance();

    private final String nombrePlaylist;
    private final List<Musica> seleccionadas = new ArrayList<>();
    private final Set<Integer> idsSeleccionadas = new HashSet<>();

    public DialogoNuevaPlaylist(java.awt.Frame parent, boolean modal, String nombrePlaylist) {
        super(parent, modal);
        initComponents();
        this.nombrePlaylist = nombrePlaylist;
        inicializar();
    }
    
    private void inicializar() {
        Presentacion.aplicarNuevaPlaylist(this);
        configurarTablas();
        lblNombrePlaylist.setText(nombrePlaylist);
        cargarBiblioteca();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombrePlaylist = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMusicasPlaylist = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMusicas = new javax.swing.JTable();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNombrePlaylist.setFont(new java.awt.Font("Segoe UI Black", 3, 36)); // NOI18N
        lblNombrePlaylist.setForeground(new java.awt.Color(51, 51, 51));
        lblNombrePlaylist.setText("Playlist");

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setText("Biblioteca");

        tblMusicasPlaylist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Nombre", "Id"
            }
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblMusicasPlaylist.getColumnModel().getColumn(0).setResizable(false);
        jScrollPane1.setViewportView(tblMusicasPlaylist);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("Musicas en la Playlist");

        tblMusicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Nombre", "Artista", "Album", "Genero", "Id"
            }
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblMusicas.getColumnModel().getColumn(0).setResizable(false);
        jScrollPane3.setViewportView(tblMusicas);

        txtBusqueda.addActionListener(this::txtBusquedaActionPerformed);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnAgregar))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtBusqueda)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnBuscar))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGuardar)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(93, 93, 93))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombrePlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombrePlaylist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarTablas() {
        Tabla.establecerAnchoMaximo(tblMusicas, 0, 45);
        Tabla.establecerAnchoMaximo(tblMusicasPlaylist, 0, 45);
        Tabla.ocultarColumna(tblMusicas, 5);
        Tabla.ocultarColumna(tblMusicasPlaylist, 2);
    }
    
    private void cargarBiblioteca() {
        Tabla.cargarMusicasParaBusqueda(tblMusicas, biblioteca.getBiblioteca().listaMusicas());
    }
    
    private void cargarMusicasSeleccionadas() {
        DefaultTableModel tabla = (DefaultTableModel) tblMusicasPlaylist.getModel();
        tabla.setRowCount(0);

        int no = 1;
        for (Musica musica : seleccionadas) {
            tabla.addRow(new Object[]{ no++, musica.getNombre(), musica.getId() });
        }
    }
    
    private void buscarMusicas() {
        String texto = txtBusqueda.getText();
        List<Musica> resultados = biblioteca.buscarMusicas(texto);
        Tabla.cargarMusicasParaBusqueda(tblMusicas, resultados);
    }
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (nombrePlaylist == null || nombrePlaylist.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre de playlist inválido.");
            return;
        }

        if (biblioteca.existePlaylist(nombrePlaylist)) {
            JOptionPane.showMessageDialog(this, "Ya existe una playlist con ese nombre.");
            return;
        }

        if (seleccionadas.isEmpty()) {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "No has agregado músicas.\n¿Deseas guardar la playlist vacía?",
                    "Guardar playlist",
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }
        }

        String mensajeCreacion = biblioteca.crearPlaylist(nombrePlaylist);
        Playlist playlistCreada = biblioteca.buscarPlaylistPorNombre(nombrePlaylist);

        if (playlistCreada == null) {
            JOptionPane.showMessageDialog(
                    this,
                    mensajeCreacion + "\n\nNo se pudo encontrar la playlist creada.",
                    "Playlist",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        String mensajeAgregado = biblioteca.agregarMusicasAPlaylist(
                playlistCreada.getId(),
                seleccionadas
        );

        JOptionPane.showMessageDialog(
                this,
                mensajeCreacion + "\n\n" + mensajeAgregado,
                "Playlist",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        int[] filas = tblMusicas.getSelectedRows();

        if (filas.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una o más músicas de la biblioteca.");
            return;
        }

        int agregadas = 0;
        int repetidas = 0;
        int noEncontradas = 0;
        for (int fila : filas) {
            int filaModelo = tblMusicas.convertRowIndexToModel(fila);
            Object valor = tblMusicas.getModel().getValueAt(filaModelo, 5);
            if (valor == null) {
                noEncontradas++;
                continue;
            }

            int id;
            try {
                id = Integer.parseInt(valor.toString());
            } catch (NumberFormatException e) {
                noEncontradas++;
                continue;
            }
            Musica seleccionada = biblioteca.buscarPorId(id);
            if (seleccionada == null) {
                noEncontradas++;
                continue;
            }
            if (!idsSeleccionadas.add(seleccionada.getId())) {
                repetidas++;
                continue;
            }
            seleccionadas.add(seleccionada);
            agregadas++;
        }

        cargarMusicasSeleccionadas();
        if (repetidas > 0 || noEncontradas > 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Agregadas: " + agregadas
                    + "\nRepetidas ignoradas: " + repetidas
                    + "\nNo encontradas: " + noEncontradas
            );
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarMusicas();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        buscarMusicas();
    }//GEN-LAST:event_txtBusquedaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblNombrePlaylist;
    private javax.swing.JTable tblMusicas;
    private javax.swing.JTable tblMusicasPlaylist;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}