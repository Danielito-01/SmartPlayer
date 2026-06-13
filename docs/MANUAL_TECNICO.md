# Manual Técnico - SmartPlayer

## 1. Descripción general

SmartPlayer es una aplicación de escritorio desarrollada en Java para administrar y reproducir música local. El sistema permite cargar canciones desde carpetas, organizar playlists, reproducir canciones, manejar cola de reproducción, registrar historial, generar estadísticas y comparar el rendimiento de búsqueda entre un Árbol Binario de Búsqueda y un Árbol AVL.

El proyecto fue construido aplicando estructuras de datos dinámicas estudiadas en Programación III / Estructura de Datos. Su almacenamiento se basa en archivos locales y estructuras en memoria, sin uso de bases de datos.

## 2. Objetivo técnico

Implementar un sistema funcional que permita demostrar el uso de estructuras dinámicas en un escenario real de reproducción multimedia. El sistema administra canciones, playlists, búsquedas, historial, cola de reproducción, recorridos de árboles, visualización gráfica y exportación/importación de playlists.

## 3. Arquitectura del sistema

El proyecto está organizado por paquetes para separar responsabilidades:

```text
src/
├── estructuras/
├── modelos/
├── recursos/
├── servicios/
├── smartplayer/
├── utilidades/
└── vistas/
```

### 3.1 Paquete `modelos`

Contiene las clases que representan los datos principales del sistema.

| Clase | Responsabilidad |
|---|---|
| `Musica` | Representa una canción con sus metadatos: nombre, artista, álbum, género, duración, tamaño, ruta, año e identificador. |
| `Playlist` | Representa una lista de reproducción creada por el usuario y su colección de canciones. |

### 3.2 Paquete `estructuras`

Contiene las estructuras de datos implementadas manualmente para el proyecto.

| Clase | Estructura | Uso |
|---|---|---|
| `ListaMusicas` | Lista doble y comportamiento circular | Almacena canciones de biblioteca y playlists. Permite navegación anterior/siguiente y modo infinito. |
| `ColaReproduccion` | Cola | Administra las canciones pendientes de reproducción automática. |
| `PilaHistorial` | Pila | Guarda las canciones reproducidas recientemente. |
| `ArbolABB` | Árbol Binario de Búsqueda | Permite insertar, buscar, modificar, eliminar, recorrer y visualizar canciones. |
| `ArbolAVL` | Árbol AVL | Mantiene un índice balanceado para búsquedas eficientes. |
| `BibliotecaGeneral` | Estructura coordinadora | Centraliza biblioteca, playlists, ABB, AVL, tiempos y operaciones principales. |
| `TablaHash` | Tabla hash | Apoya búsquedas o validaciones internas según el uso del proyecto. |

### 3.3 Paquete `servicios`

Contiene lógica funcional que no pertenece directamente a la interfaz ni a las estructuras.

| Clase | Responsabilidad |
|---|---|
| `GestorCargaDeMusicas` | Lee archivos MP3 desde carpetas y subcarpetas, valida canciones y obtiene metadatos. |
| `GestorReproductor` | Controla reproducción, pausa, continuación y estado del reproductor. |
| `GestorEstadistica` | Genera estadísticas musicales y resúmenes. |
| `GestorArchivoPlaylist` | Exporta, importa, encripta y desencripta playlists. |
| `GestorHistorial` | Administra la información del historial de reproducción. |
| `GestorGraphviz` | Genera visualizaciones de árboles mediante archivos DOT/Graphviz. |
| `GestorPortada` | Maneja portadas o recursos visuales de canciones cuando están disponibles. |

### 3.4 Paquete `vistas`

Contiene las ventanas y diálogos de la interfaz gráfica.

