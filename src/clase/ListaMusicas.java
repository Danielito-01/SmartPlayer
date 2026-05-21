package clase;

public class ListaMusicas {
    private Musica primera;
    private Musica ultima;

    public ListaMusicas() {
        this.primera = null;
        this.ultima = null;
    }
    
    public boolean estaVacia() {
        return primera == null;
    }
    
    public void guardarMusica(Musica musica) {
        if (estaVacia()) {
            primera = musica;
            ultima = musica;
        }else {
            ultima.setSiguiente(musica);
            musica.setAnterior(ultima);
            ultima = musica;
        }
    }
}
