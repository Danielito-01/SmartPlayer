package vistas;

import estructuras.BibliotecaGeneral;
import estructuras.ColaReproduccion;
import estructuras.ListaMusicas;
import estructuras.PilaHistorial;
import java.awt.Component;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.Musica;
import modelos.Playlist;
import servicios.GestorHistorial;
import servicios.GestorPortada;
import servicios.GestorReproductor;
import utilidades.Presentacion;
import utilidades.Tabla;

public class VentanaPrincipal extends javax.swing.JFrame {
    private final BibliotecaGeneral biblioteca = BibliotecaGeneral.getInstance();
    private ListaMusicas listaSeleccionada;
    private ListaMusicas listaReproduciendo;
    private Musica musicaSeleccionada;
    private Musica musicaReproduciendo;
    
    private final PilaHistorial pilaHistorial = biblioteca.getPilaHistorial();
    private final ColaReproduccion colaReproduccion = biblioteca.getColaReproduccion();
    private boolean reproduciendoDesdeCola;
    
    private boolean busquedaActiva;
    private String textoBusquedaActiva = "";
    
    private final GestorReproductor reproductor = new GestorReproductor();
    private boolean actualizandoSlider;
    private double duracionActualSegundos;

    public VentanaPrincipal() {
        initComponents();  
        Presentacion.aplicarVentanaPrincipal(this);
        inicializar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pMenuMusicas = new javax.swing.JPopupMenu();
        agregarACola = new javax.swing.JMenuItem();
        pMenuCola = new javax.swing.JPopupMenu();
        quitarDeCola = new javax.swing.JMenuItem();
        panPlaylist = new javax.swing.JPanel();
        scpPlaylist = new javax.swing.JScrollPane();
        tblPlaylist = new javax.swing.JTable();
        btnBiblioteca = new javax.swing.JButton();
        lblCola = new javax.swing.JLabel();
        scpCola = new javax.swing.JScrollPane();
        tblCola = new javax.swing.JTable();
        panCanciones = new javax.swing.JPanel();
        scpMusicas = new javax.swing.JScrollPane();
        tblMusicas = new javax.swing.JTable();
        lblTituloLista = new javax.swing.JLabel();
        btnReproducirLista = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblDuracionLista = new javax.swing.JLabel();
        panReproduccion = new javax.swing.JPanel();
        lblNombreMusica = new javax.swing.JLabel();
        lblTxtArtista = new javax.swing.JLabel();
        lblTxtGenero = new javax.swing.JLabel();
        lblTxtAlbum = new javax.swing.JLabel();
        lblTxtTamanio = new javax.swing.JLabel();
        lblTxtAnio = new javax.swing.JLabel();
        lblPortada = new javax.swing.JLabel();
        sldProgreso = new javax.swing.JSlider();
        btnPlayPausa = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        lblTiempoActual = new javax.swing.JLabel();
        lblArtista = new javax.swing.JLabel();
        lblAlbum = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        lblTamanio = new javax.swing.JLabel();
        lblAnio = new javax.swing.JLabel();
        tglCircular = new javax.swing.JToggleButton();
        tglContinua = new javax.swing.JToggleButton();
        lblDuracion = new javax.swing.JLabel();
        jmbMenu = new javax.swing.JMenuBar();
        menuAdministrar = new javax.swing.JMenu();
        jmiCargarMusicas = new javax.swing.JMenuItem();
        jmiBiblioteca = new javax.swing.JMenuItem();
        jmiHistorial = new javax.swing.JMenuItem();
        menuPlaylist = new javax.swing.JMenu();
        jmiNueva = new javax.swing.JMenuItem();
        jmiAdministrar = new javax.swing.JMenuItem();
        menuRequisitos = new javax.swing.JMenu();
        jmiPilaHistorial = new javax.swing.JMenuItem();
        jmiBusquedaEnArboles = new javax.swing.JMenuItem();
        jmiVisualizacionArboles = new javax.swing.JMenuItem();
        jmiRecorridos = new javax.swing.JMenuItem();

        agregarACola.setText("Agregar a cola");
        agregarACola.addActionListener(this::agregarAColaActionPerformed);
        pMenuMusicas.add(agregarACola);

        quitarDeCola.setText("Quitar de cola");
        quitarDeCola.addActionListener(this::quitarDeColaActionPerformed);
        pMenuCola.add(quitarDeCola);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panPlaylist.setBackground(new java.awt.Color(153, 204, 255));
        panPlaylist.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPlaylist.setBackground(new java.awt.Color(21, 153, 245));
        tblPlaylist.setForeground(new java.awt.Color(255, 255, 255));
        tblPlaylist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Playlist", "Id"
            }
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblPlaylist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlaylistMouseClicked(evt);
            }
        });
        tblPlaylist.getColumnModel().getColumn(0).setResizable(false);
        scpPlaylist.setViewportView(tblPlaylist);

        panPlaylist.add(scpPlaylist, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 330, 240));

        btnBiblioteca.setBackground(new java.awt.Color(21, 153, 245));
        btnBiblioteca.setFont(new java.awt.Font("Segoe UI Symbol", 3, 30)); // NOI18N
        btnBiblioteca.setForeground(new java.awt.Color(255, 255, 255));
        btnBiblioteca.setText("Biblioteca");
        btnBiblioteca.setToolTipText("");
        btnBiblioteca.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBiblioteca.setOpaque(true);
        btnBiblioteca.addActionListener(this::btnBibliotecaActionPerformed);
        panPlaylist.add(btnBiblioteca, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 40));

        lblCola.setFont(new java.awt.Font("Segoe UI Symbol", 3, 24)); // NOI18N
        lblCola.setHorizontalAlignment(SwingConstants.CENTER);
        lblCola.setText("COLA");
        panPlaylist.add(lblCola, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 320, 36));

        tblCola.setBackground(new java.awt.Color(51, 204, 255));
        tblCola.setForeground(new java.awt.Color(255, 255, 255));
        tblCola.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Musica", "Id"
            }
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblCola.getColumnModel().getColumn(0).setResizable(false);
        tblCola.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblColaMousePressed(evt);
            }
        });
        scpCola.setViewportView(tblCola);

        panPlaylist.add(scpCola, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 330, 250));

        panCanciones.setBackground(new java.awt.Color(153, 204, 255));

        tblMusicas.setBackground(new java.awt.Color(0, 93, 232));
        tblMusicas.setForeground(new java.awt.Color(255, 255, 255));
        tblMusicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Musica", "Id"
            }
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblMusicas.getColumnModel().getColumn(0).setResizable(false);
        tblMusicas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMusicasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMusicasMousePressed(evt);
            }
        });
        scpMusicas.setViewportView(tblMusicas);

        lblTituloLista.setBackground(new java.awt.Color(204, 255, 255));
        lblTituloLista.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        lblTituloLista.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloLista.setText("BIBLIOTECA");
        lblTituloLista.setMaximumSize(new java.awt.Dimension(250, 45));
        lblTituloLista.setMinimumSize(new java.awt.Dimension(250, 45));
        lblTituloLista.setPreferredSize(new java.awt.Dimension(250, 45));

        btnReproducirLista.setBackground(new java.awt.Color(0, 63, 105));
        btnReproducirLista.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnReproducirLista.setForeground(new java.awt.Color(255, 255, 255));
        btnReproducirLista.setText("↳ Reproducir lista ▶");
        btnReproducirLista.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnReproducirLista.setOpaque(true);
        btnReproducirLista.addActionListener(this::btnReproducirListaActionPerformed);

        txtBusqueda.addActionListener(this::txtBusquedaActionPerformed);

        jButton1.setText("Buscar");
        jButton1.setActionCommand("btnBusqueda");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        lblDuracionLista.setFont(new java.awt.Font("Segoe UI", 3, 10)); // NOI18N
        lblDuracionLista.setText("00 hrs 00 min");

        javax.swing.GroupLayout panCancionesLayout = new javax.swing.GroupLayout(panCanciones);
        panCanciones.setLayout(panCancionesLayout);
        panCancionesLayout.setHorizontalGroup(
            panCancionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCancionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panCancionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panCancionesLayout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(btnReproducirLista, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scpMusicas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panCancionesLayout.createSequentialGroup()
                        .addGroup(panCancionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblTituloLista, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panCancionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDuracionLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panCancionesLayout.setVerticalGroup(
            panCancionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panCancionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panCancionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTituloLista, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDuracionLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panCancionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(txtBusqueda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scpMusicas, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReproducirLista, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panReproduccion.setBackground(new java.awt.Color(73, 134, 190));

        lblNombreMusica.setBackground(new java.awt.Color(153, 204, 255));
        lblNombreMusica.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        lblNombreMusica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreMusica.setText("Nombre Musica");
        lblNombreMusica.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblNombreMusica.setText("<html><div style='width:250px;'>Nombre Musica</div></html>");

        lblTxtArtista.setBackground(new java.awt.Color(0, 153, 255));
        lblTxtArtista.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtArtista.setText("Artista:");

        lblTxtGenero.setBackground(new java.awt.Color(0, 153, 255));
        lblTxtGenero.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtGenero.setText("Genero:");

        lblTxtAlbum.setBackground(new java.awt.Color(0, 153, 255));
        lblTxtAlbum.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtAlbum.setText("Album:");

        lblTxtTamanio.setBackground(new java.awt.Color(0, 153, 255));
        lblTxtTamanio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtTamanio.setText("Tamaño:");

        lblTxtAnio.setBackground(new java.awt.Color(0, 153, 255));
        lblTxtAnio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTxtAnio.setText("Año:");

        lblPortada.setOpaque(true);

        sldProgreso.setMaximum(1000);
        sldProgreso.setValue(0);

        btnPlayPausa.setBackground(new java.awt.Color(0, 63, 105));
        btnPlayPausa.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        btnPlayPausa.setForeground(new java.awt.Color(255, 255, 255));
        btnPlayPausa.setText("▶");
        btnPlayPausa.setActionCommand("");
        btnPlayPausa.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnPlayPausa.setBorderPainted(false);
        btnPlayPausa.setOpaque(true);
        btnPlayPausa.addActionListener(this::btnPlayPausaActionPerformed);

        btnSiguiente.setBackground(new java.awt.Color(0, 63, 105));
        btnSiguiente.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        btnSiguiente.setText("⏭");
        btnSiguiente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnSiguiente.setBorderPainted(false);
        btnSiguiente.setOpaque(true);
        btnSiguiente.addActionListener(this::btnSiguienteActionPerformed);

        btnAnterior.setBackground(new java.awt.Color(0, 63, 105));
        btnAnterior.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        btnAnterior.setForeground(new java.awt.Color(255, 255, 255));
        btnAnterior.setText("⏮");
        btnAnterior.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnAnterior.setBorderPainted(false);
        btnAnterior.setOpaque(true);
        btnAnterior.addActionListener(this::btnAnteriorActionPerformed);

        lblTiempoActual.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblTiempoActual.setText("00:00");

        lblArtista.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblArtista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArtista.setText("Desconocido");
        lblArtista.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblAlbum.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblAlbum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlbum.setText("Desconocido");
        lblAlbum.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblGenero.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblGenero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGenero.setText("Desconocido");
        lblGenero.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblTamanio.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblTamanio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTamanio.setText("Desconocido");
        lblTamanio.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblAnio.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblAnio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnio.setText("Desconocido");
        lblAnio.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        tglCircular.setBackground(new java.awt.Color(0, 63, 105));
        tglCircular.setFont(new java.awt.Font("Segoe UI Symbol", 1, 10)); // NOI18N
        tglCircular.setForeground(new java.awt.Color(255, 255, 255));
        tglCircular.setText("🔁");
        tglCircular.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        tglCircular.setBorderPainted(false);
        tglCircular.setOpaque(true);
        tglCircular.addActionListener(this::tglCircularActionPerformed);

        tglContinua.setBackground(new java.awt.Color(0, 63, 105));
        tglContinua.setFont(new java.awt.Font("Segoe UI Symbol", 1, 7)); // NOI18N
        tglContinua.setForeground(new java.awt.Color(255, 255, 255));
        tglContinua.setText("▶▶");
        tglContinua.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        tglContinua.setBorderPainted(false);
        tglContinua.setOpaque(true);
        tglContinua.addActionListener(this::tglContinuaActionPerformed);

        lblDuracion.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblDuracion.setText("00:00");

        javax.swing.GroupLayout panReproduccionLayout = new javax.swing.GroupLayout(panReproduccion);
        panReproduccion.setLayout(panReproduccionLayout);
        panReproduccionLayout.setHorizontalGroup(
            panReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panReproduccionLayout.createSequentialGroup()
                .addGroup(panReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panReproduccionLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panReproduccionLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(tglContinua, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlayPausa, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tglCircular, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panReproduccionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panReproduccionLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(lblTiempoActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sldProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDuracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(panReproduccionLayout.createSequentialGroup()
                        .addGroup(panReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panReproduccionLayout.createSequentialGroup()
                                .addComponent(lblTxtArtista, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblArtista, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTxtAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panReproduccionLayout.createSequentialGroup()
                                .addComponent(lblTxtGenero)
                                .addGap(3, 3, 3)
                                .addComponent(lblGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(lblTxtAnio)
                                .addGap(3, 3, 3)
                                .addComponent(lblAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(lblTxtTamanio)
                                .addGap(3, 3, 3)
                                .addComponent(lblTamanio, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        panReproduccionLayout.setVerticalGroup(
            panReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panReproduccionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombreMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblArtista, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTxtAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTxtArtista, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTxtGenero)
                    .addComponent(lblTxtAnio)
                    .addComponent(lblAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTxtTamanio)
                    .addComponent(lblTamanio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(panReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDuracion)
                    .addComponent(sldProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTiempoActual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPlayPausa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSiguiente))
                    .addGroup(panReproduccionLayout.createSequentialGroup()
                        .addGroup(panReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tglCircular, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tglContinua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        menuAdministrar.setText("Administrar");

        jmiCargarMusicas.setText("Cargar musicas");
        jmiCargarMusicas.addActionListener(this::jmiCargarMusicasActionPerformed);
        menuAdministrar.add(jmiCargarMusicas);

        jmiBiblioteca.setText("Biblioteca General");
        jmiBiblioteca.addActionListener(this::jmiBibliotecaActionPerformed);
        menuAdministrar.add(jmiBiblioteca);

        jmiHistorial.setText("Historial");
        jmiHistorial.addActionListener(this::jmiHistorialActionPerformed);
        menuAdministrar.add(jmiHistorial);

        jmbMenu.add(menuAdministrar);

        menuPlaylist.setText("Playlists");

        jmiNueva.setText("Nueva");
        jmiNueva.addActionListener(this::jmiNuevaActionPerformed);
        menuPlaylist.add(jmiNueva);

        jmiAdministrar.setText("Administrar");
        jmiAdministrar.addActionListener(this::jmiAdministrarActionPerformed);
        menuPlaylist.add(jmiAdministrar);

        jmbMenu.add(menuPlaylist);

        menuRequisitos.setText("Requisitos");

        jmiPilaHistorial.setText("PilaHistorial");
        jmiPilaHistorial.addActionListener(this::jmiPilaHistorialActionPerformed);
        menuRequisitos.add(jmiPilaHistorial);

        jmiBusquedaEnArboles.setText("Busqueda en Arboles");
        jmiBusquedaEnArboles.addActionListener(this::jmiBusquedaEnArbolesActionPerformed);
        menuRequisitos.add(jmiBusquedaEnArboles);

        jmiVisualizacionArboles.setText("Visualizacion Arboles");
        jmiVisualizacionArboles.addActionListener(this::jmiVisualizacionArbolesActionPerformed);
        menuRequisitos.add(jmiVisualizacionArboles);

        jmiRecorridos.setText("Recorridos");
        jmiRecorridos.addActionListener(this::jmiRecorridosActionPerformed);
        menuRequisitos.add(jmiRecorridos);

        jmbMenu.add(menuRequisitos);

        setJMenuBar(jmbMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(panCanciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(panReproduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(panPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panCanciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panReproduccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void inicializar() {
        configurarTablas();
        cargarTablaMusicas(listaSeleccionada);
        cargarTablaPlaylists();
        cargarTablaCola();
        conectarReproductor();
    }
    
    private void refrescar() {
        cargarTablaMusicas(listaSeleccionada);
        cargarTablaPlaylists();
        cargarTablaCola();
        actualizarVistaReproduccion();
    } 
  
    private void configurarTablas() {
        Tabla.ocultarColumna(tblMusicas, 2);
        Tabla.ocultarColumna(tblPlaylist, 2);
        Tabla.ocultarColumna(tblCola, 2);

        Tabla.establecerAnchoMaximo(tblMusicas, 0, 45);
        Tabla.establecerAnchoMaximo(tblPlaylist, 0, 45);
        Tabla.establecerAnchoMaximo(tblCola, 0, 45);

        tblMusicas.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String texto = value == null ? "" : value.toString();

                int filaModelo = table.convertRowIndexToModel(row);
                Object valorId = table.getModel().getValueAt(filaModelo, 2);

                if (valorId != null) {
                    try {
                        int id = Integer.parseInt(valorId.toString());

                        if (reproduciendoDesdeCola) {
                            Musica musicaPendiente = listaReproduciendo == null ? null : listaReproduciendo.getActual();

                            if (listaSeleccionada == listaReproduciendo
                                    && musicaPendiente != null
                                    && id == musicaPendiente.getId()) {
                                texto = "⏳ " + texto;
                            }
                        } else {
                            if (listaSeleccionada == listaReproduciendo
                                    && musicaReproduciendo != null
                                    && id == musicaReproduciendo.getId()) {
                                texto = "🔊 " + texto;
                            }
                        }
                    } catch (NumberFormatException e) {
                        // Ignorar fila inválida
                    }
                }

                setText(texto);
                return this;
            }
        });

        tblPlaylist.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String texto = value == null ? "" : value.toString();

                int filaModelo = table.convertRowIndexToModel(row);
                Object valorId = table.getModel().getValueAt(filaModelo, 2);

                if (valorId != null && listaReproduciendo != null && musicaReproduciendo != null) {
                    try {
                        int idPlaylist = Integer.parseInt(valorId.toString());

                        for (Playlist playlist : biblioteca.getPlaylists()) {
                            if (playlist.getId() == idPlaylist && playlist.getPlaylist() == listaReproduciendo) {
                                texto = (reproduciendoDesdeCola ? "⏳ " : "🔊 ") + texto;
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        // Ignorar fila inválida
                    }
                }

                setText(texto);
                return this;
            }
        });

        tblCola.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String texto = value == null ? "" : value.toString();

                int filaModelo = table.convertRowIndexToModel(row);

                if (reproduciendoDesdeCola && filaModelo == 0) {
                    texto = "🔊 " + texto;
                }

                setText(texto);
                return this;
            }
        });
    }

    private void cargarTablaMusicas(ListaMusicas lista) {
        busquedaActiva = false;
        textoBusquedaActiva = "";

        if (lista == null) {
            listaSeleccionada = biblioteca.getBiblioteca();
            lblTituloLista.setText("BIBLIOTECA");
            lblDuracionLista.setText(biblioteca.getBiblioteca().getDuracionTotalFormateada());
        } else {
            listaSeleccionada = lista;
        }

        Tabla.cargarMusicas(tblMusicas, listaSeleccionada.listaMusicas());
    }

    private void cargarTablaPlaylists() {
        DefaultTableModel modelo = (DefaultTableModel) tblPlaylist.getModel();
        modelo.setRowCount(0);

        int no = 1;
        for (Playlist playlist : biblioteca.getPlaylists()) {
            modelo.addRow(new Object[]{
                no++,
                playlist.getNombre(),
                playlist.getId()
            });
        }
    }

    private void cargarTablaCola() {
        Tabla.cargarMusicas(tblCola, colaReproduccion.listaMusicas());

        if (reproduciendoDesdeCola) {
            lblCola.setText("🔊 COLA (" + colaReproduccion.getTamanio() + ")");
        } else if (colaReproduccion.estaVacia()) {
            lblCola.setText("COLA");
        } else {
            lblCola.setText("COLA (" + colaReproduccion.getTamanio() + ")");
        }

        tblCola.repaint();
    }
    
    private void mostrarBiblioteca() {
        listaSeleccionada = biblioteca.getBiblioteca();
        cargarTablaMusicas(listaSeleccionada);
        lblTituloLista.setText("BIBLIOTECA");
        lblDuracionLista.setText(biblioteca.getBiblioteca().getDuracionTotalFormateada());

        if (listaSeleccionada == listaReproduciendo) {
            musicaSeleccionada = musicaReproduciendo;
        } else {
            musicaSeleccionada = null;
        }

        actualizarVistaReproduccion();
    }
    
    private void mostrarPlaylist(int id) {
        Playlist playlist = biblioteca.buscarPlaylistPorId(id);

        if (playlist == null) {
            return;
        }

        listaSeleccionada = playlist.getPlaylist();
        cargarTablaMusicas(listaSeleccionada);
        lblTituloLista.setText(playlist.getNombre());
        lblDuracionLista.setText(playlist.getPlaylist().getDuracionTotalFormateada());

        if (listaSeleccionada == listaReproduciendo) {
            musicaSeleccionada = musicaReproduciendo;
        } else {
            musicaSeleccionada = null;
        }

        actualizarVistaReproduccion();
    }

    private void reproducirMusica(Musica musica, GestorHistorial.Origen origen, String nombreOrigen) {
        if (musica == null) {
            return;
        }

        registrarReproduccion(musica, origen, nombreOrigen);
        musicaReproduciendo = musica;

        lblNombreMusica.setText("<html><div style='width:250px; text-align:center;'>" + musica.getNombre() + "</div></html>");
        lblArtista.setText("<html><div style='width:100px; text-align:center;'>" + musica.getArtista() + "</div></html>");
        lblAlbum.setText("<html><div style='width:100px; text-align:center;'>" + musica.getAlbum() + "</div></html>");
        lblGenero.setText("<html><div style='width:80px; text-align:center;'>" + musica.getGenero() + "</div></html>");
        lblTamanio.setText(musica.formatearTamanio());
        lblAnio.setText(musica.anioReal());
        lblDuracion.setText(musica.formatearDuracion());
        lblTiempoActual.setText("00:00");
        duracionActualSegundos = 0;

        actualizandoSlider = true;
        sldProgreso.setValue(0);
        actualizandoSlider = false;

        lblPortada.setIcon(GestorPortada.obtenerPortadaGrande(musica, 425, 298));

        actualizarVistaReproduccion();

        reproductor.reproducir(musica);
        btnPlayPausa.setText("⏸");
    }
    
    private void registrarReproduccion(Musica musica, GestorHistorial.Origen origen, String nombreOrigen) {
        if (musica == null) {
            return;
        }

        musica.aumentarReproducciones();
        pilaHistorial.push(musica);

        biblioteca.getHistorial().registrarReproduccion(
                musica,
                origen,
                nombreOrigen
        );
    }
    
    private void conectarReproductor() {
        reproductor.setEventos(new GestorReproductor.Eventos() {
            @Override
            public void alActualizarTiempo(double actual, double total) {
                if (musicaReproduciendo == null) return;

                duracionActualSegundos = total;
                lblTiempoActual.setText(formatearTiempo(actual));

                if (total > 0) {
                    lblDuracion.setText(musicaReproduciendo.formatearDuracion());
                }

                if (total > 0 && !sldProgreso.getValueIsAdjusting()) {
                    actualizandoSlider = true;

                    int valor = (int) Math.round((actual / total) * 1000);
                    valor = Math.max(0, Math.min(1000, valor));

                    sldProgreso.setValue(valor);
                    actualizandoSlider = false;
                }
            }

            @Override
            public void alCambiarEstado(boolean reproduciendo) {
                btnPlayPausa.setText(reproduciendo ? "⏸" : "▶");
            }
            @Override
            public void alTerminar() {
                btnPlayPausa.setText("▶");

                if (reproduciendoDesdeCola) {
                    colaReproduccion.desencolar();

                    if (!tglContinua.isSelected()) {
                        finalizarReproduccion();
                        return;
                    }

                    if (!colaReproduccion.estaVacia()) {
                        reproducirCola();
                        return;
                    }

                    reproduciendoDesdeCola = false;
                    cargarTablaCola();
                    actualizarVistaReproduccion();

                    if (!reproducirSiguiente()) {
                        finalizarReproduccion();
                    }

                    return;
                }

                if (!tglContinua.isSelected()) {
                    finalizarReproduccion();
                    return;
                }

                if (!reproducirSiguiente()) {
                    finalizarReproduccion();
                }
            }
            @Override
            public void alError(String mensaje) {
                JOptionPane.showMessageDialog(VentanaPrincipal.this, mensaje);

                if (reproduciendoDesdeCola) {
                    colaReproduccion.desencolar();
                }

                finalizarReproduccion();
            }
        });

        sldProgreso.addChangeListener((ChangeEvent e) -> {
            if (actualizandoSlider || duracionActualSegundos <= 0) {
                return;
            }

            double segundos = (sldProgreso.getValue() / 1000.0) * duracionActualSegundos;

            if (sldProgreso.getValueIsAdjusting()) {
                lblTiempoActual.setText(formatearTiempo(segundos));
            } else {
                reproductor.moverA(segundos);
            }
        });
    }
    
    private String formatearTiempo(double segundos) {
        if (Double.isNaN(segundos) || Double.isInfinite(segundos) || segundos < 0) {
            return "00:00";
        }

        int total = (int) segundos;
        int minutos = total / 60;
        int segundosRestantes = total % 60;

        return String.format("%02d:%02d", minutos, segundosRestantes);
    }
    
    private int idSeleccion(JTable tabla) {
        int fila = tabla.getSelectedRow();
        if (fila < 0) return 0;

        int filaModelo = tabla.convertRowIndexToModel(fila);
        Object valor = tabla.getModel().getValueAt(filaModelo, 2);
        if (valor == null)return 0;

        int id;
        try {
            id = Integer.parseInt(valor.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
        if (id <= 0) return 0;
        return id;
    }
    
    private boolean reproducirCola() {
        Musica musicaCola = colaReproduccion.peek();

        if (musicaCola == null) {
            reproduciendoDesdeCola = false;
            cargarTablaCola();
            actualizarVistaReproduccion();
            return false;
        }

        reproduciendoDesdeCola = true;
        musicaReproduciendo = musicaCola;

        reproducirMusica(
                musicaCola,
                GestorHistorial.Origen.COLA,
                "Cola de reproducción"
        );

        cargarTablaCola();
        actualizarVistaReproduccion();
        return true;
    }
    
    private boolean reproducirSiguiente() {
        boolean veniaDeCola = reproduciendoDesdeCola;

        if (reproduciendoDesdeCola) {
            colaReproduccion.desencolar();

            if (!colaReproduccion.estaVacia()) {
                return reproducirCola();
            }

            reproduciendoDesdeCola = false;
            cargarTablaCola();
            actualizarVistaReproduccion();
        }

        if (!colaReproduccion.estaVacia()) {
            return reproducirCola();
        }

        if (listaReproduciendo == null) {
            if (veniaDeCola) {
                reproductor.detener();
                finalizarReproduccion();
            }

            return false;
        }

        listaReproduciendo.setCircular(tglCircular.isSelected());

        Musica siguiente = listaReproduciendo.avanzar();

        if (siguiente == null) {
            if (veniaDeCola) {
                reproductor.detener();
                finalizarReproduccion();
            }

            return false;
        }

        reproduciendoDesdeCola = false;

        if (listaSeleccionada == listaReproduciendo) {
            musicaSeleccionada = siguiente;
        }

        reproducirMusica(siguiente, obtenerOrigenDeLista(listaReproduciendo), obtenerNombreOrigenDeLista(listaReproduciendo));
        cargarTablaCola();
        actualizarVistaReproduccion();
        return true;
    }
    
    private boolean reproducirAnterior() {
        if (reproduciendoDesdeCola) {
            return false;
        }

        if (listaReproduciendo == null) {
            return false;
        }

        listaReproduciendo.setCircular(tglCircular.isSelected());

        Musica anterior = listaReproduciendo.retroceder();

        if (anterior == null) {
            return false;
        }

        reproduciendoDesdeCola = false;

        if (listaSeleccionada == listaReproduciendo) {
            musicaSeleccionada = anterior;
        }

        reproducirMusica(anterior, obtenerOrigenDeLista(listaReproduciendo), obtenerNombreOrigenDeLista(listaReproduciendo));
        cargarTablaCola();
        actualizarVistaReproduccion();

        return true;
    }
    
    private void finalizarReproduccion(boolean limpiarDatos) {
        reproduciendoDesdeCola = false;
        musicaReproduciendo = null;
        musicaSeleccionada = null;

        btnPlayPausa.setText("▶");
        lblTiempoActual.setText("00:00");
        duracionActualSegundos = 0;

        actualizandoSlider = true;
        sldProgreso.setValue(0);
        actualizandoSlider = false;

        if (limpiarDatos) {
            limpiarDatosEnPantalla();
        }

        cargarTablaCola();
        actualizarVistaReproduccion();
    }
    
    private void finalizarReproduccion() {
        finalizarReproduccion(false);
    }
    
    private boolean seleccionarFilaPorId(JTable tabla, int id) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            int filaModelo = tabla.convertRowIndexToModel(i);
            Object valor = tabla.getModel().getValueAt(filaModelo, 2);
            if (valor == null) {
                continue;
            }
            try {
                int idFila = Integer.parseInt(valor.toString());

                if (idFila == id) {
                    tabla.setRowSelectionInterval(i, i);
                    tabla.scrollRectToVisible(tabla.getCellRect(i, 0, true));
                    return true;
                }
            } catch (NumberFormatException e) {
                // Ignorar fila inválida
            }
        }
        return false;
    }
    
    private void actualizarVistaReproduccion() {
        tblMusicas.clearSelection();

        if (musicaSeleccionada != null) {
            seleccionarFilaPorId(tblMusicas, musicaSeleccionada.getId());
        }

        tblPlaylist.clearSelection();

        for (Playlist playlist : biblioteca.getPlaylists()) {
            if (playlist.getPlaylist() == listaSeleccionada) {
                seleccionarFilaPorId(tblPlaylist, playlist.getId());
                lblTituloLista.setText(playlist.getNombre());
                lblDuracionLista.setText(playlist.getPlaylist().getDuracionTotalFormateada());
                break;
            }
        }

        if (musicaReproduciendo != null && listaReproduciendo == biblioteca.getBiblioteca()) {
            btnBiblioteca.setText(reproduciendoDesdeCola ? "⏳ Biblioteca" : "🔊 Biblioteca");
        } else {
            btnBiblioteca.setText("Biblioteca");
        }

        tblMusicas.repaint();
        tblPlaylist.repaint();
        tblCola.repaint();
    }
    
    private void buscarMusicas() {
        String texto = txtBusqueda.getText();
        List<Musica> resultados;

        busquedaActiva = texto != null && !texto.trim().isEmpty();
        textoBusquedaActiva = busquedaActiva ? texto.trim() : "";

        if (listaSeleccionada != biblioteca.getBiblioteca()) {
            resultados = listaSeleccionada.buscarMusicas(texto);
        } else {
            resultados = biblioteca.buscarMusicas(texto);
        }

        if (busquedaActiva) {
            biblioteca.getHistorial().registrarBusqueda(
                    textoBusquedaActiva,
                    resultados.size(),
                    obtenerOrigenDeLista(listaSeleccionada),
                    obtenerNombreOrigenDeLista(listaSeleccionada)
            );
        }

        Tabla.cargarMusicas(tblMusicas, resultados);
        actualizarVistaReproduccion();
    }
    
    private GestorHistorial.Origen obtenerOrigenTablaMusicas() {
        if (busquedaActiva) {
            return GestorHistorial.Origen.BUSQUEDA;
        }

        return obtenerOrigenDeLista(listaSeleccionada);
    }

    private String obtenerNombreOrigenTablaMusicas() {
        if (busquedaActiva) {
            return "Búsqueda en " + obtenerNombreOrigenDeLista(listaSeleccionada)
                    + ": " + textoBusquedaActiva;
        }

        return obtenerNombreOrigenDeLista(listaSeleccionada);
    }

    private GestorHistorial.Origen obtenerOrigenDeLista(ListaMusicas lista) {
        if (lista == null) {
            return GestorHistorial.Origen.DESCONOCIDO;
        }

        if (lista == biblioteca.getBiblioteca()) {
            return GestorHistorial.Origen.BIBLIOTECA;
        }

        Playlist playlist = obtenerPlaylistDeLista(lista);

        if (playlist != null) {
            return GestorHistorial.Origen.PLAYLIST;
        }

        return GestorHistorial.Origen.DESCONOCIDO;
    }

    private String obtenerNombreOrigenDeLista(ListaMusicas lista) {
        if (lista == null) {
            return "Origen desconocido";
        }

        if (lista == biblioteca.getBiblioteca()) {
            return "Biblioteca general";
        }

        Playlist playlist = obtenerPlaylistDeLista(lista);

        if (playlist != null) {
            return "Playlist " + playlist.getNombre();
        }

        return "Origen desconocido";
    }

    private Playlist obtenerPlaylistDeLista(ListaMusicas lista) {
        if (lista == null) {
            return null;
        }

        for (Playlist playlist : biblioteca.getPlaylists()) {
            if (playlist.getPlaylist() == lista) {
                return playlist;
            }
        }

        return null;
    }
    
    private void manejarCambiosAdministracion(int idPlaylistAdministrada, ListaMusicas listaPlaylistAntes) {
        Playlist playlistDespues = null;
        boolean listaFueEliminada = false;
        boolean musicaActualYaNoExiste = false;
        boolean musicaActualYaNoEstaEnPlaylist = false;

        if (idPlaylistAdministrada > 0) {
            playlistDespues = biblioteca.buscarPlaylistPorId(idPlaylistAdministrada);

            if (playlistDespues == null && listaPlaylistAntes != null) {
                listaFueEliminada = true;

                if (listaSeleccionada == listaPlaylistAntes) {
                    listaSeleccionada = biblioteca.getBiblioteca();
                    lblTituloLista.setText("BIBLIOTECA");
                    lblDuracionLista.setText(biblioteca.getBiblioteca().getDuracionTotalFormateada());
                }
            } else if (playlistDespues != null) {
                if (listaSeleccionada == playlistDespues.getPlaylist()) {
                    lblTituloLista.setText(playlistDespues.getNombre());
                    lblDuracionLista.setText(playlistDespues.getPlaylist().getDuracionTotalFormateada());
                }

                if (listaReproduciendo == playlistDespues.getPlaylist()
                        && musicaReproduciendo != null
                        && !playlistDespues.contieneMusica(musicaReproduciendo)) {
                    musicaActualYaNoEstaEnPlaylist = true;
                }
            }
        }

        if (musicaReproduciendo != null
                && biblioteca.buscarPorId(musicaReproduciendo.getId()) == null) {
            musicaActualYaNoExiste = true;
        }

        boolean musicaActualFueAfectada =
                listaFueEliminada && listaReproduciendo == listaPlaylistAntes && musicaReproduciendo != null
                || musicaActualYaNoExiste
                || musicaActualYaNoEstaEnPlaylist;

        if (musicaActualFueAfectada) {
            continuarDespuesDeEliminarActual(listaFueEliminada);
        }

        cargarTablaMusicas(listaSeleccionada);
        cargarTablaPlaylists();
        cargarTablaCola();
        actualizarVistaReproduccion();
    }
    
    private void continuarDespuesDeEliminarActual(boolean listaFueEliminada) {
        reproductor.detener();

        if (!tglContinua.isSelected()) {
            finalizarReproduccion(true);
            return;
        }

        if (!colaReproduccion.estaVacia()) {
            reproducirCola();
            return;
        }

        if (listaFueEliminada || listaReproduciendo == null) {
            finalizarReproduccion(true);
            return;
        }

        Musica siguiente = listaReproduciendo.getActual();

        if (siguiente == null || biblioteca.buscarPorId(siguiente.getId()) == null) {
            finalizarReproduccion(true);
            return;
        }

        musicaSeleccionada = listaSeleccionada == listaReproduciendo ? siguiente : null;

        reproducirMusica(
                siguiente,
                obtenerOrigenDeLista(listaReproduciendo),
                obtenerNombreOrigenDeLista(listaReproduciendo)
        );

        cargarTablaCola();
        actualizarVistaReproduccion();
    }
    
    private void limpiarDatosEnPantalla() {
        lblNombreMusica.setText("Sin reproducción");
        lblArtista.setText("-");
        lblAlbum.setText("-");
        lblGenero.setText("-");
        lblTamanio.setText("-");
        lblAnio.setText("-");
        lblDuracion.setText("00:00");
        lblTiempoActual.setText("00:00");

        duracionActualSegundos = 0;

        actualizandoSlider = true;
        sldProgreso.setValue(0);
        actualizandoSlider = false;

        lblPortada.setIcon(GestorPortada.obtenerPortadaGrande(null, 425, 298));
    }
    
    private void jmiCargarMusicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCargarMusicasActionPerformed
        DialogoCargaMusicas interfazCargaDeMusicas = new DialogoCargaMusicas(this, true);
        interfazCargaDeMusicas.setVisible(true);
        refrescar();
    }//GEN-LAST:event_jmiCargarMusicasActionPerformed

    private void btnBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBibliotecaActionPerformed
        mostrarBiblioteca();
    }//GEN-LAST:event_btnBibliotecaActionPerformed
    
    private void tblPlaylistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlaylistMouseClicked
        if (evt.getClickCount() != 2) {
            return;
        }
        mostrarPlaylist(idSeleccion(tblPlaylist));
    }//GEN-LAST:event_tblPlaylistMouseClicked

    private void tblMusicasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMusicasMouseClicked
        Musica musica = biblioteca.buscarPorId(idSeleccion(tblMusicas));

        if (musica == null) {
            return;
        }

        musicaSeleccionada = musica;

        if (evt.getClickCount() != 2) {
            return;
        }

        GestorHistorial.Origen origen = obtenerOrigenTablaMusicas();
        String nombreOrigen = obtenerNombreOrigenTablaMusicas();

        listaReproduciendo = listaSeleccionada;

        if (listaReproduciendo != null) {
            listaReproduciendo.seleccionarMusica(musicaSeleccionada);
            listaReproduciendo.setCircular(tglCircular.isSelected());
        }

        if (reproduciendoDesdeCola) {
            colaReproduccion.desencolar();
        }

        reproduciendoDesdeCola = false;
        reproducirMusica(musicaSeleccionada, origen, nombreOrigen);

        cargarTablaCola();
        actualizarVistaReproduccion();
    }//GEN-LAST:event_tblMusicasMouseClicked

    private void btnReproducirListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReproducirListaActionPerformed
        if (listaSeleccionada.estaVacia()) {
            JOptionPane.showMessageDialog(this, "Seleccione una lista para reproducir.");
            return;
        }

        if (reproduciendoDesdeCola) {
            colaReproduccion.desencolar();
        }

        musicaSeleccionada = listaSeleccionada.seleccionarPrimera();
        listaReproduciendo = listaSeleccionada;
        reproduciendoDesdeCola = false;

        reproducirMusica(
                musicaSeleccionada,
                obtenerOrigenDeLista(listaReproduciendo),
                obtenerNombreOrigenDeLista(listaReproduciendo)
        );

        tglContinua.setSelected(true);

        cargarTablaCola();
        actualizarVistaReproduccion();
    }//GEN-LAST:event_btnReproducirListaActionPerformed

    private void tglContinuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglContinuaActionPerformed

    }//GEN-LAST:event_tglContinuaActionPerformed

    private void tglCircularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglCircularActionPerformed
        if (listaReproduciendo != null) {
            listaReproduciendo.setCircular(tglCircular.isSelected());
        }
    }//GEN-LAST:event_tglCircularActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        reproducirAnterior();
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        reproducirSiguiente();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnPlayPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayPausaActionPerformed
        if (musicaReproduciendo != null) {
            reproductor.alternarPausa();
            return;
        }

        if (!colaReproduccion.estaVacia()) {
            reproducirCola();
            return;
        }

        Musica musicaTabla = biblioteca.buscarPorId(idSeleccion(tblMusicas));
        if (musicaTabla != null) {
            GestorHistorial.Origen origen = obtenerOrigenTablaMusicas();
            String nombreOrigen = obtenerNombreOrigenTablaMusicas();

            musicaSeleccionada = musicaTabla;
            listaReproduciendo = listaSeleccionada;

            if (listaReproduciendo != null) {
                listaReproduciendo.seleccionarMusica(musicaSeleccionada);
                listaReproduciendo.setCircular(tglCircular.isSelected());
            }

            reproduciendoDesdeCola = false;

            reproducirMusica(musicaSeleccionada, origen, nombreOrigen);

            cargarTablaCola();
            actualizarVistaReproduccion();
            return;
        }

        Musica actualLista = listaReproduciendo == null ? null : listaReproduciendo.getActual();

        if (actualLista != null) {
            reproduciendoDesdeCola = false;
            listaReproduciendo.setCircular(tglCircular.isSelected());

            if (listaSeleccionada == listaReproduciendo) {
                musicaSeleccionada = actualLista;
            } else {
                musicaSeleccionada = null;
            }

            reproducirMusica(
                    actualLista,
                    obtenerOrigenDeLista(listaReproduciendo),
                    obtenerNombreOrigenDeLista(listaReproduciendo)
            );

            cargarTablaCola();
            actualizarVistaReproduccion();
            return;
        }

        JOptionPane.showMessageDialog(this, "Selecciona una música de\n  la lista para reproducir\n                  ⬅️");
    }//GEN-LAST:event_btnPlayPausaActionPerformed

    private void jmiNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNuevaActionPerformed
        String nombre = JOptionPane.showInputDialog(this, "Nombre de la playlist:");
        if (nombre == null) return; // cancelo
        nombre = nombre.trim();
        if (nombre.isBlank()) {
            JOptionPane.showMessageDialog(this, "Nombre invalido.");
            return;
        }
        if (biblioteca.existePlaylist(nombre)) {
            JOptionPane.showMessageDialog(this, "Ya existe una playlist con ese nombre.");
            return;
        }
        DialogoNuevaPlaylist dialogo = new DialogoNuevaPlaylist(this, true, nombre);
        dialogo.setVisible(true);
        refrescar();
    }//GEN-LAST:event_jmiNuevaActionPerformed

    private void tblMusicasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMusicasMousePressed
        // 1. Obtener la fila exacta donde se hizo clic
        int row = tblMusicas.rowAtPoint(evt.getPoint());

        // 2. Validar que el clic sea dentro de una fila válida (tabla no vacía)
        if (row >= 0 && row < tblMusicas.getRowCount()) {

            // 3. Seleccionar la fila detectada
            tblMusicas.setRowSelectionInterval(row, row);

            // 4. Mostrar el menú solo si es un clic derecho legítimo
            if (evt.isPopupTrigger() || SwingUtilities.isRightMouseButton(evt)) {
                pMenuMusicas.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tblMusicasMousePressed

    private void agregarAColaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarAColaActionPerformed
        Musica musica = biblioteca.buscarPorId(idSeleccion(tblMusicas));

        if (musica == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una música para agregar a la cola.");
            return;
        }

        if (colaReproduccion.encolar(musica)) {
            cargarTablaCola();
        }
    }//GEN-LAST:event_agregarAColaActionPerformed

    private void quitarDeColaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarDeColaActionPerformed
        int fila = tblCola.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una música de la cola.");
            return;
        }
        int posicion = tblCola.convertRowIndexToModel(fila);
        if (reproduciendoDesdeCola && posicion == 0) {
            JOptionPane.showMessageDialog(this, "No puedes quitar la música que se está reproduciendo desde la cola.");
            return;
        }
        if (colaReproduccion.eliminarEnPosicion(posicion)) {
            cargarTablaCola();
            actualizarVistaReproduccion();
        }
    }//GEN-LAST:event_quitarDeColaActionPerformed

    private void tblColaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblColaMousePressed
        int row = tblCola.rowAtPoint(evt.getPoint());

        if (row >= 0 && row < tblCola.getRowCount()) {
            tblCola.setRowSelectionInterval(row, row);

            if (evt.isPopupTrigger() || SwingUtilities.isRightMouseButton(evt)) {
                pMenuCola.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tblColaMousePressed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        buscarMusicas();
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscarMusicas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jmiBusquedaEnArbolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiBusquedaEnArbolesActionPerformed
        DialogoBusquedaEnArboles dialogo = new DialogoBusquedaEnArboles(this, false);
        dialogo.setVisible(true);
        refrescar();
    }//GEN-LAST:event_jmiBusquedaEnArbolesActionPerformed

    private void jmiPilaHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPilaHistorialActionPerformed
        DialogoPilaHistorial dialogo = new DialogoPilaHistorial(this, false);
        dialogo.setVisible(true);
        refrescar();
    }//GEN-LAST:event_jmiPilaHistorialActionPerformed

    private void jmiVisualizacionArbolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVisualizacionArbolesActionPerformed
        DialogoVisualizacionArboles dialogo = new DialogoVisualizacionArboles(this, false);
        dialogo.setVisible(true);
        refrescar();
    }//GEN-LAST:event_jmiVisualizacionArbolesActionPerformed

    private void jmiRecorridosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRecorridosActionPerformed
        DialogoRecorrido dialogo = new DialogoRecorrido(this, false);
        dialogo.setVisible(true);
        refrescar();
    }//GEN-LAST:event_jmiRecorridosActionPerformed

    private void jmiHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiHistorialActionPerformed
        DialogoHistorial dialogo = new DialogoHistorial(this, false);
        dialogo.setVisible(true);
        refrescar();
    }//GEN-LAST:event_jmiHistorialActionPerformed

    private void jmiAdministrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAdministrarActionPerformed
        int idPlaylist = idSeleccion(tblPlaylist);

        if (idPlaylist <= 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una Playlist para administrar");
            return;
        }

        Playlist playlistAntes = biblioteca.buscarPlaylistPorId(idPlaylist);
        ListaMusicas listaPlaylistAntes = playlistAntes == null ? null : playlistAntes.getPlaylist();

        DialogoAdministrarPlaylist dialogo = new DialogoAdministrarPlaylist(this, true, idPlaylist);
        dialogo.setVisible(true);

        manejarCambiosAdministracion(idPlaylist, listaPlaylistAntes);
    }//GEN-LAST:event_jmiAdministrarActionPerformed

    private void jmiBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiBibliotecaActionPerformed
        DialogoBibliotecaGeneral dialogo = new DialogoBibliotecaGeneral(this, true);
        dialogo.setVisible(true);

        manejarCambiosAdministracion(0, null);
    }//GEN-LAST:event_jmiBibliotecaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem agregarACola;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBiblioteca;
    private javax.swing.JButton btnPlayPausa;
    private javax.swing.JButton btnReproducirLista;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuBar jmbMenu;
    private javax.swing.JMenuItem jmiAdministrar;
    private javax.swing.JMenuItem jmiBiblioteca;
    private javax.swing.JMenuItem jmiBusquedaEnArboles;
    private javax.swing.JMenuItem jmiCargarMusicas;
    private javax.swing.JMenuItem jmiHistorial;
    private javax.swing.JMenuItem jmiNueva;
    private javax.swing.JMenuItem jmiPilaHistorial;
    private javax.swing.JMenuItem jmiRecorridos;
    private javax.swing.JMenuItem jmiVisualizacionArboles;
    private javax.swing.JLabel lblAlbum;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblArtista;
    private javax.swing.JLabel lblCola;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblDuracionLista;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblNombreMusica;
    private javax.swing.JLabel lblPortada;
    private javax.swing.JLabel lblTamanio;
    private javax.swing.JLabel lblTiempoActual;
    private javax.swing.JLabel lblTituloLista;
    private javax.swing.JLabel lblTxtAlbum;
    private javax.swing.JLabel lblTxtAnio;
    private javax.swing.JLabel lblTxtArtista;
    private javax.swing.JLabel lblTxtGenero;
    private javax.swing.JLabel lblTxtTamanio;
    private javax.swing.JMenu menuAdministrar;
    private javax.swing.JMenu menuPlaylist;
    private javax.swing.JMenu menuRequisitos;
    private javax.swing.JPopupMenu pMenuCola;
    private javax.swing.JPopupMenu pMenuMusicas;
    private javax.swing.JPanel panCanciones;
    private javax.swing.JPanel panPlaylist;
    private javax.swing.JPanel panReproduccion;
    private javax.swing.JMenuItem quitarDeCola;
    private javax.swing.JScrollPane scpCola;
    private javax.swing.JScrollPane scpMusicas;
    private javax.swing.JScrollPane scpPlaylist;
    private javax.swing.JSlider sldProgreso;
    private javax.swing.JTable tblCola;
    private javax.swing.JTable tblMusicas;
    private javax.swing.JTable tblPlaylist;
    private javax.swing.JToggleButton tglCircular;
    private javax.swing.JToggleButton tglContinua;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}