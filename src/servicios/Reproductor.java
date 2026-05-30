package servicios;

import java.io.FileInputStream;
import java.io.InputStream;
import javazoom.jl.player.Player;
import modelos.Musica;

public class Reproductor {

    private Player player;
    private InputStream inputStream;
    private Thread hiloReproduccion;

    private volatile boolean reproduciendo;
    private volatile boolean pausado;
    private volatile boolean finalizada;
    private volatile boolean detenidoManualmente;

    private String ruta;

    private long totalBytes;
    private long bytesRestantes;

    public Reproductor() {
        reproduciendo = false;
        pausado = false;
        finalizada = false;
        detenidoManualmente = false;
    }

    public void reproducir(Musica musica) {
        if (musica == null) {
            return;
        }

        detener();
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

    public void pausar() {
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

    public void reanudar() {
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

    public void detener() {
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
}