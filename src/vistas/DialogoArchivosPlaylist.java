package vistas;

import estructuras.BibliotecaGeneral;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import modelos.Playlist;
import servicios.GestorArchivoPlaylist;
import utilidades.Presentacion;

public class DialogoArchivosPlaylist extends javax.swing.JDialog {
    private final GestorArchivoPlaylist gestorArchivoPlaylist = new GestorArchivoPlaylist();
    private final BibliotecaGeneral biblioteca = BibliotecaGeneral.getInstance();
    private List<Playlist> playlistsExportar = new ArrayList<>();
    
    public DialogoArchivosPlaylist(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializar();
        Presentacion.aplicarArchivosPlaylist(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabArchivosPlaylist = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbPlaylistsExportar = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRutaExportar = new javax.swing.JTextArea();
        btnExaminarExportar = new javax.swing.JButton();
        chkExportarEncriptada = new javax.swing.JCheckBox();
        pwdClaveExportar = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        btnExportarPlaylist = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnExaminarImportar = new javax.swing.JButton();
        chkImportarEncriptada = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        pwdClaveImportar = new javax.swing.JPasswordField();
        btnImportarPlaylist = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRutaImportar = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRutaEncriptarOrigen = new javax.swing.JTextArea();
        btnExaminarEncriptarOrigen = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtRutaEncriptarDestino = new javax.swing.JTextArea();
        btnExaminarEncriptarDestino = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        pwdClaveEncriptar = new javax.swing.JPasswordField();
        btnEncriptarArchivo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtRutaDesencriptarOrigen = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtRutaDesencriptarDestino = new javax.swing.JTextArea();
        btnExaminarDesencriptarOrigen = new javax.swing.JButton();
        btnExaminarDesencriptarDestino = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        pwdClaveDesencriptar = new javax.swing.JPasswordField();
        btnDesencriptarArchivo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabArchivosPlaylist.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Playlist:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Ruta:");

        txtRutaExportar.setColumns(20);
        txtRutaExportar.setRows(5);
        jScrollPane1.setViewportView(txtRutaExportar);

        btnExaminarExportar.setText("Examinar");
        btnExaminarExportar.addActionListener(this::btnExaminarExportarActionPerformed);

        chkExportarEncriptada.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chkExportarEncriptada.setText("Encriptar");
        chkExportarEncriptada.addActionListener(this::chkExportarEncriptadaActionPerformed);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Clave:");

        btnExportarPlaylist.setText("Exportar");
        btnExportarPlaylist.addActionListener(this::btnExportarPlaylistActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPlaylistsExportar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkExportarEncriptada, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pwdClaveExportar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExportarPlaylist)
                    .addComponent(btnExaminarExportar))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbPlaylistsExportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnExaminarExportar, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkExportarEncriptada)
                    .addComponent(jLabel3)
                    .addComponent(pwdClaveExportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportarPlaylist))
                .addGap(10, 10, 10))
        );

        tabArchivosPlaylist.addTab("Exportar", jPanel1);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Ruta:");

        btnExaminarImportar.setText("Examinar");
        btnExaminarImportar.addActionListener(this::btnExaminarImportarActionPerformed);

        chkImportarEncriptada.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chkImportarEncriptada.setForeground(new java.awt.Color(51, 51, 51));
        chkImportarEncriptada.setText("Desencriptar");
        chkImportarEncriptada.addActionListener(this::chkImportarEncriptadaActionPerformed);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Clave:");

        btnImportarPlaylist.setText("Importar");
        btnImportarPlaylist.addActionListener(this::btnImportarPlaylistActionPerformed);

        txtRutaImportar.setColumns(20);
        txtRutaImportar.setRows(5);
        jScrollPane3.setViewportView(txtRutaImportar);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExaminarImportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chkImportarEncriptada)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pwdClaveImportar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnImportarPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnExaminarImportar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkImportarEncriptada, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwdClaveImportar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImportarPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        tabArchivosPlaylist.addTab("Importar", jPanel2);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Origen");

        txtRutaEncriptarOrigen.setColumns(20);
        txtRutaEncriptarOrigen.setRows(5);
        jScrollPane2.setViewportView(txtRutaEncriptarOrigen);

        btnExaminarEncriptarOrigen.setText("Examinar");
        btnExaminarEncriptarOrigen.addActionListener(this::btnExaminarEncriptarOrigenActionPerformed);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Destino:");

        txtRutaEncriptarDestino.setColumns(20);
        txtRutaEncriptarDestino.setRows(5);
        jScrollPane4.setViewportView(txtRutaEncriptarDestino);

        btnExaminarEncriptarDestino.setText("Examinar");
        btnExaminarEncriptarDestino.addActionListener(this::btnExaminarEncriptarDestinoActionPerformed);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Clave:");

        btnEncriptarArchivo.setText("Encriptar");
        btnEncriptarArchivo.addActionListener(this::btnEncriptarArchivoActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExaminarEncriptarOrigen, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnExaminarEncriptarDestino, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(pwdClaveEncriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEncriptarArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnExaminarEncriptarOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExaminarEncriptarDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pwdClaveEncriptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnEncriptarArchivo)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabArchivosPlaylist.addTab("Encriptar", jPanel3);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Origen:");

        txtRutaDesencriptarOrigen.setColumns(20);
        txtRutaDesencriptarOrigen.setRows(5);
        jScrollPane5.setViewportView(txtRutaDesencriptarOrigen);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Destinio:");

        txtRutaDesencriptarDestino.setColumns(20);
        txtRutaDesencriptarDestino.setRows(5);
        jScrollPane6.setViewportView(txtRutaDesencriptarDestino);

        btnExaminarDesencriptarOrigen.setText("Examinar");
        btnExaminarDesencriptarOrigen.addActionListener(this::btnExaminarDesencriptarOrigenActionPerformed);

        btnExaminarDesencriptarDestino.setText("Examinar");
        btnExaminarDesencriptarDestino.addActionListener(this::btnExaminarDesencriptarDestinoActionPerformed);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Clave:");

        btnDesencriptarArchivo.setText("Desencriptar");
        btnDesencriptarArchivo.addActionListener(this::btnDesencriptarArchivoActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addComponent(jScrollPane5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExaminarDesencriptarOrigen, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnExaminarDesencriptarDestino, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pwdClaveDesencriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDesencriptarArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExaminarDesencriptarOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnExaminarDesencriptarDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(pwdClaveDesencriptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesencriptarArchivo))
                .addGap(13, 13, 13))
        );

        tabArchivosPlaylist.addTab("Desencriptar", jPanel4);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Importar / Exportar Playlists   ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabArchivosPlaylist)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(99, 99, 99))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabArchivosPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicializar() {
        cargarPlaylistsExportar();
        configurarEstadoInicial();
    }

    private void configurarEstadoInicial() {
        configurarAreaRuta(txtRutaExportar);
        configurarAreaRuta(txtRutaImportar);
        configurarAreaRuta(txtRutaEncriptarOrigen);
        configurarAreaRuta(txtRutaEncriptarDestino);
        configurarAreaRuta(txtRutaDesencriptarOrigen);
        configurarAreaRuta(txtRutaDesencriptarDestino);

        cambiarEstadoClave(chkExportarEncriptada, pwdClaveExportar);
        cambiarEstadoClave(chkImportarEncriptada, pwdClaveImportar);
    }

    private void configurarAreaRuta(javax.swing.JTextArea area) {
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
    }

    private void cargarPlaylistsExportar() {
        cmbPlaylistsExportar.removeAllItems();

        playlistsExportar = biblioteca.getPlaylists();

        for (Playlist playlist : playlistsExportar) {
            cmbPlaylistsExportar.addItem(playlist.getNombre());
        }
    }

    private Playlist getPlaylistExportarSeleccionada() {
        int indice = cmbPlaylistsExportar.getSelectedIndex();

        if (indice < 0 || indice >= playlistsExportar.size()) {
            return null;
        }

        return playlistsExportar.get(indice);
    }

    private String getClave(JPasswordField campo) {
        return new String(campo.getPassword()).trim();
    }

    private String getRuta(javax.swing.JTextArea campo) {
        return campo.getText().trim();
    }

    private void cambiarEstadoClave(javax.swing.JCheckBox check, JPasswordField campoClave) {
        boolean activa = check.isSelected();

        campoClave.setEnabled(activa);

        if (!activa) {
            campoClave.setText("");
        }
    }

    private String asegurarExtension(String ruta, String extension) {
        if (ruta == null || ruta.trim().isEmpty()) {
            return "";
        }

        ruta = ruta.trim();

        if (!ruta.toLowerCase().endsWith("." + extension.toLowerCase())) {
            ruta += "." + extension;
        }

        return ruta;
    }

    private String seleccionarArchivoGuardar(String titulo, String extension) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(titulo);

        int opcion = chooser.showSaveDialog(this);

        if (opcion != JFileChooser.APPROVE_OPTION) {
            return "";
        }

        File archivo = chooser.getSelectedFile();
        return asegurarExtension(archivo.getAbsolutePath(), extension);
    }

    private String seleccionarArchivoAbrir(String titulo) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(titulo);

        int opcion = chooser.showOpenDialog(this);

        if (opcion != JFileChooser.APPROVE_OPTION) {
            return "";
        }

        return chooser.getSelectedFile().getAbsolutePath();
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                titulo,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void btnExaminarExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarExportarActionPerformed
        String extension = chkExportarEncriptada.isSelected() ? "sppenc" : "spp";

        String ruta = seleccionarArchivoGuardar(
                "Guardar playlist",
                extension
        );

        if (!ruta.isEmpty()) {
            txtRutaExportar.setText(ruta);
        }
    }//GEN-LAST:event_btnExaminarExportarActionPerformed

    private void chkExportarEncriptadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkExportarEncriptadaActionPerformed
        cambiarEstadoClave(chkExportarEncriptada, pwdClaveExportar);

        String ruta = getRuta(txtRutaExportar);

        if (!ruta.isEmpty()) {
            String extension = chkExportarEncriptada.isSelected() ? "sppenc" : "spp";

            ruta = ruta.replaceAll("(?i)\\.sppenc$", "");
            ruta = ruta.replaceAll("(?i)\\.spp$", "");

            txtRutaExportar.setText(asegurarExtension(ruta, extension));
        }
    }//GEN-LAST:event_chkExportarEncriptadaActionPerformed

    private void btnExportarPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarPlaylistActionPerformed
        Playlist playlist = getPlaylistExportarSeleccionada();

        if (playlist == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una playlist.");
            return;
        }

        boolean encriptada = chkExportarEncriptada.isSelected();
        String extension = encriptada ? "sppenc" : "spp";
        String ruta = asegurarExtension(getRuta(txtRutaExportar), extension);
        String clave = getClave(pwdClaveExportar);

        if (ruta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona una ruta para exportar.");
            return;
        }

        if (encriptada && clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa una clave para encriptar.");
            return;
        }

        txtRutaExportar.setText(ruta);

        String mensaje = gestorArchivoPlaylist.exportarPlaylist(
                playlist.getId(),
                ruta,
                encriptada,
                clave
        );

        mostrarMensaje("Exportar playlist", mensaje);
        dispose();
    }//GEN-LAST:event_btnExportarPlaylistActionPerformed

    private void btnExaminarImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarImportarActionPerformed
        String ruta = seleccionarArchivoAbrir("Seleccionar playlist");

        if (!ruta.isEmpty()) {
            txtRutaImportar.setText(ruta);
        }
    }//GEN-LAST:event_btnExaminarImportarActionPerformed

    private void chkImportarEncriptadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkImportarEncriptadaActionPerformed
        cambiarEstadoClave(chkImportarEncriptada, pwdClaveImportar);
    }//GEN-LAST:event_chkImportarEncriptadaActionPerformed

    private void btnImportarPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarPlaylistActionPerformed
        String ruta = getRuta(txtRutaImportar);
        boolean encriptada = chkImportarEncriptada.isSelected();
        String clave = getClave(pwdClaveImportar);

        if (ruta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un archivo para importar.");
            return;
        }

        if (encriptada && clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa la clave para desencriptar.");
            return;
        }

        String mensaje = gestorArchivoPlaylist.importarPlaylist(
                ruta,
                encriptada,
                clave
        );

        mostrarMensaje("Importar playlist", mensaje);

        cargarPlaylistsExportar();
        dispose();
    }//GEN-LAST:event_btnImportarPlaylistActionPerformed

    private void btnExaminarEncriptarOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarEncriptarOrigenActionPerformed
        String ruta = seleccionarArchivoAbrir("Seleccionar archivo para encriptar");

        if (!ruta.isEmpty()) {
            txtRutaEncriptarOrigen.setText(ruta);
        }
    }//GEN-LAST:event_btnExaminarEncriptarOrigenActionPerformed

    private void btnExaminarEncriptarDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarEncriptarDestinoActionPerformed
        String ruta = seleccionarArchivoGuardar(
                "Guardar archivo encriptado",
                "sppenc"
        );

        if (!ruta.isEmpty()) {
            txtRutaEncriptarDestino.setText(ruta);
        }
    }//GEN-LAST:event_btnExaminarEncriptarDestinoActionPerformed

    private void btnEncriptarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncriptarArchivoActionPerformed
        String rutaOrigen = getRuta(txtRutaEncriptarOrigen);
        String rutaDestino = asegurarExtension(
                getRuta(txtRutaEncriptarDestino),
                "sppenc"
        );
        String clave = getClave(pwdClaveEncriptar);

        if (rutaOrigen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona el archivo origen.");
            return;
        }

        if (rutaDestino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona la ruta destino.");
            return;
        }

        if (clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa una clave para encriptar.");
            return;
        }

        txtRutaEncriptarDestino.setText(rutaDestino);

        String mensaje = gestorArchivoPlaylist.encriptarArchivo(
                rutaOrigen,
                rutaDestino,
                clave
        );

        mostrarMensaje("Encriptar archivo", mensaje);
        dispose();
    }//GEN-LAST:event_btnEncriptarArchivoActionPerformed

    private void btnExaminarDesencriptarOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarDesencriptarOrigenActionPerformed
        String ruta = seleccionarArchivoAbrir("Seleccionar archivo encriptado");

        if (!ruta.isEmpty()) {
            txtRutaDesencriptarOrigen.setText(ruta);
        }
    }//GEN-LAST:event_btnExaminarDesencriptarOrigenActionPerformed

    private void btnExaminarDesencriptarDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarDesencriptarDestinoActionPerformed
        String ruta = seleccionarArchivoGuardar(
                "Guardar archivo desencriptado",
                "spp"
        );

        if (!ruta.isEmpty()) {
            txtRutaDesencriptarDestino.setText(ruta);
        }
    }//GEN-LAST:event_btnExaminarDesencriptarDestinoActionPerformed

    private void btnDesencriptarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesencriptarArchivoActionPerformed
        String rutaOrigen = getRuta(txtRutaDesencriptarOrigen);

        String rutaDestino = asegurarExtension(
                getRuta(txtRutaDesencriptarDestino),
                "spp"
        );

        String clave = getClave(pwdClaveDesencriptar);

        if (rutaOrigen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona el archivo encriptado.");
            return;
        }

        if (rutaDestino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona la ruta destino.");
            return;
        }

        if (clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa la clave para desencriptar.");
            return;
        }

        txtRutaDesencriptarDestino.setText(rutaDestino);

        String mensaje = gestorArchivoPlaylist.desencriptarArchivo(
                rutaOrigen,
                rutaDestino,
                clave
        );

        mostrarMensaje("Desencriptar archivo", mensaje);
        dispose();
    }//GEN-LAST:event_btnDesencriptarArchivoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDesencriptarArchivo;
    private javax.swing.JButton btnEncriptarArchivo;
    private javax.swing.JButton btnExaminarDesencriptarDestino;
    private javax.swing.JButton btnExaminarDesencriptarOrigen;
    private javax.swing.JButton btnExaminarEncriptarDestino;
    private javax.swing.JButton btnExaminarEncriptarOrigen;
    private javax.swing.JButton btnExaminarExportar;
    private javax.swing.JButton btnExaminarImportar;
    private javax.swing.JButton btnExportarPlaylist;
    private javax.swing.JButton btnImportarPlaylist;
    private javax.swing.JCheckBox chkExportarEncriptada;
    private javax.swing.JCheckBox chkImportarEncriptada;
    private javax.swing.JComboBox<String> cmbPlaylistsExportar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPasswordField pwdClaveDesencriptar;
    private javax.swing.JPasswordField pwdClaveEncriptar;
    private javax.swing.JPasswordField pwdClaveExportar;
    private javax.swing.JPasswordField pwdClaveImportar;
    private javax.swing.JTabbedPane tabArchivosPlaylist;
    private javax.swing.JTextArea txtRutaDesencriptarDestino;
    private javax.swing.JTextArea txtRutaDesencriptarOrigen;
    private javax.swing.JTextArea txtRutaEncriptarDestino;
    private javax.swing.JTextArea txtRutaEncriptarOrigen;
    private javax.swing.JTextArea txtRutaExportar;
    private javax.swing.JTextArea txtRutaImportar;
    // End of variables declaration//GEN-END:variables
}
