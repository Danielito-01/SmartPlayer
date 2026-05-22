package smartplayer;

import java.util.logging.Level;
import java.util.logging.Logger;
import smartplayer.interfaz.InterfazPrincipal;

public class SmartPlayer {

    public static void main(String[] args) {
        Logger.getLogger("org.jaudiotagger").setLevel(Level.WARNING);
        InterfazPrincipal interfaz = new InterfazPrincipal();
        interfaz.setVisible(true);
    }
}
