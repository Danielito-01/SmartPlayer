package servicios;

import java.io.File;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import modelos.Musica;

public class Reproductor {

    private MediaPlayer mediaPlayer;

    private volatile boolean reproduciendo;
    private volatile boolean pausado;
    private volatile boolean finalizada;

    private String rutaActual;
    private double volumen;

    static {
        // Inicializa JavaFX cuando el proyecto usa Swing o Java normal.
        // Si tu proyecto ya es JavaFX puro, esto no molesta.
        new JFXPanel();

        // Evita que JavaFX se cierre cuando no hay ventanas JavaFX visibles.
        Platform.setImplicitExit(false);
    }

    public Reproductor() {
        reproduciendo = false;
        pausado = false;
        finalizada = false;
        volumen = 1.0;
    }

    public void reproducir(Musica musica) {
        if (musica == null || musica.getRuta() == null || musica.getRuta().isBlank()) {
            return;
        }

        ejecutarEnJavaFX(() -> {
            try {
                detenerInterno();

                rutaActual = musica.getRuta();

                File archivo = new File(rutaActual);

                if (!archivo.exists()) {
                    System.out.println("El archivo no existe: " + rutaActual);
                    return;
                }

                Media media = new Media(archivo.toURI().toString());
                mediaPlayer = new MediaPlayer(media);

                mediaPlayer.setVolume(volumen);

                reproduciendo = false;
                pausado = false;
                finalizada = false;

                mediaPlayer.setOnReady(() -> {
                    mediaPlayer.play();
                    reproduciendo = true;
                    pausado = false;
                });

                mediaPlayer.setOnPaused(() -> {
                    reproduciendo = false;
                    pausado = true;
                });

                mediaPlayer.setOnPlaying(() -> {
                    reproduciendo = true;
                    pausado = false;
                });

                mediaPlayer.setOnStopped(() -> {
                    reproduciendo = false;
                    pausado = false;
                });

                mediaPlayer.setOnEndOfMedia(() -> {
                    reproduciendo = false;
                    pausado = false;
                    finalizada = true;
                    mediaPlayer.stop();
                });

                mediaPlayer.setOnError(() -> {
                    reproduciendo = false;
                    pausado = false;
                    System.out.println("Error al reproducir: " + mediaPlayer.getError());
                });

            } catch (Exception e) {
                reproduciendo = false;
                pausado = false;
                e.printStackTrace();
            }
        });
    }

    public void pausar() {
        ejecutarEnJavaFX(() -> {
            if (mediaPlayer != null && reproduciendo) {
                mediaPlayer.pause();
            }
        });
    }

    public void reanudar() {
        ejecutarEnJavaFX(() -> {
            if (mediaPlayer != null && pausado) {
                mediaPlayer.play();
            }
        });
    }

    public void detener() {
        ejecutarEnJavaFX(() -> {
            detenerInterno();
        });
    }

    private void detenerInterno() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
        }

        reproduciendo = false;
        pausado = false;
        finalizada = false;
    }

    public void cambiarVolumen(double volumen) {
        if (volumen < 0) {
            volumen = 0;
        }

        if (volumen > 1) {
            volumen = 1;
        }

        this.volumen = volumen;

        double volumenFinal = volumen;

        ejecutarEnJavaFX(() -> {
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(volumenFinal);
            }
        });
    }

    public double obtenerVolumen() {
        return volumen;
    }

    public void avanzarA(double segundos) {
        ejecutarEnJavaFX(() -> {
            if (mediaPlayer != null) {
                mediaPlayer.seek(Duration.seconds(segundos));
            }
        });
    }

    public double obtenerTiempoActual() {
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentTime().toSeconds();
        }

        return 0;
    }

    public double obtenerDuracionTotal() {
        if (mediaPlayer != null && mediaPlayer.getTotalDuration() != null) {
            return mediaPlayer.getTotalDuration().toSeconds();
        }

        return 0;
    }

    public boolean estaReproduciendo() {
        return reproduciendo;
    }

    public boolean estaPausado() {
        return pausado;
    }

    public boolean estaFinalizada() {
        if (finalizada) {
            finalizada = false;
            return true;
        }

        return false;
    }

    public String getRutaActual() {
        return rutaActual;
    }

    private void ejecutarEnJavaFX(Runnable accion) {
        if (Platform.isFxApplicationThread()) {
            accion.run();
        } else {
            Platform.runLater(accion);
        }
    }
}