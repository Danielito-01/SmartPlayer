package vistas;

import estructuras.BibliotecaGeneral;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelos.Musica;
import modelos.Playlist;
import servicios.GestorHistorial;
import utilidades.Presentacion;
import utilidades.Tabla;

public class DialogoAdministrarPlaylist extends javax.swing.JDialog {
    private final BibliotecaGeneral biblioteca = BibliotecaGeneral.getInstance();
    private final int idPlaylist;
    private Playlist playlist;
    
    public DialogoAdministrarPlaylist(java.awt.Frame parent, boolean modal, int idPlaylist) {
        super(parent, modal);
        initComponents();
        this.idPlaylist = idPlaylist;
        this.playlist = biblioteca.buscarPlaylistPorId(idPlaylist);
        if (this.playlist == null) {
            JOptionPane.showMessageDialog(this, "No se encontró la playlist.");
            dispose();
            return;
        }
        Presentacion.aplicarAdministrarPlaylist(this);
        inicializar();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnQuitar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMusicasPlaylist = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtBusquedaPlaylist = new javax.swing.JTextField();
        btnBuscarPlaylist = new javax.swing.JButton();
        txtNombrePlaylist = new javax.swing.JTextField();
        btnRenombrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBusquedaBiblioteca = new javax.swing.JTextField();
        btnBuscarBiblioteca = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMusicasBiblioteca = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnEliminarPlaylist = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(this::btnQuitarActionPerformed);

        tblMusicasPlaylist.setModel(new javax.swing.table.DefaultTableModel(
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
        }
    );
    tblMusicasPlaylist.getColumnModel().getColumn(0).setResizable(false);
    tblMusicasPlaylist.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mousePressed(java.awt.event.MouseEvent evt) {
            tblMusicasPlaylistMousePressed(evt);
        }
    });
    jScrollPane2.setViewportView(tblMusicasPlaylist);

    jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
    jLabel4.setText("Buscar:");

    txtBusquedaPlaylist.addActionListener(this::txtBusquedaPlaylistActionPerformed);

    btnBuscarPlaylist.setText("Buscar");
    btnBuscarPlaylist.addActionListener(this::btnBuscarPlaylistActionPerformed);

    txtNombrePlaylist.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
    txtNombrePlaylist.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtNombrePlaylist.setText("Nombre Playlist");

    btnRenombrar.setText("Renombrar");
    btnRenombrar.addActionListener(this::btnRenombrarActionPerformed);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtNombrePlaylist, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtBusquedaPlaylist)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnRenombrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscarPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addContainerGap(10, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtNombrePlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(btnRenombrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(2, 2, 2)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(txtBusquedaPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnBuscarPlaylist))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnQuitar)
            .addGap(12, 12, 12))
    );

    jPanel2.setBackground(new java.awt.Color(204, 255, 204));

    jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Biblioteca");
    jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

    jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(51, 51, 51));
    jLabel2.setText("Buscar");

    txtBusquedaBiblioteca.addActionListener(this::txtBusquedaBibliotecaActionPerformed);

    btnBuscarBiblioteca.setText("Buscar");
    btnBuscarBiblioteca.addActionListener(this::btnBuscarBibliotecaActionPerformed);

    tblMusicasBiblioteca.setModel(new javax.swing.table.DefaultTableModel(
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
    }
    );
    tblMusicasBiblioteca.getColumnModel().getColumn(0).setResizable(false);
    tblMusicasBiblioteca.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mousePressed(java.awt.event.MouseEvent evt) {
            tblMusicasBibliotecaMousePressed(evt);
        }
    });
    jScrollPane1.setViewportView(tblMusicasBiblioteca);

    btnAgregar.setText("Agregar");
    btnAgregar.addActionListener(this::btnAgregarActionPerformed);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtBusquedaBiblioteca)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnBuscarBiblioteca))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
            .addContainerGap())
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap(138, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(116, 116, 116))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(12, 12, 12)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(txtBusquedaBiblioteca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnBuscarBiblioteca))
            .addGap(18, 18, 18)
            .addComponent(jScrollPane1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnAgregar)
            .addGap(12, 12, 12))
    );

    jPanel3.setBackground(new java.awt.Color(153, 255, 204));

    btnEliminarPlaylist.setText("Eliminar Playlist");
    btnEliminarPlaylist.addActionListener(this::btnEliminarPlaylistActionPerformed);

    btnCerrar.setText("Cerrar");
    btnCerrar.addActionListener(this::btnCerrarActionPerformed);

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnEliminarPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(29, 29, 29)
            .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminarPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
            .addContainerGap())
    );

    jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(51, 51, 51));
    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel5.setText("Administrar Playlist");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(10, 10, 10))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(295, 295, 295))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicializar(){
        configurarTablas();
        cargarDatos();
    }

    private void configurarTablas(){
        Tabla.establecerAnchoMaximo(tblMusicasPlaylist, 0, 55);
        Tabla.establecerAnchoMaximo(tblMusicasBiblioteca, 0, 55);
        Tabla.ocultarColumna(tblMusicasPlaylist, 5);
        Tabla.ocultarColumna(tblMusicasBiblioteca, 5);
    }
    
    private void cargarDatos() {
        playlist = biblioteca.buscarPlaylistPorId(idPlaylist);

        if (playlist == null) {
            JOptionPane.showMessageDialog(this, "La playlist ya no existe.");
            dispose();
            return;
        }

        txtNombrePlaylist.setText(playlist.getNombre());
        cargarBiblioteca();
        cargarMusicasPlaylist();
    }
    
    private void cargarBiblioteca() {
        Tabla.cargarMusicasParaBusqueda(tblMusicasBiblioteca, biblioteca.getBiblioteca().listaMusicas());
    }
    
    private void cargarMusicasPlaylist() {
        Tabla.cargarMusicasParaBusqueda(tblMusicasPlaylist, playlist.getMusicas());
    }
    
    private void buscarMusicasBiblioteca() {
        String texto = txtBusquedaBiblioteca.getText();
        List<Musica> resultados = biblioteca.buscarMusicas(texto);

        boolean busquedaActiva = texto != null && !texto.trim().isEmpty();

        if (busquedaActiva) {
            biblioteca.getHistorial().registrarBusqueda(
                    texto.trim(),
                    resultados.size(),
                    GestorHistorial.Origen.BIBLIOTECA,
                    "Administrar playlist - Biblioteca general"
            );
        }
        Tabla.cargarMusicasParaBusqueda(tblMusicasBiblioteca, resultados);
    }
    
    private void buscarMusicasPlaylist() {
        String texto = txtBusquedaPlaylist.getText();

        List<Musica> resultados = playlist.getPlaylist().buscarMusicas(texto);

        boolean busquedaActiva = texto != null && !texto.trim().isEmpty();

        if (busquedaActiva) {
            biblioteca.getHistorial().registrarBusqueda(
                    texto.trim(),
                    resultados.size(),
                    GestorHistorial.Origen.PLAYLIST,
                    "Administrar playlist - " + playlist.getNombre()
            );
        }

        Tabla.cargarMusicasParaBusqueda(tblMusicasPlaylist, resultados);
    }
    
    private List<Musica> obtenerMusicasSeleccionadas(JTable tabla) {
        List<Musica> musicas = new ArrayList<>();

        int[] filas = tabla.getSelectedRows();

        for (int fila : filas) {
            int filaModelo = tabla.convertRowIndexToModel(fila);
            Object valor = tabla.getModel().getValueAt(filaModelo, 5);

            if (valor == null) {
                continue;
            }

            int id;

            try {
                id = Integer.parseInt(valor.toString());
            } catch (NumberFormatException e) {
                continue;
            }

            Musica musica = biblioteca.buscarPorId(id);

            if (musica != null) {
                musicas.add(musica);
            }
        }

        return musicas;
    }
 
    private void txtBusquedaPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaPlaylistActionPerformed
        buscarMusicasPlaylist();
    }//GEN-LAST:event_txtBusquedaPlaylistActionPerformed

    private void btnBuscarPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPlaylistActionPerformed
        buscarMusicasPlaylist();
    }//GEN-LAST:event_btnBuscarPlaylistActionPerformed

    private void txtBusquedaBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaBibliotecaActionPerformed
        buscarMusicasBiblioteca();
    }//GEN-LAST:event_txtBusquedaBibliotecaActionPerformed

    private void btnBuscarBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarBibliotecaActionPerformed
        buscarMusicasBiblioteca();
    }//GEN-LAST:event_btnBuscarBibliotecaActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        List<Musica> musicas = obtenerMusicasSeleccionadas(tblMusicasPlaylist);

        if (musicas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona una o más músicas de la playlist.");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas quitar las músicas seleccionadas de esta playlist?",
                "Quitar músicas",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        String mensaje = biblioteca.quitarMusicasDePlaylist(idPlaylist, musicas);

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Playlist",
                JOptionPane.INFORMATION_MESSAGE
        );

        cargarDatos();
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void tblMusicasPlaylistMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMusicasPlaylistMousePressed
        
    }//GEN-LAST:event_tblMusicasPlaylistMousePressed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        List<Musica> musicas = obtenerMusicasSeleccionadas(tblMusicasBiblioteca);

        if (musicas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona una o más músicas de la biblioteca.");
            return;
        }

        String mensaje = biblioteca.agregarMusicasAPlaylist(idPlaylist, musicas);

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Agregar músicas",
                JOptionPane.INFORMATION_MESSAGE
        );

        cargarDatos();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tblMusicasBibliotecaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMusicasBibliotecaMousePressed
        
    }//GEN-LAST:event_tblMusicasBibliotecaMousePressed

    private void btnEliminarPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPlaylistActionPerformed
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas eliminar esta playlist?\n"
                + "Las músicas no se eliminarán de la biblioteca general.",
                "Eliminar playlist",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        String mensaje = biblioteca.eliminarPlaylist(idPlaylist);

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Playlist",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();        
    }//GEN-LAST:event_btnEliminarPlaylistActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnRenombrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenombrarActionPerformed
        String nuevoNombre = txtNombrePlaylist.getText();

        String mensaje = biblioteca.renombrarPlaylist(idPlaylist, nuevoNombre);

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Playlist",
                JOptionPane.INFORMATION_MESSAGE
        );

        cargarDatos();  
    }//GEN-LAST:event_btnRenombrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarBiblioteca;
    private javax.swing.JButton btnBuscarPlaylist;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminarPlaylist;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnRenombrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblMusicasBiblioteca;
    private javax.swing.JTable tblMusicasPlaylist;
    private javax.swing.JTextField txtBusquedaBiblioteca;
    private javax.swing.JTextField txtBusquedaPlaylist;
    private javax.swing.JTextField txtNombrePlaylist;
    // End of variables declaration//GEN-END:variables
}
