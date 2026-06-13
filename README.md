# SmartPlayer

SmartPlayer es una aplicación de escritorio desarrollada en Java para la gestión y reproducción de música local. El proyecto permite cargar canciones desde carpetas, administrar playlists, reproducir música, manejar cola de reproducción, consultar historial, buscar canciones mediante estructuras de datos y visualizar árboles de búsqueda.

Este proyecto fue desarrollado para el curso de Programación III / Estructura de Datos, aplicando estructuras dinámicas como listas, pilas, colas, árboles ABB, árboles AVL y arreglos para el análisis de información musical.

## Características principales

- Carga de canciones MP3 desde archivos o carpetas.
- Búsqueda recursiva en subcarpetas.
- Lectura de metadatos musicales: nombre, artista, álbum, género, año, duración, tamaño y ruta.
- Reproducción musical con controles de reproducir, pausar, continuar, siguiente y anterior.
- Creación, edición y eliminación de playlists.
- Reproducción normal, aleatoria y circular/infinita.
- Cola de reproducción automática.
- Historial de canciones reproducidas.
- Búsqueda y comparación de eficiencia entre ABB y AVL.
- Visualización de árboles mediante Graphviz.
- Estadísticas musicales generales.
- Exportación e importación de playlists.
- Encriptación y desencriptación de playlists exportadas.

## Tecnologías utilizadas

- Java
- NetBeans
- Swing
- JavaFX MediaPlayer
- FlatLaf
- JAudioTagger
- Graphviz
- Archivos locales como fuente y almacenamiento de información

## Estructuras de datos implementadas

| Estructura | Clase principal | Uso dentro del sistema |
|---|---|---|
| Lista doble | `ListaMusicas` | Biblioteca musical, playlists y navegación siguiente/anterior |
| Lista circular | `ListaMusicas` | Modo de reproducción circular o infinita |
| Pila | `PilaHistorial` | Historial de canciones reproducidas |
| Cola | `ColaReproduccion` | Cola de reproducción automática |
| Árbol ABB | `ArbolABB` | Inserción, búsqueda, modificación, eliminación, recorridos y visualización |
| Árbol AVL | `ArbolAVL` | Índice balanceado para búsqueda eficiente y comparación contra ABB |
| Arreglos / listas auxiliares | Varias clases | Estadísticas, reportes y carga de datos |
| Tabla hash | `TablaHash` | Apoyo para búsquedas o control interno de canciones |

> Nota: aunque el enunciado solicitaba una lista simple para la biblioteca, en este proyecto se utiliza una lista doble para la biblioteca y las playlists. Esta decisión permite reutilizar la navegación hacia adelante y hacia atrás sin duplicar estructuras, manteniendo la funcionalidad requerida.

## Arquitectura del proyecto

```text
SmartPlayer/
├── nbproject/              # Configuración del proyecto NetBeans
├── src/
│   ├── estructuras/        # Estructuras de datos propias
│   ├── modelos/            # Clases de dominio: Musica, Playlist
│   ├── recursos/           # Recursos gráficos o archivos auxiliares
│   ├── servicios/          # Lógica de carga, reproducción, estadísticas y archivos
│   ├── smartplayer/        # Clase principal de arranque
│   ├── utilidades/         # Utilidades generales
│   └── vistas/             # Interfaz gráfica Swing
├── docs/                   # Manuales e informe del proyecto
├── build.xml               # Archivo de construcción Ant/NetBeans
└── README.md               # Descripción general del proyecto
```

## Documentación

La documentación del proyecto se encuentra en la carpeta `docs/`:

- [`docs/MANUAL_TECNICO.md`](docs/MANUAL_TECNICO.md)
- [`docs/MANUAL_USUARIO.md`](docs/MANUAL_USUARIO.md)
- [`docs/INFORME_COMPARATIVO.md`](docs/INFORME_COMPARATIVO.md)

## Requisitos para ejecutar

- Java instalado.
- NetBeans IDE.
- JavaFX configurado para reproducción multimedia.
- Graphviz instalado si se desea visualizar árboles gráficamente.
- Carpeta local con archivos `.mp3` para realizar pruebas.

## Ejecución del proyecto

1. Clonar el repositorio:

```bash
git clone https://github.com/Danielito-01/SmartPlayer.git
```

2. Abrir el proyecto en NetBeans.
3. Verificar que las librerías requeridas estén agregadas al proyecto.
4. Ejecutar la clase principal desde el paquete `smartplayer`.
5. Cargar canciones desde una carpeta local.
6. Utilizar las opciones de biblioteca, playlists, cola, historial, estadísticas y árboles.

## Flujo general de uso

1. El usuario selecciona una carpeta con canciones.
2. El sistema recorre la carpeta y subcarpetas para detectar archivos MP3.
3. Cada canción válida se agrega a la biblioteca y a los índices ABB y AVL.
4. El usuario puede buscar, reproducir, crear playlists o administrar la cola.
5. Las reproducciones alimentan el historial y las estadísticas.
6. Las playlists pueden exportarse, encriptarse, importarse y recuperarse.
7. El sistema permite comparar el rendimiento de búsqueda entre ABB y AVL.

## Pruebas recomendadas para defensa

- Cargar una carpeta con al menos 2,000 canciones.
- Medir y mostrar tiempo de carga en ABB y AVL.
- Buscar canciones por nombre, artista o álbum.
- Comparar tiempo de búsqueda entre ABB y AVL.
- Crear una playlist y reproducirla en modo circular.
- Agregar canciones a la cola y reproducirlas automáticamente.
- Revisar historial de reproducción.
- Mostrar recorridos y visualización de árboles.
- Exportar, encriptar, importar y desencriptar una playlist.
- Mostrar estadísticas generales.

## Autor

**Daniel**  
Proyecto Final - Programación III / Estructura de Datos  
Universidad Mariano Gálvez de Guatemala
