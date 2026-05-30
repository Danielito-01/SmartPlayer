package servicios;

import estructuras.ArbolABB;
import estructuras.ArbolAVL;
import estructuras.ListaMusicas;
import modelos.Musica;
import modelos.TipoBusqueda;

public class GestorBusqueda {
    private final ArbolABB abbNombre;
    private final ArbolABB abbArtista;
    private final ArbolABB abbAlbum;
    private final ArbolABB abbGenero;

    private final ArbolAVL avlNombre;
    private final ArbolAVL avlArtista;
    private final ArbolAVL avlAlbum;
    private final ArbolAVL avlGenero;

    public GestorBusqueda() {
        abbNombre = new ArbolABB(TipoBusqueda.NOMBRE);
        abbArtista = new ArbolABB(TipoBusqueda.ARTISTA);
        abbAlbum = new ArbolABB(TipoBusqueda.ALBUM);
        abbGenero = new ArbolABB(TipoBusqueda.GENERO);

        avlNombre = new ArbolAVL(TipoBusqueda.NOMBRE);
        avlArtista = new ArbolAVL(TipoBusqueda.ARTISTA);
        avlAlbum = new ArbolAVL(TipoBusqueda.ALBUM);
        avlGenero = new ArbolAVL(TipoBusqueda.GENERO);
    }

    public void insertar(Musica musica) {
        if (musica == null) return;

        abbNombre.insertar(musica);
        abbArtista.insertar(musica);
        abbAlbum.insertar(musica);
        abbGenero.insertar(musica);

        avlNombre.insertar(musica);
        avlArtista.insertar(musica);
        avlAlbum.insertar(musica);
        avlGenero.insertar(musica);
    }

    public ListaMusicas buscarABB(TipoBusqueda tipo, String valor) {
        switch(tipo) {
            case NOMBRE:
                return abbNombre.buscar(valor);
            case ARTISTA:
                return abbArtista.buscar(valor);
            case ALBUM:
                return abbAlbum.buscar(valor);
            case GENERO:
                return abbGenero.buscar(valor);
            default:
                return null;
        }
    }

    public ListaMusicas buscarAVL(TipoBusqueda tipo, String valor) {
        switch(tipo) {
            case NOMBRE:
                return avlNombre.buscar(valor);
            case ARTISTA:
                return avlArtista.buscar(valor);
            case ALBUM:
                return avlAlbum.buscar(valor);
            case GENERO:
                return avlGenero.buscar(valor);
            default:
                return null;
        }
    }

    public void limpiar() {
        abbNombre.limpiar();
        abbArtista.limpiar();
        abbAlbum.limpiar();
        abbGenero.limpiar();

        avlNombre.limpiar();
        avlArtista.limpiar();
        avlAlbum.limpiar();
        avlGenero.limpiar();
    }

    public ArbolABB getAbbNombre() {
        return abbNombre;
    }

    public ArbolABB getAbbArtista() {
        return abbArtista;
    }

    public ArbolABB getAbbAlbum() {
        return abbAlbum;
    }

    public ArbolABB getAbbGenero() {
        return abbGenero;
    }

    public ArbolAVL getAvlNombre() {
        return avlNombre;
    }

    public ArbolAVL getAvlArtista() {
        return avlArtista;
    }

    public ArbolAVL getAvlAlbum() {
        return avlAlbum;
    }

    public ArbolAVL getAvlGenero() {
        return avlGenero;
    }
}