| Clase | Uso |
|---|---|
| `VentanaPrincipal` | Pantalla principal del reproductor. |
| `DialogoCargaMusicas` | Carga canciones desde archivos o carpetas. |
| `DialogoBibliotecaGeneral` | Muestra y administra la biblioteca. |
| `DialogoAdministrarPlaylist` | Permite agregar o eliminar canciones de playlists. |
| `DialogoNuevaPlaylist` | Permite crear una nueva playlist. |
| `DialogoArchivosPlaylist` | Importa/exporta playlists, con opción de encriptación. |
| `DialogoBusquedaEnArboles` | Compara búsquedas entre ABB y AVL. |
| `DialogoRecorrido` | Muestra recorridos del árbol. |
| `DialogoVisualizacionArboles` | Visualiza árboles mediante Graphviz. |
| `DialogoEstadisticas` | Presenta estadísticas musicales. |
| `DialogoHistorial` / `DialogoPilaHistorial` | Presenta historial de reproducción. |

## 4. Flujo técnico principal

1. El usuario selecciona una carpeta desde la interfaz.
2. `GestorCargaDeMusicas` recorre la carpeta y subcarpetas.
3. Por cada archivo MP3 válido se crea un objeto `Musica`.
4. La canción se inserta en `BibliotecaGeneral`.
5. `BibliotecaGeneral` actualiza las estructuras internas:
   - Lista de canciones.
   - Árbol ABB.
   - Árbol AVL.
   - Estructuras auxiliares.
6. El usuario puede buscar, reproducir, administrar playlists o consultar estadísticas.
7. Al reproducir canciones, se actualizan historial, contadores y cola.
8. Las playlists pueden guardarse en archivos y recuperarse posteriormente.

## 5. Estructuras utilizadas

### 5.1 Lista doble

Se utiliza para almacenar canciones en biblioteca y playlists. Aunque el enunciado solicitaba una lista simple para la biblioteca, se implementó una lista doble para reutilizar la navegación hacia adelante y hacia atrás, evitando duplicación de código.

Operaciones principales:

- Insertar canción.
- Eliminar canción.
- Obtener actual.
- Avanzar a siguiente.
- Retroceder a anterior.
- Recorrer canciones.

Complejidad aproximada:

| Operación | Complejidad |
|---|---|
| Insertar al final | O(1) si se conserva referencia a último nodo |
| Eliminar por búsqueda | O(n) |
| Siguiente / anterior | O(1) |
| Recorrido completo | O(n) |

### 5.2 Lista circular

El modo circular permite que, al llegar al final de una playlist, el reproductor pueda volver al inicio. Esto implementa la reproducción infinita o repetición continua.

Complejidad:

| Operación | Complejidad |
|---|---|
| Avanzar circularmente | O(1) |
| Retroceder circularmente | O(1) |

### 5.3 Pila

La pila se utiliza para el historial de reproducción. Cada canción reproducida se agrega sobre la cima, permitiendo consultar primero la canción más reciente.

Complejidad:

| Operación | Complejidad |
|---|---|
| Push | O(1) |
| Pop | O(1) |
| Peek | O(1) |
| Recorrido del historial | O(n) |

### 5.4 Cola

La cola administra canciones pendientes de reproducción. Se utiliza un comportamiento FIFO: la primera canción agregada es la primera en reproducirse.

Complejidad:

| Operación | Complejidad |
|---|---|
| Encolar | O(1) |
| Desencolar | O(1) |
| Consultar frente | O(1) |
| Recorrer cola | O(n) |

### 5.5 Árbol Binario de Búsqueda ABB

El ABB almacena canciones ordenadas principalmente por nombre y permite búsqueda, inserción, modificación, eliminación y recorridos.

Operaciones principales:

- Insertar canción.
- Buscar canción.
- Eliminar canción.
- Actualizar canción.
- Generar recorrido InOrden.
- Generar archivo DOT para visualización.

Complejidad:

| Caso | Búsqueda | Inserción | Eliminación |
|---|---:|---:|---:|
| Promedio | O(log n) | O(log n) | O(log n) |
| Peor caso | O(n) | O(n) | O(n) |

El peor caso ocurre si los datos se insertan de forma muy ordenada y el árbol se desbalancea.

### 5.6 Árbol AVL

El AVL es un árbol binario de búsqueda balanceado. Después de insertar, calcula el factor de balance y aplica rotaciones para conservar una altura controlada.

Rotaciones utilizadas:

