package clase;

import java.io.FileInputStream;
import java.io.InputStream;
import javazoom.jl.player.Player;

public class Reproductor {

    private Player player;
    private InputStream inputStream;
    private Thread hiloReproduccion;

    private boolean reproduciendo;
    private boolean pausado;
    private boolean finalizada;
    private boolean detenidoManualmente;

    private String ruta;

    private long totalBytes;
    private long bytesRestantes;

    public Reproductor() {

        reproduciendo = false;
        pausado = false;
        finalizada = false;
        detenidoManualmente = false;
    }

    public void Play(Musica musica) {
        if (musica == null) {
            return;
        }

        Stop();
        try {

            ruta = musica.getRuta();
            inputStream = new FileInputStream(ruta);
            totalBytes = inputStream.available();
            bytesRestantes = totalBytes;
            player = new Player(inputStream);
            reproduciendo = true;
            pausado = false;
            finalizada = false;
            detenidoManualmente = false;

            iniciarHiloReproduccion();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Pausa() {
        if (player != null && reproduciendo && !pausado) {
            try {
                bytesRestantes = inputStream.available();
                detenidoManualmente = true;
                player.close();
                pausado = true;
                reproduciendo = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void Reanudar() {
        if (pausado) {
            try {
                inputStream = new FileInputStream(ruta);
                inputStream.skip(totalBytes - bytesRestantes);
                player = new Player(inputStream);
                pausado = false;
                reproduciendo = true;
                detenidoManualmente = false;

                iniciarHiloReproduccion();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void Stop() {
        try {
            detenidoManualmente = true;
            reproduciendo = false;
            pausado = false;
            bytesRestantes = 0;

            if (player != null) {
                player.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }

            player = null;
            inputStream = null;
            hiloReproduccion = null;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciarHiloReproduccion() {
        hiloReproduccion = new Thread(() -> {
            try {
                player.play();
            } catch (Exception e) {
                System.out.println("Error en reproducción: " + e.getMessage());
            } finally {
                reproduciendo = false;
                if (!pausado && !detenidoManualmente) {
                    finalizada = true;
                }
            }
        });

        hiloReproduccion.start();
    }

    public boolean Reproduciendo() {
        return reproduciendo;
    }

    public boolean Pausado() {
        return pausado;
    }

    public boolean Finalizada() {
        if (finalizada) {
            finalizada = false;
            return true;
        }
        return false;
    }
}
