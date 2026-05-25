package smartplayer;

import java.util.logging.Level;
import java.util.logging.Logger;
import interfaz.Principal;

public class SmartPlayer {

    public static void main(String[] args) {
        Logger.getLogger("org.jaudiotagger").setLevel(Level.WARNING);
        Principal interfazPrincipal = new Principal();
        interfazPrincipal.setVisible(true);
    }
}
