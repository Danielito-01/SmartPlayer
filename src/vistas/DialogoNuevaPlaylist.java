package vistas;

import servicios.Biblioteca;
import modelos.Playlist;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Musica;
import utilidades.TablaHelper;

public class DialogoNuevaPlaylist extends javax.swing.JDialog {
    private final Biblioteca biblioteca = Biblioteca.getInstance();
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
        configurarTablas();
        configurarEventos();
        lblNombrePlaylist.setText(nombrePlaylist);
        cargarBiblioteca();
        cargarMusicasSeleccionadas();
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

        lblNombrePlaylist.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblNombrePlaylist.setText("Playlist");

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setText("Biblioteca");

        tblMusicasPlaylist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Nombre"
            }
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
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
        jScrollPane3.setViewportView(tblMusicas);

        btnBuscar.setText("Buscar");

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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAgregar)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(99, 99, 99)
                            .addComponent(btnGuardar)
                            .addGap(14, 14, 14)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGuardar)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                .addComponent(lblNombrePlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombrePlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarTablas() {
        TablaHelper.establecerAnchoMaximo(tblMusicas, 0, 30);
        TablaHelper.establecerAnchoMaximo(tblMusicasPlaylist, 0, 30);
        TablaHelper.ocultarColumna(tblMusicas, 5);
    }

    private void configurarEventos() {
        btnBuscar.addActionListener(e -> filtrarBiblioteca());
        txtBusqueda.addActionListener(e -> filtrarBiblioteca());
    }
    
    private void cargarMusicasSeleccionadas() {
        DefaultTableModel tabla = (DefaultTableModel) tblMusicasPlaylist.getModel();
        tabla.setRowCount(0);

        int no = 1;
        for (Musica musica : seleccionadas) {
            tabla.addRow(new Object[]{ no++, musica.getNombre() });
        }
    }
    
    private void cargarBiblioteca() {
        cargarMusicasEnTabla(biblioteca.getBiblioteca().toListAdelante());
    }

    private void cargarMusicasEnTabla(List<Musica> musicas) {
        DefaultTableModel tabla = (DefaultTableModel) tblMusicas.getModel();
        tabla.setRowCount(0);

        int no = 1;
        for (Musica musica : musicas) {
            tabla.addRow(new Object[]{
                no++,
                musica.getNombre(),
                musica.getArtista(),
                musica.getAlbum(),
                musica.getGenero(),
                musica.getId()
            });
        }
    }

    private void filtrarBiblioteca() {
        String busqueda = txtBusqueda.getText().trim().toLowerCase();
        if (busqueda.isBlank()) {
            cargarBiblioteca();
            return;
        }

        List<Musica> resultado = new java.util.ArrayList<>();
        for (Musica musica : biblioteca.getBiblioteca().toListAdelante()) {
            if (musica.getNombre().toLowerCase().contains(busqueda)
                    || musica.getArtista().toLowerCase().contains(busqueda)
                    || musica.getAlbum().toLowerCase().contains(busqueda)
                    || musica.getGenero().toLowerCase().contains(busqueda)) {

                resultado.add(musica);
            }
        }
        cargarMusicasEnTabla(resultado);
    }
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (seleccionadas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agrega al menos una musica antes de guardar");
            return;
        }
        Playlist playlist = biblioteca.crearPlaylist(nombrePlaylist);
        if (playlist == null) {
            JOptionPane.showMessageDialog(this, "No se pudo crear: nombre invalido o duplicado");
            return;
        }
        for (Musica musica : seleccionadas) {
            playlist.agregarMusica(musica); 
        }
        
        JOptionPane.showMessageDialog(
                this,
                "Playlist creada correctamente",
                "Playlist",
                JOptionPane.INFORMATION_MESSAGE
        );
        dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        int[] filas = tblMusicas.getSelectedRows();
        if (filas.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una o mas musicas de la biblioteca");
            return;
        }

        int agregadas = 0;
        int repetidas = 0;
        int noEncontradas = 0;

        for (int fila : filas) {
            Object valor = tblMusicas.getValueAt(fila, 5);
            int id = Integer.parseInt(valor.toString());
            Musica seleccionada = biblioteca.getBiblioteca().buscarPorId(id);
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
            JOptionPane.showMessageDialog(this,
                    "Agregadas: " + agregadas +
                    "\nExisten ignoradas: " + repetidas +
                    "\nNo encontradas: " + noEncontradas);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

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