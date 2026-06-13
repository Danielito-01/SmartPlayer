# Manual de Usuario - SmartPlayer

## 1. Introducción

SmartPlayer es un reproductor musical de escritorio que permite administrar canciones locales, crear playlists, reproducir música, manejar cola de reproducción, consultar historial, buscar canciones y visualizar estadísticas.

El sistema trabaja con archivos de música almacenados localmente en la computadora del usuario.

## 2. Requisitos previos

Antes de usar la aplicación, se recomienda tener:

- Java instalado.
- NetBeans configurado.
- Archivos de música en formato MP3.
- Una carpeta local con canciones para realizar pruebas.
- Graphviz instalado si se desea visualizar árboles.

## 3. Abrir el proyecto

1. Abrir NetBeans.
2. Seleccionar la opción para abrir un proyecto existente.
3. Buscar la carpeta del proyecto SmartPlayer.
4. Abrir el proyecto.
5. Ejecutar la clase principal.

## 4. Pantalla principal

Al iniciar SmartPlayer se muestra la ventana principal. Desde esta pantalla se puede acceder a:

- Biblioteca musical.
- Carga de canciones.
- Playlists.
- Cola de reproducción.
- Historial.
- Estadísticas.
- Búsqueda en árboles.
- Visualización de árboles.
- Importación y exportación de playlists.

## 5. Cargar canciones

Para cargar canciones:

1. Presionar la opción de cargar música.
2. Seleccionar una carpeta o archivo MP3.
3. Confirmar la selección.
4. Esperar a que el sistema procese las canciones.
5. Revisar el resumen de carga.

El sistema busca canciones dentro de la carpeta seleccionada y también en sus subcarpetas.

### Ejemplo

Si el usuario selecciona esta carpeta:

```text
Música/
├── Rock/
│   ├── cancion1.mp3
│   └── cancion2.mp3
└── Pop/
    └── cancion3.mp3
```

SmartPlayer detectará las canciones dentro de `Rock` y `Pop` automáticamente.

## 6. Ver biblioteca musical

La biblioteca muestra la información de las canciones cargadas:

- Nombre.
- Artista.
- Álbum.
- Género.
- Duración.
- Tamaño.
- Ruta.
- Año.

Desde la biblioteca se puede seleccionar una canción para reproducirla, agregarla a una playlist o agregarla a la cola.

## 7. Reproducir canciones

Para reproducir una canción:

1. Seleccionar una canción de la tabla.
2. Presionar el botón de reproducción.
3. Usar los controles disponibles:
   - Reproducir.
   - Pausar.
   - Continuar.
   - Siguiente.
   - Anterior.

Cuando una canción se reproduce, se actualiza el historial y las estadísticas.

## 8. Crear una playlist

Para crear una playlist:

1. Abrir la opción de nueva playlist.
2. Escribir el nombre de la playlist.
3. Confirmar la creación.
4. Seleccionar canciones de la biblioteca.
5. Agregarlas a la playlist.

### Ejemplo

Nombre de playlist:

```text
Favoritas
```

Luego se pueden agregar canciones desde la biblioteca a esa playlist.

## 9. Administrar playlists

Desde la administración de playlists se puede:

- Crear playlist.
- Eliminar playlist.
- Agregar canciones.
- Eliminar canciones.
- Seleccionar una playlist para reproducirla.

## 10. Modos de reproducción de playlist

SmartPlayer permite diferentes formas de reproducción:

| Modo | Descripción |
|---|---|
| Normal | Reproduce las canciones en orden. |
| Aleatorio | Reproduce canciones en orden aleatorio. |
| Circular / infinito | Al terminar la última canción, vuelve a iniciar desde la primera. |

## 11. Cola de reproducción

La cola permite preparar canciones para que se reproduzcan automáticamente después de la canción actual.

Para usar la cola:

1. Seleccionar una canción.
2. Presionar la opción para agregar a cola.
3. Repetir con otras canciones.
4. Al finalizar la canción actual, SmartPlayer toma la siguiente canción de la cola.

La cola utiliza el orden en que se agregaron las canciones.

## 12. Historial de reproducción

El historial muestra canciones reproducidas recientemente. La canción más reciente aparece primero.

Para consultarlo:

1. Abrir la opción de historial.
2. Revisar las canciones reproducidas.
3. Usar esta información para validar estadísticas o recordar qué canciones se escucharon.

