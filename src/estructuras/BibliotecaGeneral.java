package estructuras;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelos.Musica;
import modelos.Playlist;
import servicios.GestorHistorial;

public final class BibliotecaGeneral {
    private static final BibliotecaGeneral INSTANCE = new BibliotecaGeneral();
    private final ListaMusicas biblioteca = new ListaMusicas();
    
    private final ArbolABB abb = new ArbolABB();
    private final ArbolAVL avl = new ArbolAVL();
    private final TablaHash hash = new TablaHash();
    
    private final PilaHistorial pilaHistorial = new PilaHistorial();
    private final ColaReproduccion colaReproduccion = new ColaReproduccion();
    private final GestorHistorial historial = new GestorHistorial();

    private final Set<String> rutas = new HashSet<>();
    private int idMusica = 1;

    private final List<Playlist> playlists = new ArrayList<>();
    private int idPlaylist = 1;

    private BibliotecaGeneral() {
    }

    public static BibliotecaGeneral getInstance() {
        return INSTANCE;
    }

    public ListaMusicas getBiblioteca() {
        return biblioteca;
    }

    public ArbolABB getAbb() {
        return abb;
    }

    public ArbolAVL getAvl() {
        return avl;
    }

    public TablaHash getHash() {
        return hash;
    }
    
    public ColaReproduccion getColaReproduccion() {
        return colaReproduccion;
    }
    
    public PilaHistorial getPilaHistorial() {
        return pilaHistorial;
    }

    public GestorHistorial getHistorial() {
        return historial;
    }

    public String insertarMusicas(List<Musica> musicas) {
        int recibidas = 0;
        int insertadas = 0;
        int duplicadas = 0;
        int invalidas = 0;
        int fallidas = 0;

        long tiempoABB = 0;
        long tiempoAVL = 0;
        long inicioTotal = System.nanoTime();

        if (musicas != null) {
            recibidas = musicas.size();

            for (Musica musica : musicas) {
                if (!esMusicaValida(musica)) {
                    invalidas++;
                    continue;
                }

                String ruta = normalizarRuta(musica.getRuta());

                if (rutas.contains(ruta)) {
                    duplicadas++;
                    continue;
                }

                if (musica.getId() > 0 && hash.buscarPorId(musica.getId()) != null) {
                    duplicadas++;
                    continue;
                }

                asignarId(musica);

                boolean okLista = biblioteca.agregarMusica(musica);

                long inicioABB = System.nanoTime();
                boolean okABB = abb.agregarMusica(musica);
                tiempoABB += System.nanoTime() - inicioABB;

                long inicioAVL = System.nanoTime();
                boolean okAVL = avl.agregarMusica(musica);
                tiempoAVL += System.nanoTime() - inicioAVL;

                boolean okHash = hash.insertarMusica(musica);

                if (okLista && okABB && okAVL && okHash) {
                    rutas.add(ruta);
                    insertadas++;
                } else {
                    if (okLista) {
                        biblioteca.eliminarMusica(musica);
                    }

                    if (okABB) {
                        abb.eliminarMusica(musica);
                    }

                    if (okAVL) {
                        avl.eliminarMusica(musica);
                    }

                    if (okHash) {
                        hash.eliminarMusica(musica);
                    }

                    fallidas++;
                }
            }
        }

        long tiempoTotal = System.nanoTime() - inicioTotal;

        historial.registrarCarga(
                recibidas,
                insertadas,
                duplicadas,
                invalidas,
                fallidas,
                biblioteca.getCantidad(),
                abb.getCantidad(),
                avl.getCantidad(),
                hash.getCantidad(),
                tiempoABB,
                tiempoAVL,
                tiempoTotal
        );

        return crearResumenCargaUsuario(
                recibidas,
                insertadas,
                duplicadas,
                invalidas,
                fallidas,
                biblioteca.getCantidad(),
                abb.getCantidad(),
                avl.getCantidad(),
                hash.getCantidad(),
                tiempoABB,
                tiempoAVL
        );
    }
    
