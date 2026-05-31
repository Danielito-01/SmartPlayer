package utilidades;

public class Formato {

    public static String tiempo(int segundosTotales) {
        int minutos = segundosTotales / 60;
        int segundos = segundosTotales % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    public static String textoHtml(String texto, int width) {
        return "<html><div style='width:" + width + "px;'>"
                + escaparHtml(texto)
                + "</div></html>";
    }

    public static String textoHtml(String texto, int width, int height) {
        return "<html>"
                + "<div style='width:" + width + "px; height:" + height + "px;'>"
                + escaparHtml(texto)
                + "</div>"
                + "</html>";
    }

    private static String escaparHtml(String texto) {
        if (texto == null || texto.isBlank()) {
            return "Desconocido";
        }
        return texto.trim()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}