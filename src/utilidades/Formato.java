package utilidades;

public class Formato {

    public static String tiempo(int segundosTotales) {
        int minutos = segundosTotales / 60;
        int segundos = segundosTotales % 60;

        return String.format("%02d:%02d", minutos, segundos);
    }

    public static String textoHtml(String texto, int width) {
        return "<html><div style='width:" + width + "px;'>" + texto + "</div></html>";
    }
}