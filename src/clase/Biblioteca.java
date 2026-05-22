package clase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Biblioteca {
    private int nextId = 1;
    private static final Biblioteca INSTANCE = new Biblioteca();
    private final ListaMusicas listaGeneral = new ListaMusicas();
    private final Set<String> rutas = new HashSet<>();

    private Biblioteca() {}

    public static Biblioteca getInstance() {
        return INSTANCE;
    }

    public ListaMusicas getListaGeneral() {
        return listaGeneral;
    }

    public int agregarSinRepetir(List<Musica> nuevas) {
        if (nuevas == null) return 0;

        int agregadas = 0;
        for (Musica m : nuevas) {
            if (m == null || m.getRuta() == null || m.getRuta().isBlank()) continue;
            String key = m.getRuta().trim().toLowerCase();
            if (!rutas.add(key)) {
                continue; // ya existe
            }
            if (m.getId() <= 0) {
                m.setId(nextId++);
            }
            listaGeneral.agregar(m);
            agregadas++;
        }
        return agregadas;
    }
}