    private String crearResumenCargaUsuario(int recibidas, int insertadas, int duplicadas, int invalidas, int fallidas,
            int totalBiblioteca, int totalABB, int totalAVL, int totalHash, long tiempoABB, long tiempoAVL) {
        StringBuilder sb = new StringBuilder();

        sb.append("Proceso finalizado.\n\n");

        sb.append("Músicas recibidas: ").append(recibidas).append("\n");
        sb.append("Músicas insertadas: ").append(insertadas).append("\n");
        sb.append("Músicas duplicadas: ").append(duplicadas).append("\n");
        sb.append("Músicas inválidas: ").append(invalidas).append("\n");
        sb.append("Músicas fallidas: ").append(fallidas).append("\n\n");

        sb.append("Total en lista general: ").append(totalBiblioteca).append("\n");
        sb.append("Total en ABB: ").append(totalABB).append("\n");
        sb.append("Total en AVL: ").append(totalAVL).append("\n");
        sb.append("Total en tabla hash: ").append(totalHash).append("\n\n");

        sb.append("Tiempo de inserción ABB: ")
                .append(formatearMilisegundos(tiempoABB))
                .append(" ms\n");

        sb.append("Tiempo de inserción AVL: ")
                .append(formatearMilisegundos(tiempoAVL))
                .append(" ms");

        return sb.toString();
    }

    private String formatearMilisegundos(long nanosegundos) {
        return String.format("%.4f", nanosegundos / 1_000_000.0);
    }

    public List<Musica> buscarMusicas(String texto) {
        List<Musica> resultados = new ArrayList<>();
        Set<Integer> idsAgregados = new HashSet<>();

        if (texto == null || texto.trim().isEmpty()) {
            return biblioteca.listaMusicas();
        }

        String busqueda = texto.trim();

        agregarSinDuplicar(resultados, idsAgregados, abb.buscarMusica(busqueda));
        agregarSinDuplicar(resultados, idsAgregados, avl.buscarMusica(busqueda));
        agregarSinDuplicar(resultados, idsAgregados, buscarPorArtista(busqueda));
        agregarSinDuplicar(resultados, idsAgregados, buscarPorAlbum(busqueda));
        agregarSinDuplicar(resultados, idsAgregados, buscarPorGenero(busqueda));

        try {
            int numero = Integer.parseInt(busqueda);

            Musica musicaPorId = buscarPorId(numero);

            if (musicaPorId != null && idsAgregados.add(musicaPorId.getId())) {
                resultados.add(musicaPorId);
            }

            agregarSinDuplicar(resultados, idsAgregados, buscarPorAnio(numero));
        } catch (NumberFormatException e) {
        }

        return resultados;
    }
    
