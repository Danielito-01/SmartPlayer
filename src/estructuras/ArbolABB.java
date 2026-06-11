package estructuras;

import java.util.ArrayList;
import java.util.List;
import modelos.Musica;

public class ArbolABB {

    private static final class NMusica {
        Musica musica;
        NMusica izquierdo;
        NMusica derecho;

        NMusica(Musica musica) {
            this.musica = musica;
        }
    }

    private NMusica raiz;
    private int cantidad;

    public ArbolABB() {
        raiz = null;
        cantidad = 0;
    }

    public int getCantidad() {
        return cantidad;
    }

    private boolean estaVacio() {
        return cantidad == 0;
    }

    public boolean agregarMusica(Musica musica) {
        if (!esMusicaValida(musica)) {
            return false;
        }

        NMusica nueva = new NMusica(musica);

        if (estaVacio()) {
            raiz = nueva;
            cantidad++;
            return true;
        }

        NMusica aux = raiz;
        NMusica padre = null;

        while (aux != null) {
            padre = aux;

            int comparacion = compararMusicas(musica, aux.musica);

            if (comparacion == 0) {
                return false;
            }

            if (comparacion < 0) {
                aux = aux.izquierdo;
            } else {
                aux = aux.derecho;
            }
        }

        if (compararMusicas(musica, padre.musica) < 0) {
            padre.izquierdo = nueva;
        } else {
            padre.derecho = nueva;
        }

        cantidad++;
        return true;
    }

    public List<Musica> buscarMusica(String nombre) {
        List<Musica> resultados = new ArrayList<>();

        if (nombre == null || nombre.trim().isEmpty()) {
            return resultados;
        }

        buscarEnOrden(raiz, nombre.trim(), resultados);
        return resultados;
    }

    private void buscarEnOrden(NMusica nodo, String nombreBuscado, List<Musica> resultados) {
        if (nodo == null) {
            return;
        }

        buscarEnOrden(nodo.izquierdo, nombreBuscado, resultados);

        if (coincideDesdeInicio(nodo.musica, nombreBuscado)) {
            resultados.add(nodo.musica);
        }

        buscarEnOrden(nodo.derecho, nombreBuscado, resultados);
    }

    private boolean coincideDesdeInicio(Musica musica, String nombreBuscado) {
        if (musica == null || nombreBuscado == null) {
            return false;
        }

        String nombreMusica = obtenerNombre(musica).toLowerCase();
        String busqueda = nombreBuscado.trim().toLowerCase();

        return nombreMusica.startsWith(busqueda);
    }

    public boolean eliminarMusica(Musica musica) {
        if (musica == null || estaVacio()) {
            return false;
        }

        boolean[] eliminada = {false};
        raiz = eliminarPorId(raiz, musica.getId(), eliminada);

        return eliminada[0];
    }

    public boolean actualizarMusica(Musica musica) {
        if (!esMusicaValida(musica) || estaVacio()) {
            return false;
        }

        boolean eliminada = eliminarMusica(musica);

        if (!eliminada) {
            return false;
        }

        return agregarMusica(musica);
    }

    public String generarDot() {
        StringBuilder dot = new StringBuilder();

        dot.append("digraph ArbolABB {\n");
        dot.append("    rankdir=TB;\n");
        dot.append("    node [shape=record, style=filled, fillcolor=\"#EAF2FF\", color=\"#4A90E2\"];\n");
        dot.append("    edge [color=\"#4A90E2\"];\n\n");

        if (raiz == null) {
            dot.append("    vacio [label=\"Árbol vacío\"];\n");
        } else {
            generarDot(raiz, dot);
        }

        dot.append("}\n");

        return dot.toString();
    }

    private void generarDot(NMusica nodo, StringBuilder dot) {
        if (nodo == null) {
            return;
        }

        String idNodo = idNodo(nodo);
        String etiqueta = etiquetaNodo(nodo.musica);

        dot.append("    ")
                .append(idNodo)
                .append(" [label=\"")
                .append(etiqueta)
                .append("\"];\n");

        if (nodo.izquierdo != null) {
            dot.append("    ")
                    .append(idNodo)
                    .append(" -> ")
                    .append(idNodo(nodo.izquierdo))
                    .append(" [label=\"Izq\"];\n");

            generarDot(nodo.izquierdo, dot);
        } else {
            agregarNodoVacio(dot, idNodo, "Izq");
        }

        if (nodo.derecho != null) {
            dot.append("    ")
                    .append(idNodo)
                    .append(" -> ")
                    .append(idNodo(nodo.derecho))
                    .append(" [label=\"Der\"];\n");

            generarDot(nodo.derecho, dot);
        } else {
            agregarNodoVacio(dot, idNodo, "Der");
        }
    }

    private void agregarNodoVacio(StringBuilder dot, String idPadre, String lado) {
        String idVacio = "null_" + idPadre + "_" + lado.toLowerCase();

        dot.append("    ")
                .append(idVacio)
                .append(" [label=\"\", shape=point, color=\"#BFD7F5\"];\n");

        dot.append("    ")
                .append(idPadre)
                .append(" -> ")
                .append(idVacio)
                .append(" [label=\"")
                .append(lado)
                .append("\"];\n");
    }

    private String idNodo(NMusica nodo) {
        return "nodo_" + nodo.musica.getId();
    }

    private String etiquetaNodo(Musica musica) {
        return escaparDot(musica.getNombre())
                + "\\nID: "
                + musica.getId();
    }

    private String escaparDot(String texto) {
        if (texto == null) {
            return "";
        }

        return texto
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }

    private NMusica eliminarPorId(NMusica nodo, int id, boolean[] eliminada) {
        if (nodo == null) {
            return null;
        }

        if (nodo.musica.getId() == id) {
            eliminada[0] = true;
            cantidad--;
            return eliminarNodo(nodo);
        }

        nodo.izquierdo = eliminarPorId(nodo.izquierdo, id, eliminada);

        if (!eliminada[0]) {
            nodo.derecho = eliminarPorId(nodo.derecho, id, eliminada);
        }

        return nodo;
    }

    private NMusica eliminarNodo(NMusica nodo) {
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return null;
        }

        if (nodo.izquierdo == null) {
            return nodo.derecho;
        }

        if (nodo.derecho == null) {
            return nodo.izquierdo;
        }

        NMusica reemplazo = obtenerMenor(nodo.derecho);
        nodo.musica = reemplazo.musica;
        nodo.derecho = eliminarMenor(nodo.derecho);

        return nodo;
    }

    private NMusica obtenerMenor(NMusica nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }

        return nodo;
    }

    private NMusica eliminarMenor(NMusica nodo) {
        if (nodo.izquierdo == null) {
            return nodo.derecho;
        }

        nodo.izquierdo = eliminarMenor(nodo.izquierdo);

        return nodo;
    }

    private int compararMusicas(Musica a, Musica b) {
        int comparacionNombre = obtenerNombre(a).compareToIgnoreCase(obtenerNombre(b));

        if (comparacionNombre != 0) {
            return comparacionNombre;
        }

        return Integer.compare(a.getId(), b.getId());
    }

    private boolean esMusicaValida(Musica musica) {
        return musica != null
                && musica.getNombre() != null
                && !musica.getNombre().trim().isEmpty();
    }

    private String obtenerNombre(Musica musica) {
        return musica.getNombre().trim();
    }
}