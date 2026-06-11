package estructuras;

import java.util.ArrayList;
import java.util.List;
import modelos.Musica;

public class ArbolAVL {

    private static final class NMusica {
        Musica musica;
        NMusica izquierdo;
        NMusica derecho;
        int altura;

        NMusica(Musica musica) {
            this.musica = musica;
            this.altura = 1;
        }
    }

    private NMusica raiz;
    private int cantidad;

    public ArbolAVL() {
        raiz = null;
        cantidad = 0;
    }

    public Musica getRaiz() {
        return raiz.musica;
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

        if (existeId(raiz, musica.getId())) {
            return false;
        }

        raiz = agregarMusica(raiz, musica);
        cantidad++;

        return true;
    }

    private NMusica agregarMusica(NMusica nodo, Musica musica) {
        if (nodo == null) {
            return new NMusica(musica);
        }

        int comparacion = compararMusicas(musica, nodo.musica);

        if (comparacion < 0) {
            nodo.izquierdo = agregarMusica(nodo.izquierdo, musica);
        } else {
            nodo.derecho = agregarMusica(nodo.derecho, musica);
        }

        return balancear(nodo);
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

        if (eliminada[0]) {
            cantidad--;
        }

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

        dot.append("digraph ArbolAVL {\n");
        dot.append("    rankdir=TB;\n");
        dot.append("    node [shape=record, style=filled, fillcolor=\"#EAF8EA\", color=\"#3FA34D\"];\n");
        dot.append("    edge [color=\"#3FA34D\"];\n\n");

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
        String etiqueta = etiquetaNodo(nodo);

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
                .append(" [label=\"\", shape=point, color=\"#B7E4BF\"];\n");

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

    private String etiquetaNodo(NMusica nodo) {
        return escaparDot(nodo.musica.getNombre())
                + "\\nID: "
                + nodo.musica.getId()
                + "\\nAltura: "
                + nodo.altura
                + "\\nBalance: "
                + obtenerBalance(nodo);
    }

    private String escaparDot(String texto) {
        if (texto == null || texto.isBlank()) {
            return "Desconocido";
        }

        return texto
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "")
                .replace("\n", "\\n")
                .replace("{", "\\{")
                .replace("}", "\\}")
                .replace("|", "\\|")
                .replace("<", "\\<")
                .replace(">", "\\>");
    }

    private NMusica eliminarPorId(NMusica nodo, int id, boolean[] eliminada) {
        if (nodo == null) {
            return null;
        }

        if (nodo.musica.getId() == id) {
            eliminada[0] = true;
            return eliminarNodo(nodo);
        }

        nodo.izquierdo = eliminarPorId(nodo.izquierdo, id, eliminada);

        if (!eliminada[0]) {
            nodo.derecho = eliminarPorId(nodo.derecho, id, eliminada);
        }

        return balancear(nodo);
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

        return balancear(nodo);
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

        return balancear(nodo);
    }

    private NMusica balancear(NMusica nodo) {
        if (nodo == null) {
            return null;
        }

        actualizarAltura(nodo);

        int balance = obtenerBalance(nodo);

        if (balance > 1) {
            if (obtenerBalance(nodo.izquierdo) < 0) {
                nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            }

            return rotarDerecha(nodo);
        }

        if (balance < -1) {
            if (obtenerBalance(nodo.derecho) > 0) {
                nodo.derecho = rotarDerecha(nodo.derecho);
            }

            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private NMusica rotarDerecha(NMusica nodo) {
        NMusica nuevaRaiz = nodo.izquierdo;
        NMusica temporal = nuevaRaiz.derecho;

        nuevaRaiz.derecho = nodo;
        nodo.izquierdo = temporal;

        actualizarAltura(nodo);
        actualizarAltura(nuevaRaiz);

        return nuevaRaiz;
    }

    private NMusica rotarIzquierda(NMusica nodo) {
        NMusica nuevaRaiz = nodo.derecho;
        NMusica temporal = nuevaRaiz.izquierdo;

        nuevaRaiz.izquierdo = nodo;
        nodo.derecho = temporal;

        actualizarAltura(nodo);
        actualizarAltura(nuevaRaiz);

        return nuevaRaiz;
    }

    private void actualizarAltura(NMusica nodo) {
        nodo.altura = 1 + Math.max(
                altura(nodo.izquierdo),
                altura(nodo.derecho)
        );
    }

    private int altura(NMusica nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private int obtenerBalance(NMusica nodo) {
        if (nodo == null) {
            return 0;
        }

        return altura(nodo.izquierdo) - altura(nodo.derecho);
    }

    private boolean existeId(NMusica nodo, int id) {
        if (nodo == null) {
            return false;
        }

        if (nodo.musica.getId() == id) {
            return true;
        }

        return existeId(nodo.izquierdo, id)
                || existeId(nodo.derecho, id);
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