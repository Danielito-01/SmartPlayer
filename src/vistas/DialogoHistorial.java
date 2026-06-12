package vistas;

public class DialogoHistorial extends javax.swing.JDialog {
    
    public DialogoHistorial(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabHistorial = new javax.swing.JTabbedPane();
        panCargas = new javax.swing.JPanel();
        scpCargas = new javax.swing.JScrollPane();
        jtxtCargas = new javax.swing.JTextArea();
        panBusquedas = new javax.swing.JPanel();
        scpBusquedas = new javax.swing.JScrollPane();
        jtxtBusquedas = new javax.swing.JTextArea();
        panEliminaciones = new javax.swing.JPanel();
        scpEliminaciones = new javax.swing.JScrollPane();
        jtxtEliminaciones = new javax.swing.JTextArea();
        panPlaylist = new javax.swing.JPanel();
        scpPlaylist = new javax.swing.JScrollPane();
        jtxtPlaylist = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtxtCargas.setColumns(20);
        jtxtCargas.setRows(5);
        scpCargas.setViewportView(jtxtCargas);

        javax.swing.GroupLayout panCargasLayout = new javax.swing.GroupLayout(panCargas);
        panCargas.setLayout(panCargasLayout);
        panCargasLayout.setHorizontalGroup(
            panCargasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCargasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpCargas, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
                .addContainerGap())
        );
        panCargasLayout.setVerticalGroup(
            panCargasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCargasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpCargas, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabHistorial.addTab("Cargas", panCargas);

        jtxtBusquedas.setColumns(20);
        jtxtBusquedas.setRows(5);
        scpBusquedas.setViewportView(jtxtBusquedas);

        javax.swing.GroupLayout panBusquedasLayout = new javax.swing.GroupLayout(panBusquedas);
        panBusquedas.setLayout(panBusquedasLayout);
        panBusquedasLayout.setHorizontalGroup(
            panBusquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBusquedasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpBusquedas, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE))
        );
        panBusquedasLayout.setVerticalGroup(
            panBusquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBusquedasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpBusquedas, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
        );

        tabHistorial.addTab("Busquedas", panBusquedas);

        jtxtEliminaciones.setColumns(20);
        jtxtEliminaciones.setRows(5);
        scpEliminaciones.setViewportView(jtxtEliminaciones);

        javax.swing.GroupLayout panEliminacionesLayout = new javax.swing.GroupLayout(panEliminaciones);
        panEliminaciones.setLayout(panEliminacionesLayout);
        panEliminacionesLayout.setHorizontalGroup(
            panEliminacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpEliminaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
        );
        panEliminacionesLayout.setVerticalGroup(
            panEliminacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEliminacionesLayout.createSequentialGroup()
                .addComponent(scpEliminaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabHistorial.addTab("Eliminaciones", panEliminaciones);

        jtxtPlaylist.setColumns(20);
        jtxtPlaylist.setRows(5);
        scpPlaylist.setViewportView(jtxtPlaylist);

        javax.swing.GroupLayout panPlaylistLayout = new javax.swing.GroupLayout(panPlaylist);
        panPlaylist.setLayout(panPlaylistLayout);
        panPlaylistLayout.setHorizontalGroup(
            panPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPlaylistLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
                .addContainerGap())
        );
        panPlaylistLayout.setVerticalGroup(
            panPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPlaylistLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scpPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabHistorial.addTab("Playlist", panPlaylist);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Historial General");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabHistorial)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(361, 361, 361)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(tabHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea jtxtBusquedas;
    private javax.swing.JTextArea jtxtCargas;
    private javax.swing.JTextArea jtxtEliminaciones;
    private javax.swing.JTextArea jtxtPlaylist;
    private javax.swing.JPanel panBusquedas;
    private javax.swing.JPanel panCargas;
    private javax.swing.JPanel panEliminaciones;
    private javax.swing.JPanel panPlaylist;
    private javax.swing.JScrollPane scpBusquedas;
    private javax.swing.JScrollPane scpCargas;
    private javax.swing.JScrollPane scpEliminaciones;
    private javax.swing.JScrollPane scpPlaylist;
    private javax.swing.JTabbedPane tabHistorial;
    // End of variables declaration//GEN-END:variables
}