| Caso | Solución |
|---|---|
| RI | Rotación izquierda |
| RD | Rotación derecha |
| RID | Rotación izquierda en hijo y derecha en nodo |
| RDI | Rotación derecha en hijo y izquierda en nodo |

Complejidad:

| Operación | Complejidad |
|---|---|
| Búsqueda | O(log n) |
| Inserción | O(log n) |
| Eliminación | O(log n), si se reequilibra |
| Balanceo | O(1) por nodo evaluado |

### 5.7 Arreglos y listas auxiliares

Se utilizan para resúmenes, tablas, reportes, estadísticas y comparaciones de resultados. Permiten mostrar información en la interfaz de forma ordenada.

## 6. Algoritmos implementados

### 6.1 Carga recursiva de canciones

El sistema recorre carpetas y subcarpetas buscando archivos MP3. Por cada archivo válido:

1. Lee la información básica del archivo.
2. Intenta obtener metadatos.
3. Crea un objeto `Musica`.
4. Lo inserta en la biblioteca.
5. Actualiza ABB y AVL.

### 6.2 Búsqueda ABB vs AVL

El usuario ingresa un criterio de búsqueda. El sistema ejecuta la búsqueda en ambas estructuras y mide el tiempo usando una medición temporal de alta precisión. Luego muestra los resultados y compara cuál estructura respondió más rápido.

### 6.3 Reproducción musical

El reproductor permite:

- Reproducir canción seleccionada.
- Pausar canción.
- Continuar reproducción.
- Pasar a la siguiente canción.
- Regresar a la canción anterior.
- Tomar canciones desde la cola cuando existen pendientes.
- Reproducir playlists en modo normal, aleatorio o circular.

### 6.4 Administración de playlists

Las playlists permiten:

- Crear playlist.
- Eliminar playlist.
- Agregar canciones.
- Eliminar canciones.
- Reproducir canciones de la playlist.
- Exportar playlist.
- Importar playlist.
- Encriptar archivo exportado.
- Desencriptar archivo importado.

### 6.5 Encriptación y desencriptación de playlists

El sistema puede exportar playlists en formato protegido. La playlist se guarda en un archivo y puede recuperarse posteriormente usando la clave correspondiente. Si el archivo importado contiene canciones que no están cargadas en la biblioteca actual, el sistema puede conservar la referencia pendiente o recuperar únicamente las canciones disponibles, según la lógica implementada.

### 6.6 Visualización de árboles

Los árboles generan una representación en formato DOT. Luego Graphviz transforma esa descripción en una imagen visual para facilitar la defensa y explicación del comportamiento del ABB y AVL.

## 7. Estadísticas implementadas

El sistema genera estadísticas como:

- Canción más reproducida.
- Artista más escuchado.
- Playlist más grande o más larga.
- Género más frecuente.
- Promedio de duración de canciones.
- Tamaño total de canciones.
- Comparación de búsqueda ABB y AVL.

## 8. Restricciones técnicas

- El proyecto no utiliza base de datos.
- El almacenamiento se realiza con archivos locales.
- Las estructuras principales son implementadas manualmente.
- El proyecto debe ejecutarse sin conexión a Internet.
- La interfaz se construye como aplicación de escritorio.

## 9. Recomendaciones para defensa técnica

Durante la defensa, se recomienda demostrar el proyecto en este orden:

1. Cargar una carpeta con canciones.
2. Mostrar biblioteca y metadatos.
3. Reproducir una canción.
4. Crear una playlist.
5. Reproducir la playlist en modo circular.
6. Agregar canciones a la cola.
7. Mostrar historial.
8. Buscar en ABB y AVL.
9. Visualizar árboles.
10. Mostrar estadísticas.
11. Exportar una playlist.
12. Importar una playlist.
13. Probar encriptación y desencriptación.

## 10. Conclusión técnica

SmartPlayer demuestra la aplicación de estructuras de datos en un sistema funcional. El uso de listas permite administrar la navegación musical, la cola y la pila representan procesos reales de reproducción, y los árboles ABB y AVL permiten comparar eficiencia de búsqueda. La separación por paquetes facilita el mantenimiento y la explicación del proyecto durante la defensa.
