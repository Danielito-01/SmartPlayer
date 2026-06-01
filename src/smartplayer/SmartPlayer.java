package smartplayer;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.swing.UIManager;
import vistas.VentanaPrincipal;

public class SmartPlayer {

    public static void main(String[] args) {
        silenciarWarningsMolestos();
        try {
            System.setProperty("flatlaf.useWindowDecorations", "true");
            FlatIntelliJLaf.setup();
            UIManager.put("Button.arc", 999);
            UIManager.put("Component.arc", 18);
            UIManager.put("ProgressBar.arc", 999);
            UIManager.put("TextComponent.arc", 15);
            UIManager.put("ScrollBar.thumbArc", 999);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }

    private static void silenciarWarningsMolestos() {
        Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
        Logger.getLogger("org.jaudiotagger.tag").setLevel(Level.OFF);
        Logger.getLogger("org.jaudiotagger.audio").setLevel(Level.OFF);
        Logger.getLogger("org.jaudiotagger.tag.id3").setLevel(Level.OFF);
        Logger.getLogger("org.jaudiotagger.tag.id3.framebody").setLevel(Level.OFF);
        Logger.getLogger("sun.awt.shell").setLevel(Level.OFF);
        Logger.getLogger("sun.awt.shell.Win32ShellFolderManager2").setLevel(Level.OFF);
        Logger.getLogger("sun.awt.shell.Win32ShellFolder2").setLevel(Level.OFF);
        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            Filter filtroAnterior = handler.getFilter();
            handler.setFilter((LogRecord record) -> {
                String logger = record.getLoggerName();
                String mensaje = record.getMessage();
                boolean esWarningShell = logger != null
                        && logger.startsWith("sun.awt.shell");
                boolean esJaudiotagger = logger != null
                        && logger.startsWith("org.jaudiotagger");
                boolean esWarningPersonal = mensaje != null
                        && mensaje.contains("Cannot access 'Personal'");
                if (esWarningShell || esJaudiotagger || esWarningPersonal) {
                    return false;
                }
                if (filtroAnterior != null) {
                    return filtroAnterior.isLoggable(record);
                }
                return true;
            });
        }
    }
}