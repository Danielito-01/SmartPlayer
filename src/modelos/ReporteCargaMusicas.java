package modelos;

public class ReporteCargaMusicas {
    private int recibidas;
    private int omitidasInvalidas;
    private int omitidasDuplicadas;

    private int ingresadasBiblioteca;
    private int ingresadasABB;
    private int ingresadasAVL;

    private int totalBiblioteca;
    private int totalABB;
    private int totalAVL;

    private long tiempoBibliotecaNs;
    private long tiempoABBNs;
    private long tiempoAVLNs;
    private long tiempoTotalNs;

    public int getRecibidas() {
        return recibidas;
    }

    public void setRecibidas(int recibidas) {
        this.recibidas = recibidas;
    }

    public int getOmitidasInvalidas() {
        return omitidasInvalidas;
    }

    public void incrementarOmitidasInvalidas() {
        this.omitidasInvalidas++;
    }

    public int getOmitidasDuplicadas() {
        return omitidasDuplicadas;
    }

    public void incrementarOmitidasDuplicadas() {
        this.omitidasDuplicadas++;
    }

    public int getIngresadasBiblioteca() {
        return ingresadasBiblioteca;
    }

    public void incrementarIngresadasBiblioteca() {
        this.ingresadasBiblioteca++;
    }

    public int getIngresadasABB() {
        return ingresadasABB;
    }

    public void incrementarIngresadasABB() {
        this.ingresadasABB++;
    }

    public int getIngresadasAVL() {
        return ingresadasAVL;
    }

    public void incrementarIngresadasAVL() {
        this.ingresadasAVL++;
    }

    public int getTotalBiblioteca() {
        return totalBiblioteca;
    }

    public void setTotalBiblioteca(int totalBiblioteca) {
        this.totalBiblioteca = totalBiblioteca;
    }

    public int getTotalABB() {
        return totalABB;
    }

    public void setTotalABB(int totalABB) {
        this.totalABB = totalABB;
    }

    public int getTotalAVL() {
        return totalAVL;
    }

    public void setTotalAVL(int totalAVL) {
        this.totalAVL = totalAVL;
    }

    public void sumarTiempoBibliotecaNs(long tiempoNs) {
        this.tiempoBibliotecaNs += tiempoNs;
    }

    public void sumarTiempoABBNs(long tiempoNs) {
        this.tiempoABBNs += tiempoNs;
    }

    public void sumarTiempoAVLNs(long tiempoNs) {
        this.tiempoAVLNs += tiempoNs;
    }

    public void setTiempoTotalNs(long tiempoTotalNs) {
        this.tiempoTotalNs = tiempoTotalNs;
    }

    public double getTiempoBibliotecaMs() {
        return tiempoBibliotecaNs / 1_000_000.0;
    }

    public double getTiempoABBMs() {
        return tiempoABBNs / 1_000_000.0;
    }

    public double getTiempoAVLMs() {
        return tiempoAVLNs / 1_000_000.0;
    }

    public double getTiempoTotalMs() {
        return tiempoTotalNs / 1_000_000.0;
    }
}