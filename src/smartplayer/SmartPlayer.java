package smartplayer;

import java.util.logging.Level;
import java.util.logging.Logger;
import interfaz.Principal;

public class SmartPlayer {

    public static void main(String[] args) {
        Logger.getLogger("org.jaudiotagger").setLevel(Level.WARNING);
        Principal interfaz = new Principal();
        interfaz.setVisible(true);
    }
}
