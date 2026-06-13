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
            sb.append("Género: ").append(encontrada.getGenero()).append("\n");
            sb.append("Reproducciones: ").append(encontrada.getReproducciones());
        }

        return sb.toString();
    }

    public String eliminarMusicas(List<Musica> musicas) {
        int recibidas = musicas == null ? 0 : musicas.size();
        int eliminadas = 0;
        int noEncontradas = 0;
        int invalidas = 0;
        int repetidas = 0;

        Set<Integer> idsProcesados = new HashSet<>();

        if (musicas == null || musicas.isEmpty()) {
            return "No se eliminaron músicas.\n"
                    + "Motivo: No se recibieron músicas.";
        }

        for (Musica musica : musicas) {
            if (musica == null || musica.getId() <= 0) {
                invalidas++;
                continue;
            }

            if (!idsProcesados.add(musica.getId())) {
                repetidas++;
                continue;
            }

            boolean eliminada = eliminarMusicaPorId(musica.getId());

            if (eliminada) {
                eliminadas++;
            } else {
                noEncontradas++;
            }
        }

        return "Proceso de eliminación finalizado.\n"
                + "Recibidas: " + recibidas + "\n"
                + "Eliminadas: " + eliminadas + "\n"
                + "No encontradas: " + noEncontradas + "\n"
                + "Inválidas: " + invalidas + "\n"
                + "Repetidas: " + repetidas;
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
    
    public String editarMusica(int id, String nombre, String artista, String album, String genero, int anio) {
        Musica musica = buscarPorId(id);

        if (musica == null) {
            historial.registrarEdicionMusica(
                    id,
                    "",
                    "",
                    "",
                    "",
                    0,
                    nombre,
                    artista,
                    album,
                    genero,
                    anio,
                    false,
                    false,
                    false,
                    false,
                    "Música no encontrada"
            );

            return "No se pudo editar la música.\n"
                    + "Motivo: Música no encontrada.";
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            registrarEdicionFallidaPorValidacion(musica, nombre, artista, album, genero, anio,
                    "El nombre no puede estar vacío.");

            return "No se pudo editar la música.\n"
                    + "Motivo: El nombre no puede estar vacío.";
        }

        if (artista == null || artista.trim().isEmpty()) {
            registrarEdicionFallidaPorValidacion(musica, nombre, artista, album, genero, anio,
                    "El artista no puede estar vacío.");

            return "No se pudo editar la música.\n"
                    + "Motivo: El artista no puede estar vacío.";
        }

        if (album == null || album.trim().isEmpty()) {
            registrarEdicionFallidaPorValidacion(musica, nombre, artista, album, genero, anio,
                    "El álbum no puede estar vacío.");

            return "No se pudo editar la música.\n"
                    + "Motivo: El álbum no puede estar vacío.";
        }

        if (genero == null || genero.trim().isEmpty()) {
            registrarEdicionFallidaPorValidacion(musica, nombre, artista, album, genero, anio,
                    "El género no puede estar vacío.");

            return "No se pudo editar la música.\n"
                    + "Motivo: El género no puede estar vacío.";
        }

        if (anio < 0) {
            registrarEdicionFallidaPorValidacion(musica, nombre, artista, album, genero, anio,
                    "El año no es válido.");

            return "No se pudo editar la música.\n"
                    + "Motivo: El año no es válido.";
        }

        nombre = nombre.trim();
        artista = artista.trim();
        album = album.trim();
        genero = genero.trim();

        String nombreAnterior = musica.getNombre();
        String artistaAnterior = musica.getArtista();
        String albumAnterior = musica.getAlbum();
        String generoAnterior = musica.getGenero();
        int anioAnterior = musica.getAnio();

        musica.setNombre(nombre);
        musica.setArtista(artista);
        musica.setAlbum(album);
        musica.setGenero(genero);
        musica.setAnio(anio);

        boolean okABB = abb.actualizarMusica(musica);
        boolean okAVL = avl.actualizarMusica(musica);
        boolean okHash = hash.actualizarMusica(musica);

        boolean editada = okABB && okAVL && okHash;

        if (!editada) {
            musica.setNombre(nombreAnterior);
            musica.setArtista(artistaAnterior);
            musica.setAlbum(albumAnterior);
            musica.setGenero(generoAnterior);
            musica.setAnio(anioAnterior);

            abb.actualizarMusica(musica);
            avl.actualizarMusica(musica);
            hash.actualizarMusica(musica);

            historial.registrarEdicionMusica(
                    id,
                    nombreAnterior,
                    artistaAnterior,
                    albumAnterior,
                    generoAnterior,
                    anioAnterior,
                    nombre,
                    artista,
                    album,
                    genero,
                    anio,
                    false,
                    okABB,
                    okAVL,
                    okHash,
                    "No se pudo completar la edición. Se restauraron los datos anteriores."
            );

            return "No se pudo editar la música completamente.\n"
                    + "Se restauraron los datos anteriores.";
        }

        historial.registrarEdicionMusica(
                id,
                nombreAnterior,
                artistaAnterior,
                albumAnterior,
                generoAnterior,
                anioAnterior,
                nombre,
                artista,
                album,
                genero,
                anio,
                true,
                okABB,
                okAVL,
                okHash,
                "Música editada correctamente."
        );

        return "Música editada correctamente.\n"
                + "Antes: " + nombreAnterior + "\n"
                + "Ahora: " + musica.getNombre();
    }
    
    private void registrarEdicionFallidaPorValidacion(
            Musica musica,
            String nombreNuevo,
            String artistaNuevo,
            String albumNuevo,
            String generoNuevo,
            int anioNuevo,
            String mensaje
    ) {
        historial.registrarEdicionMusica(
                musica.getId(),
                musica.getNombre(),
                musica.getArtista(),
                musica.getAlbum(),
                musica.getGenero(),
                musica.getAnio(),
                nombreNuevo,
                artistaNuevo,
                albumNuevo,
                generoNuevo,
                anioNuevo,
                false,
                false,
                false,
                false,
                mensaje
        );
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
    
    public Musica buscarPorRuta(String ruta) {
        String rutaBuscada = normalizarRuta(ruta);

        if (rutaBuscada.isEmpty()) {
            return null;
        }

        for (Musica musica : biblioteca.listaMusicas()) {
            if (musica != null && normalizarRuta(musica.getRuta()).equals(rutaBuscada)) {
                return musica;
            }
        }

        return null;
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
    
    public String eliminarPlaylist(int idPlaylist) {
        Playlist playlist = buscarPlaylistPorId(idPlaylist);

        if (playlist == null) {
            historial.registrarEliminacionPlaylist(
                    idPlaylist,
                    "No encontrada",
                    0,
                    false,
                    "Playlist no encontrada"
            );

            return "No se pudo eliminar la playlist.\n"
                    + "Motivo: Playlist no encontrada.";
        }

        String nombre = playlist.getNombre();
        int cantidadMusicas = playlist.getCantidad();

        boolean eliminada = playlists.remove(playlist);

        historial.registrarEliminacionPlaylist(
                playlist.getId(),
                nombre,
                cantidadMusicas,
                eliminada,
                eliminada
                        ? "Playlist eliminada correctamente"
                        : "No se pudo eliminar la playlist"
        );

        if (!eliminada) {
            return "No se pudo eliminar la playlist.";
        }

        return "Playlist eliminada correctamente.\n"
                + "Nombre: " + nombre + "\n"
                + "Músicas que tenía: " + cantidadMusicas;
    }
    
    public String quitarMusicasDePlaylist(int idPlaylist, List<Musica> musicas) {
        Playlist playlist = buscarPlaylistPorId(idPlaylist);

        if (playlist == null) {
            int recibidas = musicas == null ? 0 : musicas.size();

            historial.registrarMusicasQuitadasDePlaylist(
                    idPlaylist,
                    "No encontrada",
                    musicas,
                    0,
                    recibidas,
                    false,
                    "Playlist no encontrada"
            );

            return "No se pudieron quitar músicas.\n"
                    + "Motivo: Playlist no encontrada.";
        }

        int recibidas = musicas == null ? 0 : musicas.size();
        int quitadas = 0;
        int noEncontradas = 0;
        int invalidas = 0;

        Set<Integer> idsProcesados = new HashSet<>();

        if (musicas != null) {
            for (Musica musica : musicas) {
                if (musica == null || musica.getId() <= 0) {
                    invalidas++;
                    continue;
                }

                if (!idsProcesados.add(musica.getId())) {
                    noEncontradas++;
                    continue;
                }

                if (!playlist.contieneMusica(musica)) {
                    noEncontradas++;
                    continue;
                }

                if (playlist.eliminarMusica(musica)) {
                    quitadas++;
                } else {
                    noEncontradas++;
                }
            }
        }

        historial.registrarMusicasQuitadasDePlaylist(
                playlist.getId(),
                playlist.getNombre(),
                musicas,
                quitadas,
                noEncontradas + invalidas,
                true,
                "Proceso terminado"
        );

        return "Músicas quitadas de la playlist.\n"
                + "Playlist: " + playlist.getNombre() + "\n"
                + "Recibidas: " + recibidas + "\n"
                + "Quitadas: " + quitadas + "\n"
                + "No encontradas: " + noEncontradas + "\n"
                + "Inválidas: " + invalidas;
    }
    
    public String renombrarPlaylist(int idPlaylist, String nuevoNombre) {
        Playlist playlist = buscarPlaylistPorId(idPlaylist);

        if (playlist == null) {
            historial.registrarEdicionPlaylist(
                    idPlaylist,
                    "No encontrada",
                    nuevoNombre,
                    false,
                    "Playlist no encontrada"
            );

            return "No se pudo renombrar la playlist.\n"
                    + "Motivo: Playlist no encontrada.";
        }

        String nombreAnterior = playlist.getNombre();

        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            historial.registrarEdicionPlaylist(
                    playlist.getId(),
                    nombreAnterior,
                    "",
                    false,
                    "Nombre inválido"
            );

            return "No se pudo renombrar la playlist.\n"
                    + "Motivo: Nombre inválido.";
        }

        String nombreNuevo = nuevoNombre.trim();

        if (nombreAnterior.equalsIgnoreCase(nombreNuevo)) {
            historial.registrarEdicionPlaylist(
                    playlist.getId(),
                    nombreAnterior,
                    nombreNuevo,
                    false,
                    "El nombre es el mismo; no se realizaron cambios"
            );

            return "La playlist ya tiene ese nombre.\n"
                    + "No se realizaron cambios.";
        }

        if (existeOtraPlaylistConNombre(playlist.getId(), nombreNuevo)) {
            historial.registrarEdicionPlaylist(
                    playlist.getId(),
                    nombreAnterior,
                    nombreNuevo,
                    false,
                    "Ya existe otra playlist con ese nombre"
            );

            return "No se pudo renombrar la playlist.\n"
                    + "Motivo: Ya existe otra playlist con ese nombre.";
        }

        playlist.setNombre(nombreNuevo);

        historial.registrarEdicionPlaylist(
                playlist.getId(),
                nombreAnterior,
                playlist.getNombre(),
                true,
                "Playlist renombrada correctamente"
        );

        return "Playlist renombrada correctamente.\n"
                + "Antes: " + nombreAnterior + "\n"
                + "Ahora: " + playlist.getNombre();
    }
    
    private boolean existeOtraPlaylistConNombre(int idPlaylistActual, String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        String nombreBuscado = nombre.trim();
        for (Playlist playlist : playlists) {
            if (playlist.getId() != idPlaylistActual
                    && playlist.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return true;
            }
        }
        return false;
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

    private void agregarSinDuplicar(List<Musica> destino, Set<Integer> idsAgregados, List<Musica> origen) {
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