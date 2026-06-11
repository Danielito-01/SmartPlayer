package servicios;

import java.io.File;
import javax.swing.SwingUtilities;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
import modelos.Musica;

public class GestorReproductor {

    public interface Eventos {
        void alActualizarTiempo(double actual, double total);
        void alCambiarEstado(boolean reproduciendo);
        void alTerminar();
        void alError(String mensaje);
    }

    private static boolean javaFxIniciado = false;

    private MediaPlayer reproductor;
    private Eventos eventos;
    private volatile boolean reproduciendo;

    public GestorReproductor() {
        iniciarJavaFX();
    }

    public void setEventos(Eventos eventos) {
        this.eventos = eventos;
    }

    private static synchronized void iniciarJavaFX() {
        if (!javaFxIniciado) {
            new JFXPanel();
            Platform.setImplicitExit(false);
            javaFxIniciado = true;
        }
    }

    public void reproducir(Musica musica) {
        if (musica == null || musica.getRuta() == null || musica.getRuta().trim().isEmpty()) {
            notificarError("No se encontró la ruta de la música.");
            return;
        }

        File archivo = new File(musica.getRuta());

        if (!archivo.exists()) {
            notificarError("El archivo de la música no existe.");
            return;
        }

        String ruta = archivo.toURI().toString();

        Platform.runLater(() -> {
            try {
                liberarActual();

                Media media = new Media(ruta);
                reproductor = new MediaPlayer(media);

                reproductor.setOnReady(() -> {
                    reproduciendo = true;
                    notificarEstado(true);
                    notificarTiempo(0, obtenerDuracionTotal());
                    reproductor.play();
                });

                reproductor.currentTimeProperty().addListener((obs, anterior, actual) -> {
                    notificarTiempo(actual.toSeconds(), obtenerDuracionTotal());
                });

                reproductor.setOnEndOfMedia(() -> {
                    reproduciendo = false;
                    notificarEstado(false);
                    notificarTermino();
                });

                reproductor.setOnError(() -> {
                    reproduciendo = false;
                    notificarEstado(false);

                    String mensaje = "No se pudo reproducir la música.";
                    if (reproductor.getError() != null) {
                        mensaje += "\n" + reproductor.getError().getMessage();
                    }

                    notificarError(mensaje);
                });

            } catch (Exception e) {
                reproduciendo = false;
                notificarEstado(false);
                notificarError("Error al preparar la música.\n" + e.getMessage());
            }
        });
    }

    public void alternarPausa() {
        Platform.runLater(() -> {
            if (reproductor == null) {
                return;
            }

            Status estado = reproductor.getStatus();

            if (estado == Status.PLAYING) {
                reproductor.pause();
                reproduciendo = false;
                notificarEstado(false);
            } else {
                reproductor.play();
                reproduciendo = true;
                notificarEstado(true);
            }
        });
    }

    public void pausar() {
        Platform.runLater(() -> {
            if (reproductor != null) {
                reproductor.pause();
                reproduciendo = false;
                notificarEstado(false);
            }
        });
    }

    public void continuar() {
        Platform.runLater(() -> {
            if (reproductor != null) {
                reproductor.play();
                reproduciendo = true;
                notificarEstado(true);
            }
        });
    }

    public void moverA(double segundos) {
        final double segundosFinal = Math.max(0, segundos);

        Platform.runLater(() -> {
            if (reproductor == null) {
                return;
            }

            reproductor.seek(Duration.seconds(segundosFinal));
        });
    }

    public void detener() {
        Platform.runLater(() -> {
            if (reproductor != null) {
                reproductor.stop();
                reproduciendo = false;
                notificarEstado(false);
            }
        });
    }

    public void cerrar() {
        Platform.runLater(() -> liberarActual());
    }

    public boolean estaReproduciendo() {
        return reproduciendo;
    }

    private double obtenerDuracionTotal() {
        if (reproductor == null || reproductor.getTotalDuration() == null) {
            return 0;
        }

        Duration duracion = reproductor.getTotalDuration();

        if (duracion.isUnknown() || duracion.isIndefinite()) {
            return 0;
        }

        return duracion.toSeconds();
    }

    private void liberarActual() {
        if (reproductor != null) {
            reproductor.stop();
            reproductor.dispose();
            reproductor = null;
        }

        reproduciendo = false;
    }

    private void notificarTiempo(double actual, double total) {
        SwingUtilities.invokeLater(() -> {
            if (eventos != null) {
                eventos.alActualizarTiempo(actual, total);
            }
        });
    }

    private void notificarEstado(boolean reproduciendo) {
        SwingUtilities.invokeLater(() -> {
            if (eventos != null) {
                eventos.alCambiarEstado(reproduciendo);
            }
        });
    }

    private void notificarTermino() {
        SwingUtilities.invokeLater(() -> {
            if (eventos != null) {
                eventos.alTerminar();
            }
        });
    }

    private void notificarError(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            if (eventos != null) {
                eventos.alError(mensaje);
            }
        });
    }
}