## 13. Buscar canciones con ABB y AVL

SmartPlayer permite buscar canciones usando dos estructuras:

- Árbol Binario de Búsqueda.
- Árbol AVL.

Para comparar búsquedas:

1. Abrir la ventana de búsqueda en árboles.
2. Escribir el nombre o criterio de búsqueda.
3. Ejecutar la búsqueda.
4. Revisar los resultados y tiempos.

El sistema muestra el tiempo de respuesta de cada estructura para comparar eficiencia.

## 14. Visualizar árboles

Para visualizar árboles:

1. Cargar canciones en la biblioteca.
2. Abrir la opción de visualización de árboles.
3. Seleccionar ABB o AVL.
4. Generar la visualización.

Si Graphviz está instalado correctamente, se generará una imagen del árbol.

## 15. Ver recorridos de árboles

Los recorridos permiten mostrar las canciones del árbol en diferentes órdenes:

- InOrden.
- PreOrden.
- PostOrden.

Para verlos:

1. Abrir la ventana de recorridos.
2. Seleccionar el árbol.
3. Elegir el recorrido disponible.
4. Revisar la lista generada.

## 16. Ver estadísticas

La ventana de estadísticas muestra información general del uso musical, por ejemplo:

- Canción más reproducida.
- Artista más escuchado.
- Género más frecuente.
- Playlist más grande o más larga.
- Promedio de duración de canciones.
- Tamaño total de canciones.
- Comparación de tiempos ABB y AVL.

## 17. Exportar una playlist

Para exportar una playlist:

1. Abrir la ventana de importación/exportación.
2. Seleccionar la playlist que se desea exportar.
3. Elegir la ruta donde se guardará el archivo.
4. Indicar si se desea encriptar.
5. Si se encripta, escribir una clave.
6. Confirmar exportación.

El sistema genera un archivo que puede usarse para recuperar la playlist posteriormente.

## 18. Importar una playlist

Para importar una playlist:

1. Abrir la ventana de importación/exportación.
2. Seleccionar el archivo de playlist.
3. Indicar si el archivo está encriptado.
4. Si está encriptado, escribir la clave correcta.
5. Confirmar importación.
6. Revisar la nueva playlist creada o recuperada.

Si algunas canciones no existen en la biblioteca actual, el sistema intentará recuperar la playlist con las canciones disponibles o dejar referencias pendientes según la implementación configurada.

## 19. Recomendaciones de uso

- Cargar primero toda la biblioteca antes de importar playlists.
- Usar carpetas organizadas para facilitar pruebas.
- No mover archivos MP3 después de cargarlos, porque la ruta puede quedar desactualizada.
- Probar primero con pocas canciones y luego con una carpeta grande.
- Para defensa, preparar una carpeta con al menos 2,000 canciones.

## 20. Errores comunes

| Situación | Posible causa | Solución |
|---|---|---|
| No reproduce una canción | Ruta inválida o archivo movido | Volver a cargar la carpeta correcta |
| No aparecen metadatos completos | El archivo MP3 no tiene etiquetas completas | Revisar archivo o aceptar valores desconocidos |
| No se visualiza el árbol | Graphviz no está instalado o configurado | Instalar Graphviz y revisar la ruta |
| No se importa una playlist encriptada | Clave incorrecta o no se marcó como encriptada | Reintentar usando la opción correcta |
| Canciones faltantes en playlist importada | No están cargadas en la biblioteca actual | Cargar primero la carpeta original de música |

## 21. Flujo recomendado para demostración

1. Abrir SmartPlayer.
2. Cargar carpeta de canciones.
3. Mostrar biblioteca.
4. Reproducir una canción.
5. Crear una playlist.
6. Agregar canciones a la playlist.
7. Reproducir en modo circular.
8. Agregar canciones a la cola.
9. Mostrar historial.
10. Comparar búsqueda ABB vs AVL.
11. Visualizar árbol.
12. Mostrar estadísticas.
13. Exportar playlist.
14. Importar playlist.
15. Probar encriptación y desencriptación.

## 22. Conclusión

SmartPlayer permite administrar música local de forma sencilla mientras demuestra el uso práctico de estructuras de datos. El usuario puede reproducir canciones, crear playlists, consultar estadísticas y observar cómo distintas estructuras influyen en el rendimiento del sistema.