    public String compararBusquedaArboles(String nombre) {
        String texto = nombre == null ? "" : nombre.trim();

        if (texto.isEmpty()) {
            return "Debes ingresar el nombre exacto de una música.";
        }

        long inicioABB = System.nanoTime();
        Musica resultadoABB = abb.buscarExactaPorNombre(texto);
        long tiempoABB = System.nanoTime() - inicioABB;

        long inicioAVL = System.nanoTime();
        Musica resultadoAVL = avl.buscarExactaPorNombre(texto);
        long tiempoAVL = System.nanoTime() - inicioAVL;

        String masRapido;

        if (resultadoABB == null && resultadoAVL == null) {
            masRapido = "Ninguno encontró la música";
        } else if (tiempoABB < tiempoAVL) {
            masRapido = "ABB";
        } else if (tiempoAVL < tiempoABB) {
            masRapido = "AVL";
        } else {
            masRapido = "Empate";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Comparación de búsqueda ABB vs AVL\n\n");
        sb.append("Nombre buscado: ").append(texto).append("\n\n");

        sb.append("ABB\n");
        sb.append("Resultado: ").append(resultadoABB == null ? "No encontrado" : "Encontrado").append("\n");
        sb.append("Tiempo: ").append(formatearMilisegundos(tiempoABB)).append(" ms\n\n");

        sb.append("AVL\n");
        sb.append("Resultado: ").append(resultadoAVL == null ? "No encontrado" : "Encontrado").append("\n");
        sb.append("Tiempo: ").append(formatearMilisegundos(tiempoAVL)).append(" ms\n\n");

        sb.append("Estructura más rápida: ").append(masRapido);

        if (resultadoABB != null || resultadoAVL != null) {
            Musica encontrada = resultadoABB != null ? resultadoABB : resultadoAVL;

            sb.append("\n\nMúsica encontrada:\n");
            sb.append("ID: ").append(encontrada.getId()).append("\n");
            sb.append("Nombre: ").append(encontrada.getNombre()).append("\n");
            sb.append("Artista: ").append(encontrada.getArtista()).append("\n");
            sb.append("Álbum: ").append(encontrada.getAlbum()).append("\n");
            sb.append("Género: ").append(encontrada.getGenero());
        }

        return sb.toString();
    }

    public boolean eliminarMusica(Musica musica) {
        if (musica == null) {
            return false;
        }
        return eliminarMusicaPorId(musica.getId());
    }

    private boolean eliminarMusicaPorId(int id) {
        Musica musica = hash.buscarPorId(id);

        if (musica == null) {
            historial.registrarEliminacionMusica(
                    id,
                    null,
                    false,
                    0,
                    0,
                    0,
                    "Música no encontrada"
            );
            return false;
        }

        boolean okLista = biblioteca.eliminarMusica(musica);

        long inicioABB = System.nanoTime();
        boolean okABB = abb.eliminarMusica(musica);
        long tiempoABB = System.nanoTime() - inicioABB;

        long inicioAVL = System.nanoTime();
        boolean okAVL = avl.eliminarMusica(musica);
        long tiempoAVL = System.nanoTime() - inicioAVL;

        boolean okHash = hash.eliminarMusica(musica);

        boolean eliminada = okLista || okABB || okAVL || okHash;

        int playlistsAfectadas = 0;

        if (eliminada) {
            String ruta = normalizarRuta(musica.getRuta());

            if (!ruta.isEmpty()) {
                rutas.remove(ruta);
            }

            playlistsAfectadas = eliminarMusicaDePlaylists(musica);
        }

        historial.registrarEliminacionMusica(
                id,
                musica,
                eliminada,
                tiempoABB,
                tiempoAVL,
                playlistsAfectadas,
                eliminada
                        ? "Música eliminada correctamente de la biblioteca general"
                        : "No se pudo eliminar la música completamente"
        );

        return eliminada;
    }

    public Musica buscarPorId(int id) {
        return hash.buscarPorId(id);
    }

    public List<Musica> buscarPorArtista(String artista) {
        return hash.buscarPorArtista(artista);
    }

    public List<Musica> buscarPorAlbum(String album) {
        return hash.buscarPorAlbum(album);
    }

    public List<Musica> buscarPorGenero(String genero) {
        return hash.buscarPorGenero(genero);
    }

    public List<Musica> buscarPorAnio(int anio) {
        return hash.buscarPorAnio(anio);
    }

    public List<Playlist> getPlaylists() {
        return new ArrayList<>(playlists);
    }

    public boolean existePlaylist(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        String nombreBuscado = nombre.trim();

        for (Playlist playlist : playlists) {
            if (playlist.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return true;
            }
        }

        return false;
    }

    public Playlist buscarPlaylistPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }

        String nombreBuscado = nombre.trim();

        for (Playlist playlist : playlists) {
            if (playlist.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return playlist;
            }
        }

