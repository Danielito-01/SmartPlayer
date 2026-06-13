package vistas;

import estructuras.BibliotecaGeneral;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelos.Musica;
import servicios.GestorHistorial;
import utilidades.Presentacion;
import utilidades.Tabla;

public class DialogoBibliotecaGeneral extends javax.swing.JDialog {
    private final BibliotecaGeneral biblioteca = BibliotecaGeneral.getInstance();
    private Musica musicaEditando;
    
    public DialogoBibliotecaGeneral(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Presentacion.aplicarBibliotecaGeneral(this);
        inicializar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMusicas = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        panEditarMusica = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtEditarNombre = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtEditarArtista = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtEditarAlbum = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtEditarGenero = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Biblioteca General");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Buscar:");

        txtBusqueda.addActionListener(this::txtBusquedaActionPerformed);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        tblMusicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Nombre", "Artista", "Album", "Genero", "Duracion", "Tamaño", "Ruta", "Año", "Repr", "Id"
            }
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }
    );
    tblMusicas.getColumnModel().getColumn(0).setResizable(false);
    jScrollPane1.setViewportView(tblMusicas);

    btnEliminar.setText("Eliminar");
    btnEliminar.addActionListener(this::btnEliminarActionPerformed);

    btnEditar.setText("Editar");
    btnEditar.addActionListener(this::btnEditarActionPerformed);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(btnEliminar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar))
                .addComponent(jScrollPane1)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(14, 14, 14)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtBusqueda)
                .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );

    panEditarMusica.setBackground(new java.awt.Color(255, 204, 204));

    btnGuardar.setText("Guardar");
    btnGuardar.addActionListener(this::btnGuardarActionPerformed);

    jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(51, 51, 51));
    jLabel3.setText("Nombre:");

    txtEditarNombre.setColumns(20);
    txtEditarNombre.setRows(5);
    jScrollPane2.setViewportView(txtEditarNombre);

    jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(51, 51, 51));
    jLabel4.setText("Artista:");

    txtEditarArtista.setColumns(20);
    txtEditarArtista.setRows(5);
    jScrollPane3.setViewportView(txtEditarArtista);

    jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(51, 51, 51));
    jLabel5.setText("Album:");

    txtEditarAlbum.setColumns(20);
    txtEditarAlbum.setRows(5);
    jScrollPane4.setViewportView(txtEditarAlbum);

    jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
    jLabel6.setForeground(new java.awt.Color(51, 51, 51));
    jLabel6.setText("Genero:");

    txtEditarGenero.setColumns(20);
    txtEditarGenero.setRows(5);
    jScrollPane5.setViewportView(txtEditarGenero);

    jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
    jLabel7.setForeground(new java.awt.Color(51, 51, 51));
    jLabel7.setText("Año:");

    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane6.setViewportView(jTextArea1);

    javax.swing.GroupLayout panEditarMusicaLayout = new javax.swing.GroupLayout(panEditarMusica);
    panEditarMusica.setLayout(panEditarMusicaLayout);
    panEditarMusicaLayout.setHorizontalGroup(
        panEditarMusicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panEditarMusicaLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(panEditarMusicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane5)
                .addGroup(panEditarMusicaLayout.createSequentialGroup()
                    .addGap(0, 199, Short.MAX_VALUE)
                    .addComponent(btnGuardar))
                .addComponent(jScrollPane2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jScrollPane6)
                .addGroup(panEditarMusicaLayout.createSequentialGroup()
                    .addGroup(panEditarMusicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(panEditarMusicaLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(panEditarMusicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)))
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    panEditarMusicaLayout.setVerticalGroup(
        panEditarMusicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panEditarMusicaLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                    .addComponent(panEditarMusica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(12, 12, 12)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panEditarMusica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(9, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicializar(){
        panEditarMusica.setVisible(false);
        Tabla.ocultarColumna(tblMusicas, 10);
        Tabla.establecerAnchoMaximo(tblMusicas, 0, 45);
        Tabla.establecerAnchoMaximo(tblMusicas, 5, 60);
        Tabla.establecerAnchoMaximo(tblMusicas, 6, 80);
        Tabla.establecerAnchoMaximo(tblMusicas, 9, 55);
        Tabla.cargarMusicasConDetallesYReproducciones(tblMusicas, biblioteca.getBiblioteca().listaMusicas());
    }
    
    private void buscarMusicas() {
        String texto = txtBusqueda.getText();
        List<Musica> resultados = biblioteca.buscarMusicas(texto);

        boolean busquedaActiva = texto != null && !texto.trim().isEmpty();

        if (busquedaActiva) {
            biblioteca.getHistorial().registrarBusqueda(
                    texto.trim(),
                    resultados.size(),
                    GestorHistorial.Origen.BIBLIOTECA,
                    "Biblioteca General"
            );
        }
        Tabla.cargarMusicasConDetallesYReproducciones(tblMusicas, resultados);
    }
    
    private List<Musica> obtenerMusicasSeleccionadas(JTable tabla) {
        List<Musica> musicas = new ArrayList<>();

        int[] filas = tabla.getSelectedRows();

        for (int fila : filas) {
            int filaModelo = tabla.convertRowIndexToModel(fila);
            Object valor = tabla.getModel().getValueAt(filaModelo, 10);

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
    
    private void refrescarTablaMusicas() {
        String texto = txtBusqueda.getText();

        if (texto != null && !texto.trim().isEmpty()) {
            List<Musica> resultados = biblioteca.buscarMusicas(texto);
            Tabla.cargarMusicasConDetallesYReproducciones(tblMusicas, resultados);
        } else {
            Tabla.cargarMusicasConDetallesYReproducciones(
                    tblMusicas,
                    biblioteca.getBiblioteca().listaMusicas()
            );
        }
    }
   
    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        buscarMusicas();
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarMusicas();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        List<Musica> musicas = obtenerMusicasSeleccionadas(tblMusicas);

        if (musicas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona una o más músicas.");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas eliminar las músicas seleccionadas de la biblioteca?\n"
                    + "También se quitarán de las playlists donde estén.",
                "Eliminar músicas",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        String mensaje = biblioteca.eliminarMusicas(musicas);

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Biblioteca General",
                JOptionPane.INFORMATION_MESSAGE
        );

        refrescarTablaMusicas();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int fila = tblMusicas.getSelectedRow();

        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una música.");
            return;
        }

        int filaModelo = tblMusicas.convertRowIndexToModel(fila);
        Object valor = tblMusicas.getModel().getValueAt(filaModelo, 10);

        if (valor == null) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el ID de la música.");
            return;
        }

        int id;

        try {
            id = Integer.parseInt(valor.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de la música no es válido.");
            return;
        }

        if (id <= 0) {
            JOptionPane.showMessageDialog(this, "El ID de la música no es válido.");
            return;
        }

        Musica musica = biblioteca.buscarPorId(id);

        if (musica == null) {
            JOptionPane.showMessageDialog(this, "No se encontró la música.");
            return;
        }
        musicaEditando = musica;

        txtEditarNombre.setText(musica.getNombre());
        txtEditarArtista.setText(musica.getArtista());
        txtEditarAlbum.setText(musica.getAlbum());
        txtEditarGenero.setText(musica.getGenero());
        jTextArea1.setText(String.valueOf(musica.getAnio()));

        panEditarMusica.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (musicaEditando == null) {
            JOptionPane.showMessageDialog(this, "No hay música seleccionada para editar.");
            return;
        }

        String nombre = txtEditarNombre.getText().trim();
        String artista = txtEditarArtista.getText().trim();
        String album = txtEditarAlbum.getText().trim();
        String genero = txtEditarGenero.getText().trim();

        int anio = 0;

        String textoAnio = jTextArea1.getText().trim();

        if (!textoAnio.isEmpty()) {
            try {
                anio = Integer.parseInt(textoAnio);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El año no es válido.");
                return;
            }
        }

        String mensaje = biblioteca.editarMusica(
                musicaEditando.getId(),
                nombre,
                artista,
                album,
                genero,
                anio
        );

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Editar música",
                JOptionPane.INFORMATION_MESSAGE
        );

        refrescarTablaMusicas();

        musicaEditando = null;
        panEditarMusica.setVisible(false);
    }//GEN-LAST:event_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel panEditarMusica;
    private javax.swing.JTable tblMusicas;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextArea txtEditarAlbum;
    private javax.swing.JTextArea txtEditarArtista;
    private javax.swing.JTextArea txtEditarGenero;
    private javax.swing.JTextArea txtEditarNombre;
    // End of variables declaration//GEN-END:variables
}
