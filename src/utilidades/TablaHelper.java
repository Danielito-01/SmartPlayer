package utilidades;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Musica;

public class TablaHelper {

    public static void ocultarColumna(JTable tabla, int columna) {
        tabla.getColumnModel().getColumn(columna).setMinWidth(0);
        tabla.getColumnModel().getColumn(columna).setMaxWidth(0);
        tabla.getColumnModel().getColumn(columna).setPreferredWidth(0);
    }

    public static void establecerAnchoMaximo(JTable tabla, int columna, int ancho) {
        tabla.getColumnModel().getColumn(columna).setMaxWidth(ancho);
    }
    
    public static void cargarMusicas(JTable tablaMusicas, List<Musica> musicas) {
        DefaultTableModel tabla = (DefaultTableModel) tablaMusicas.getModel();
        tabla.setRowCount(0);

        if (musicas == null) {
            return;
        }

        int no = 1;
        for (Musica musica : musicas) {
            tabla.addRow(new Object[]{
                no++,
                musica.toString(),
                musica.getId()
            });
        }
    }
    
    public static boolean seleccionarFilaPorId(JTable tabla, int columnaId, int idBuscado) {
        for (int fila = 0; fila < tabla.getRowCount(); fila++) {

            Object valor = tabla.getValueAt(fila, columnaId);
            if (valor != null
                    && Integer.parseInt(valor.toString()) == idBuscado) {
                tabla.setRowSelectionInterval(fila, fila);
                return true;
            }
        }
        return false;
    }
    
    public static void cargarMusicasConDetalles(JTable tabla, List<Musica> musicas) {
        int no = 1;
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        tabla.setRowHeight(50);

        for (Musica musica : musicas) {
            modelo.addRow(new Object[]{
                no++,
                musica.getNombre(),
                musica.getArtista(),
                musica.getAlbum(),
                musica.getGenero(),
                musica.formatearDuracion(),
                musica.formatearTamanio(),
                musica.getRuta(),
                musica.anioReal(),
                musica.getPortadaPequenia()
            });
        }
    }
}