        return null;
    }

    public Playlist buscarPlaylistPorId(int id) {
        for (Playlist playlist : playlists) {
            if (playlist.getId() == id) {
                return playlist;
            }
        }

        return null;
    }

    public String crearPlaylist(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            historial.registrarCreacionPlaylist(
                    0,
                    "",
                    false,
                    "Nombre inválido"
            );

            return "No se pudo crear la playlist.\n"
                    + "Motivo: Nombre inválido.";
        }

        nombre = nombre.trim();

        if (existePlaylist(nombre)) {
            historial.registrarCreacionPlaylist(
                    0,
                    nombre,
                    false,
                    "Ya existe una playlist con ese nombre"
            );

            return "No se pudo crear la playlist.\n"
                    + "Motivo: Ya existe una playlist con ese nombre.";
        }

        Playlist playlist = new Playlist(idPlaylist++, nombre);
        playlists.add(playlist);

        historial.registrarCreacionPlaylist(
                playlist.getId(),
                playlist.getNombre(),
                true,
                "Playlist creada correctamente"
        );

        return "Playlist creada correctamente.\n"
                + "Nombre: " + playlist.getNombre();
    }

    public String agregarMusicasAPlaylist(int idPlaylist, List<Musica> musicas) {
        Playlist playlist = buscarPlaylistPorId(idPlaylist);

        if (playlist == null) {
            int invalidas = musicas == null ? 0 : musicas.size();

            historial.registrarMusicasAgregadasAPlaylist(
                    idPlaylist,
                    "No encontrada",
                    musicas,
                    0,
                    0,
                    invalidas,
                    false,
                    "Playlist no encontrada"
            );

            return "No se pudieron agregar músicas.\n"
                    + "Motivo: Playlist no encontrada.";
        }

        int recibidas = musicas == null ? 0 : musicas.size();
        int agregadas = 0;
        int duplicadas = 0;
        int invalidas = 0;

        if (musicas != null) {
            for (Musica musica : musicas) {
                if (musica == null || musica.getId() <= 0) {
                    invalidas++;
                    continue;
                }

                if (playlist.contieneMusica(musica)) {
                    duplicadas++;
                    continue;
                }

                if (playlist.agregarMusica(musica)) {
                    agregadas++;
                } else {
                    invalidas++;
                }
            }
        }

        historial.registrarMusicasAgregadasAPlaylist(
                playlist.getId(),
                playlist.getNombre(),
                musicas,
                agregadas,
                duplicadas,
                invalidas,
                true,
                "Proceso terminado"
        );

        return "Músicas agregadas a la playlist.\n"
                + "Playlist: " + playlist.getNombre() + "\n"
                + "Recibidas: " + recibidas + "\n"
                + "Agregadas: " + agregadas + "\n"
                + "Duplicadas: " + duplicadas + "\n"
                + "Inválidas: " + invalidas;
    }

    private void asignarId(Musica musica) {
        if (musica.getId() <= 0) {
            musica.setId(idMusica++);
            return;
        }

        if (musica.getId() >= idMusica) {
            idMusica = musica.getId() + 1;
        }
    }

    private boolean esMusicaValida(Musica musica) {
        return musica != null
                && musica.getNombre() != null
                && !musica.getNombre().trim().isEmpty()
                && musica.getRuta() != null
                && !musica.getRuta().trim().isEmpty();
    }

    private String normalizarRuta(String ruta) {
        if (ruta == null) {
            return "";
        }

        return ruta.trim().toLowerCase();
    }

    private void agregarSinDuplicar(
            List<Musica> destino,
            Set<Integer> idsAgregados,
            List<Musica> origen
    ) {
        if (origen == null) {
            return;
        }

        for (Musica musica : origen) {
            if (musica != null && idsAgregados.add(musica.getId())) {
                destino.add(musica);
            }
        }
    }
    
    private int eliminarMusicaDePlaylists(Musica musica) {
        if (musica == null) {
            return 0;
        }

        int playlistsAfectadas = 0;

        for (Playlist playlist : playlists) {
            if (playlist != null && playlist.contieneMusica(musica)) {
                if (playlist.eliminarMusica(musica)) {
                    playlistsAfectadas++;
                }
            }
        }

        return playlistsAfectadas;
    }
}