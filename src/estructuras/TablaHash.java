package estructuras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelos.Musica;

public class TablaHash {

    private final Map<Integer, Musica> porId;
    private final Map<String, List<Musica>> porArtista;
    private final Map<String, List<Musica>> porAlbum;
    private final Map<String, List<Musica>> porGenero;
    private final Map<Integer, List<Musica>> porAnio;

    public TablaHash() {
        porId = new HashMap<>();
        porArtista = new HashMap<>();
        porAlbum = new HashMap<>();
        porGenero = new HashMap<>();
        porAnio = new HashMap<>();
    }

    public int getCantidad() {
        return porId.size();
    }

    public boolean estaVacia() {
        return porId.isEmpty();
    }

    public boolean insertarMusica(Musica musica) {
        if (musica == null) {
            return false;
        }

        int id = musica.getId();

        limpiarIndicesPorId(id);

        porId.put(id, musica);
        agregarTexto(porArtista, musica.getArtista(), musica);
        agregarTexto(porAlbum, musica.getAlbum(), musica);
        agregarTexto(porGenero, musica.getGenero(), musica);
        agregarAnio(porAnio, musica.getAnio(), musica);

        return true;
    }

    public boolean actualizarMusica(Musica musica) {
        if (musica == null) {
            return false;
        }

        int id = musica.getId();

        if (!porId.containsKey(id)) {
            return false;
        }

        limpiarIndicesPorId(id);

        porId.put(id, musica);
        agregarTexto(porArtista, musica.getArtista(), musica);
        agregarTexto(porAlbum, musica.getAlbum(), musica);
        agregarTexto(porGenero, musica.getGenero(), musica);
        agregarAnio(porAnio, musica.getAnio(), musica);

        return true;
    }

    public boolean eliminarMusica(Musica musica) {
        if (musica == null) {
            return false;
        }

        int id = musica.getId();
        Musica eliminada = porId.remove(id);

        if (eliminada == null) {
            return false;
        }

        limpiarIndicesPorId(id);

        return true;
    }

    public Musica buscarPorId(int id) {
        return porId.get(id);
    }

    public List<Musica> buscarPorArtista(String artista) {
        return buscarTexto(porArtista, artista);
    }

    public List<Musica> buscarPorAlbum(String album) {
        return buscarTexto(porAlbum, album);
    }

    public List<Musica> buscarPorGenero(String genero) {
        return buscarTexto(porGenero, genero);
    }

    public List<Musica> buscarPorAnio(int anio) {
        List<Musica> lista = porAnio.get(anio);

        if (lista == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(lista);
    }

    private void agregarTexto(Map<String, List<Musica>> mapa, String clave, Musica musica) {
        String key = normalizar(clave);

        if (key.isEmpty()) {
            return;
        }

        if (!mapa.containsKey(key)) {
            mapa.put(key, new ArrayList<>());
        }

        mapa.get(key).add(musica);
    }

    private void agregarAnio(Map<Integer, List<Musica>> mapa, int anio, Musica musica) {
        if (anio <= 0) {
            return;
        }

        if (!mapa.containsKey(anio)) {
            mapa.put(anio, new ArrayList<>());
        }

        mapa.get(anio).add(musica);
    }

    private List<Musica> buscarTexto(Map<String, List<Musica>> mapa, String clave) {
        List<Musica> resultados = new ArrayList<>();
        String key = normalizar(clave);

        if (key.isEmpty()) {
            return resultados;
        }

        for (Map.Entry<String, List<Musica>> entrada : mapa.entrySet()) {
            String claveGuardada = entrada.getKey();

            if (claveGuardada.startsWith(key)) {
                resultados.addAll(entrada.getValue());
            }
        }

        return resultados;
    }

    private void limpiarIndicesPorId(int id) {
        limpiarMapaTexto(porArtista, id);
        limpiarMapaTexto(porAlbum, id);
        limpiarMapaTexto(porGenero, id);
        limpiarMapaAnio(porAnio, id);
    }

    private void limpiarMapaTexto(Map<String, List<Musica>> mapa, int id) {
        mapa.entrySet().removeIf(entrada -> {
            entrada.getValue().removeIf(musica -> musica.getId() == id);
            return entrada.getValue().isEmpty();
        });
    }

    private void limpiarMapaAnio(Map<Integer, List<Musica>> mapa, int id) {
        mapa.entrySet().removeIf(entrada -> {
            entrada.getValue().removeIf(musica -> musica.getId() == id);
            return entrada.getValue().isEmpty();
        });
    }

    private String normalizar(String texto) {
        if (texto == null) {
            return "";
        }

        return texto.trim().toLowerCase();
    }
}