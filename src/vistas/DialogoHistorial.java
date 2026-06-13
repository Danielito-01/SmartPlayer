package vistas;

import estructuras.BibliotecaGeneral;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import servicios.GestorHistorial;
import utilidades.Presentacion;
import utilidades.Tabla;

public class DialogoHistorial extends javax.swing.JDialog {
    private final BibliotecaGeneral biblioteca = BibliotecaGeneral.getInstance();
    private List<GestorHistorial.RegistroHistorial> registrosMostrados = new ArrayList<>();
    
    public DialogoHistorial(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializar();
        Presentacion.aplicarHistorial(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        lblBuscar = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistorial = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDetalle = new javax.swing.JTextArea();
        lblMostrados = new javax.swing.JLabel();
        lblGeneral = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(51, 51, 51));
        lblTitulo.setText("Historial General");

        lblTipo.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(51, 51, 51));
        lblTipo.setText("Tipo:");

        lblBuscar.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(51, 51, 51));
        lblBuscar.setText("Buscar:");

        btnBuscar.setText("Buscar");

        btnLimpiar.setText("Limpiar");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        tblHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Fecha", "Tipo", "Resultado", "Acción", "Resumen", "Id"
            }
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }
    );
    tblHistorial.getColumnModel().getColumn(0).setResizable(false);
    jScrollPane1.setViewportView(tblHistorial);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
            .addContainerGap())
    );

    txtDetalle.setEditable(false);
    txtDetalle.setColumns(20);
    txtDetalle.setLineWrap(true);
    txtDetalle.setRows(5);
    txtDetalle.setWrapStyleWord(true);
    jScrollPane2.setViewportView(txtDetalle);

    lblMostrados.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
    lblMostrados.setForeground(new java.awt.Color(51, 51, 51));
    lblMostrados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    lblMostrados.setText("Registros mostrados: 0");
    lblMostrados.setVerticalAlignment(javax.swing.SwingConstants.TOP);

    lblGeneral.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
    lblGeneral.setForeground(new java.awt.Color(51, 51, 51));
    lblGeneral.setText("Total general: 0");

    jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(51, 51, 51));
    jLabel1.setText("Detalles:");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(cmbTipo, 0, 212, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(lblBuscar)
                    .addGap(6, 6, 6)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(btnBuscar)
                    .addGap(27, 27, 27)
                    .addComponent(btnLimpiar)
                    .addGap(228, 228, 228)
                    .addComponent(jLabel1)
                    .addGap(151, 151, 151))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lblMostrados, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(73, 73, 73))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTitulo)
            .addGap(364, 364, 364))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblTitulo)
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTipo)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblBuscar)
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnBuscar)
                                .addComponent(btnLimpiar))))
                    .addGap(18, 18, 18))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(64, 64, 64)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMostrados)
                        .addComponent(lblGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
            .addContainerGap(17, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicializar() {
        setTitle("Historial General");
        setLocationRelativeTo(getParent());
        configurarTablaHistorial();
        cargarTipos();
        configurarEventos();
        cargarHistorialCompleto();
    }

    private void configurarTablaHistorial() {
        tblHistorial.setRowHeight(24);
        Tabla.ocultarColumna(tblHistorial, 6);
        txtDetalle.setText("");
        Tabla.establecerAnchoMaximo(tblHistorial, 0, 55);
    }

    private void configurarEventos() {
        btnBuscar.addActionListener(e -> aplicarFiltros());

        btnLimpiar.addActionListener(e -> {
            txtBusqueda.setText("");
            cmbTipo.setSelectedItem("TODOS");
            cargarHistorialCompleto();
        });

        txtBusqueda.addActionListener(e -> aplicarFiltros());

        cmbTipo.addActionListener(e -> aplicarFiltros());

        tblHistorial.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetalleSeleccionado();
            }
        });
    }

    private void cargarTipos() {
        cmbTipo.removeAllItems();
        cmbTipo.addItem("TODOS");

        for (GestorHistorial.Tipo tipo : GestorHistorial.Tipo.values()) {
            cmbTipo.addItem(tipo.name());
        }
    }

    private void cargarHistorialCompleto() {
        registrosMostrados = biblioteca.getHistorial().getRegistrosRecientes();
        cargarTabla(registrosMostrados);
    }

    private void aplicarFiltros() {
        if (cmbTipo.getSelectedItem() == null) {
            return;
        }

        String texto = txtBusqueda.getText();
        String tipoSeleccionado = cmbTipo.getSelectedItem().toString();

        List<GestorHistorial.RegistroHistorial> registros;

        if (texto != null && !texto.trim().isEmpty()) {
            registros = biblioteca.getHistorial().buscar(texto.trim());
        } else {
            registros = biblioteca.getHistorial().getRegistrosRecientes();
        }

        if (!"TODOS".equals(tipoSeleccionado)) {
            GestorHistorial.Tipo tipo = GestorHistorial.Tipo.valueOf(tipoSeleccionado);
            registros = filtrarPorTipo(registros, tipo);
        }

        registrosMostrados = registros;
        cargarTabla(registrosMostrados);
    }

    private List<GestorHistorial.RegistroHistorial> filtrarPorTipo(
            List<GestorHistorial.RegistroHistorial> registros,
            GestorHistorial.Tipo tipo
    ) {
        List<GestorHistorial.RegistroHistorial> filtrados = new ArrayList<>();

        for (GestorHistorial.RegistroHistorial registro : registros) {
            if (registro.getTipo() == tipo) {
                filtrados.add(registro);
            }
        }

        return filtrados;
    }

    private void cargarTabla(List<GestorHistorial.RegistroHistorial> registros) {
        DefaultTableModel modelo = (DefaultTableModel) tblHistorial.getModel();
        modelo.setRowCount(0);

        int no = 1;

        for (GestorHistorial.RegistroHistorial registro : registros) {
            modelo.addRow(new Object[]{
                no++,
                registro.getFechaFormateada(),
                registro.getTipo(),
                registro.getResultado(),
                registro.getAccion(),
                registro.getResumen(),
                registro.getId()
            });
        }

        Tabla.ocultarColumna(tblHistorial, 6);

        lblMostrados.setText("Registros mostrados: " + registros.size());
        lblGeneral.setText("Total general: " + biblioteca.getHistorial().getTotalRegistros());

        txtDetalle.setText("");

        if (tblHistorial.getRowCount() > 0) {
            tblHistorial.setRowSelectionInterval(0, 0);
        }
    }

    private void mostrarDetalleSeleccionado() {
        int fila = tblHistorial.getSelectedRow();

        if (fila < 0) {
            txtDetalle.setText("");
            return;
        }

        int filaModelo = tblHistorial.convertRowIndexToModel(fila);
        Object valorId = tblHistorial.getModel().getValueAt(filaModelo, 6);

        if (valorId == null) {
            txtDetalle.setText("");
            return;
        }

        int id;

        try {
            id = Integer.parseInt(valorId.toString());
        } catch (NumberFormatException e) {
            txtDetalle.setText("");
            return;
        }

        GestorHistorial.RegistroHistorial registro = buscarRegistroMostradoPorId(id);

        if (registro == null) {
            txtDetalle.setText("");
            return;
        }

        txtDetalle.setText(registro.getDetalleCompleto());
        txtDetalle.setCaretPosition(0);
    }

    private GestorHistorial.RegistroHistorial buscarRegistroMostradoPorId(int id) {
        for (GestorHistorial.RegistroHistorial registro : registrosMostrados) {
            if (registro.getId() == id) {
                return registro;
            }
        }
        return null;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblGeneral;
    private javax.swing.JLabel lblMostrados;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblHistorial;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextArea txtDetalle;
    // End of variables declaration//GEN-END:variables
}