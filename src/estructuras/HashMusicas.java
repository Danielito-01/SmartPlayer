package estructuras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelos.Musica;

public class HashMusicas {

    private final Map<Integer, Musica> porId = new HashMap<>();

    private final Map<String, List<Musica>> porArtista = new HashMap<>();
    private final Map<String, List<Musica>> porAlbum = new HashMap<>();
    private final Map<String, List<Musica>> porGenero = new HashMap<>();

    private final Map<Integer, List<Musica>> porAnio = new HashMap<>();

    // INSERTAR EN HASH
    public void insertar(Musica musica) {
        if (musica == null) return;

        porId.put(musica.getId(), musica);

        agregarTexto(porArtista, musica.getArtista(), musica);
        agregarTexto(porAlbum, musica.getAlbum(), musica);
        agregarTexto(porGenero, musica.getGenero(), musica);
        agregarAnio(porAnio, musica.getAnio(), musica);
    }

    // ELIMINAR DEL HASH
    public void eliminar(Musica musica) {
        if (musica == null) return;

        porId.remove(musica.getId());

        eliminarTexto(porArtista, musica.getArtista(), musica);
        eliminarTexto(porAlbum, musica.getAlbum(), musica);
        eliminarTexto(porGenero, musica.getGenero(), musica);
        eliminarAnio(porAnio, musica.getAnio(), musica);
    }

    // BUSCAR POR ID
    public Musica buscarPorId(int id) {
        return porId.get(id);
    }

    // BUSCAR POR ARTISTA
    public List<Musica> buscarPorArtista(String artista) {
        return buscarTexto(porArtista, artista);
    }

    // BUSCAR POR ÁLBUM
    public List<Musica> buscarPorAlbum(String album) {
        return buscarTexto(porAlbum, album);
    }

    // BUSCAR POR GÉNERO
    public List<Musica> buscarPorGenero(String genero) {
        return buscarTexto(porGenero, genero);
    }

    // BUSCAR POR AÑO
    public List<Musica> buscarPorAnio(int anio) {
        List<Musica> lista = porAnio.get(anio);

        if (lista == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(lista);
    }

    // MÉTODO AUXILIAR PARA AGREGAR ARTISTA, ÁLBUM O GÉNERO
    private void agregarTexto(Map<String, List<Musica>> mapa, String clave, Musica musica) {
        String key = normalizar(clave);

        if (key.isBlank()) return;

        if (!mapa.containsKey(key)) {
            mapa.put(key, new ArrayList<>());
        }

        mapa.get(key).add(musica);
    }

    // MÉTODO AUXILIAR PARA AGREGAR AÑO
    private void agregarAnio(Map<Integer, List<Musica>> mapa, int anio, Musica musica) {
        if (anio <= 0) return;

        if (!mapa.containsKey(anio)) {
            mapa.put(anio, new ArrayList<>());
        }

        mapa.get(anio).add(musica);
    }

    // MÉTODO AUXILIAR PARA ELIMINAR ARTISTA, ÁLBUM O GÉNERO
    private void eliminarTexto(Map<String, List<Musica>> mapa, String clave, Musica musica) {
        String key = normalizar(clave);

        if (key.isBlank()) return;

        List<Musica> lista = mapa.get(key);

        if (lista == null) return;

        lista.removeIf(m -> m.getId() == musica.getId());

        if (lista.isEmpty()) {
            mapa.remove(key);
        }
    }

    // MÉTODO AUXILIAR PARA ELIMINAR AÑO
    private void eliminarAnio(Map<Integer, List<Musica>> mapa, int anio, Musica musica) {
        if (anio <= 0) return;

        List<Musica> lista = mapa.get(anio);

        if (lista == null) return;

        lista.removeIf(m -> m.getId() == musica.getId());

        if (lista.isEmpty()) {
            mapa.remove(anio);
        }
    }

    // MÉTODO AUXILIAR PARA BUSCAR ARTISTA, ÁLBUM O GÉNERO
    private List<Musica> buscarTexto(Map<String, List<Musica>> mapa, String clave) {
        String key = normalizar(clave);

        List<Musica> lista = mapa.get(key);

        if (lista == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(lista);
    }

    // NORMALIZA TEXTO PARA EVITAR PROBLEMAS CON MAYÚSCULAS O ESPACIOS
    private String normalizar(String texto) {
        if (texto == null) return "";

        return texto.trim().toLowerCase();
    }
}