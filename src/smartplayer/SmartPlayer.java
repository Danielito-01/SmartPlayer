package smartplayer;

import java.util.logging.Level;
import java.util.logging.Logger;
import vistas.VentanaPrincipal;

public class SmartPlayer {

    public static void main(String[] args) {
        Logger.getLogger("org.jaudiotagger").setLevel(Level.SEVERE);
        VentanaPrincipal interfazPrincipal = new VentanaPrincipal();
        interfazPrincipal.setVisible(true);
    }
}
