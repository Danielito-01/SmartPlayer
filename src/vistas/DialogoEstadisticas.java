package vistas;

import modelos.Musica;
import servicios.GestorEstadistica;
import utilidades.Presentacion;

public class DialogoEstadisticas extends javax.swing.JDialog {
    private final GestorEstadistica gestorEstadistica = new GestorEstadistica();

    public DialogoEstadisticas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Presentacion.aplicarEstadisticas(this);
        cargarEstadisticas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbls = new javax.swing.JLabel();
        lbl = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTotalMusicas = new javax.swing.JLabel();
        lblDuracionTotal = new javax.swing.JLabel();
        lblTamanioTotal = new javax.swing.JLabel();
        lblPromedioDuracion = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblMusicaMasReproducida = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblArtistaMasEscuchado = new javax.swing.JLabel();
        lblGeneroMasFrecuente = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblPlaylistMasGrande = new javax.swing.JLabel();
        lblPlaylistMasLarga = new javax.swing.JLabel();
        lblPlaylistMasPesada = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Estadisticas");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Resumen general de la biblioteca");

        lbls.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lbls.setForeground(new java.awt.Color(51, 51, 51));
        lbls.setText("Total Musicas:");

        lbl.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lbl.setForeground(new java.awt.Color(51, 51, 51));
        lbl.setText("Duracion Total:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Tamaño Total:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Promedio De Duracion:");

        lblTotalMusicas.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblTotalMusicas.setText("0000");

        lblDuracionTotal.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblDuracionTotal.setForeground(new java.awt.Color(51, 51, 51));
        lblDuracionTotal.setText("0 hrs 00 min");

        lblTamanioTotal.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblTamanioTotal.setForeground(new java.awt.Color(51, 51, 51));
        lblTamanioTotal.setText("000 GB");

        lblPromedioDuracion.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblPromedioDuracion.setForeground(new java.awt.Color(51, 51, 51));
        lblPromedioDuracion.setText("0 min 00 seg");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblTamanioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(lblPromedioDuracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lblTotalMusicas, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lbl)
                            .addComponent(lblDuracionTotal, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(57, 57, 57))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbls)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(68, 68, 68))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl)
                    .addComponent(lbls))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDuracionTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalMusicas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTamanioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPromedioDuracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Lo mas escuchado");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Musica Mas Reproducida:");

        lblMusicaMasReproducida.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblMusicaMasReproducida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMusicaMasReproducida.setText("-");
        lblMusicaMasReproducida.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Artista Mas Escuchado:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Genero Mas Frecuente:");

        lblArtistaMasEscuchado.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblArtistaMasEscuchado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArtistaMasEscuchado.setText("-");
        lblArtistaMasEscuchado.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblGeneroMasFrecuente.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblGeneroMasFrecuente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGeneroMasFrecuente.setText("-");
        lblGeneroMasFrecuente.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9)
                                .addGap(90, 90, 90)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(jLabel7)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMusicaMasReproducida, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblArtistaMasEscuchado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblGeneroMasFrecuente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblArtistaMasEscuchado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGeneroMasFrecuente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMusicaMasReproducida, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Playlist");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Playlist Mas Grande:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Playlist Mas Larga:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Playlist Mas Pesada:");

        lblPlaylistMasGrande.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblPlaylistMasGrande.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlaylistMasGrande.setText("-");
        lblPlaylistMasGrande.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblPlaylistMasLarga.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblPlaylistMasLarga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlaylistMasLarga.setText("-");
        lblPlaylistMasLarga.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblPlaylistMasPesada.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblPlaylistMasPesada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlaylistMasPesada.setText("-");
        lblPlaylistMasPesada.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblPlaylistMasGrande, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblPlaylistMasLarga, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel14))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(lblPlaylistMasPesada, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPlaylistMasGrande)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPlaylistMasLarga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPlaylistMasPesada)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarEstadisticas() {
        cargarResumenGeneral();
        cargarEstadisticasMusica();
        cargarEstadisticasPlaylists();
    }
    
    private void cargarResumenGeneral() {
        GestorEstadistica.ResumenBiblioteca resumen = gestorEstadistica.getResumenBiblioteca();
        lblTotalMusicas.setText(String.valueOf(resumen.getTotalCanciones()));
        lblDuracionTotal.setText(resumen.getDuracionTotalFormateada());
        lblTamanioTotal.setText(resumen.getTamanioTotalFormateado());
        lblPromedioDuracion.setText(resumen.getPromedioDuracionFormateado());
    }
    
    private void cargarEstadisticasMusica() {
        Musica musica = gestorEstadistica.getCancionMasReproducida();

        if (musica != null) {
            String txt = musica.getNombre() + " - " + musica.getReproducciones() + " reproducciones";
            lblMusicaMasReproducida.setText("<html><div style='width:200px; text-align:center;'>" + txt + "</div></html>");
            
        } else {
            lblMusicaMasReproducida.setText("Sin musicas");
        }

        GestorEstadistica.ResultadoTextoNumero artista = gestorEstadistica.getArtistaMasEscuchado();

        if (artista != null) {
            String txt = artista.getTexto() + " - " + artista.getNumero() + " reproducciones";
            lblArtistaMasEscuchado.setText("<html><div style='width:100px; text-align:center;'>" + txt + "</div></html>");
        } else {
            lblArtistaMasEscuchado.setText("Sin datos");
        }

        GestorEstadistica.ResultadoTextoNumero genero = gestorEstadistica.getGeneroMasFrecuente();

        if (genero != null) {
            String txt = genero.getTexto() + " - " + genero.getNumero() + " musicas";
            lblGeneroMasFrecuente.setText("<html><div style='width:100px; text-align:center;'>" + txt + "</div></html>");
        } else {
            lblGeneroMasFrecuente.setText("Sin datos");
        }
    }
    
    private void cargarEstadisticasPlaylists() {
        GestorEstadistica.ResultadoPlaylist playlistGrande =
                gestorEstadistica.getPlaylistMasGrande();

        if (playlistGrande != null) {
            lblPlaylistMasGrande.setText(
                    playlistGrande.getNombre() + " - " + playlistGrande.getCantidadCanciones() + " musicas"
            );
        } else {
            lblPlaylistMasGrande.setText("Sin playlists");
        }

        GestorEstadistica.ResultadoPlaylist playlistLarga =
                gestorEstadistica.getPlaylistMasLarga();

        if (playlistLarga != null) {
            lblPlaylistMasLarga.setText(
                    playlistLarga.getNombre() + " - " + playlistLarga.getDuracionTotalFormateada()
            );
        } else {
            lblPlaylistMasLarga.setText("Sin playlists");
        }

        GestorEstadistica.ResultadoPlaylist playlistPesada =
                gestorEstadistica.getPlaylistMasPesada();

        if (playlistPesada != null) {
            lblPlaylistMasPesada.setText(
                    playlistPesada.getNombre() + " - " + playlistPesada.getTamanioTotalFormateado()
            );
        } else {
            lblPlaylistMasPesada.setText("Sin playlists");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblArtistaMasEscuchado;
    private javax.swing.JLabel lblDuracionTotal;
    private javax.swing.JLabel lblGeneroMasFrecuente;
    private javax.swing.JLabel lblMusicaMasReproducida;
    private javax.swing.JLabel lblPlaylistMasGrande;
    private javax.swing.JLabel lblPlaylistMasLarga;
    private javax.swing.JLabel lblPlaylistMasPesada;
    private javax.swing.JLabel lblPromedioDuracion;
    private javax.swing.JLabel lblTamanioTotal;
    private javax.swing.JLabel lblTotalMusicas;
    private javax.swing.JLabel lbls;
    // End of variables declaration//GEN-END:variables
}
