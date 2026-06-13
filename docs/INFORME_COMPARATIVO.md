# Informe Comparativo de Eficiencia y Análisis de Estructuras - SmartPlayer

## 1. Introducción

Este informe presenta el análisis de las estructuras de datos utilizadas en SmartPlayer, un sistema de gestión y reproducción musical desarrollado en Java. El objetivo principal es comparar la eficiencia de búsqueda entre un Árbol Binario de Búsqueda y un Árbol AVL, además de justificar el uso de listas, pilas, colas y estructuras auxiliares dentro del proyecto.

## 2. Objetivo del informe

Analizar el comportamiento de las estructuras de datos implementadas en SmartPlayer y comparar su eficiencia en operaciones reales del sistema, especialmente en carga y búsqueda de canciones.

## 3. Estructuras evaluadas

| Estructura | Clase | Uso principal |
|---|---|---|
| Lista doble | `ListaMusicas` | Biblioteca, playlists y navegación musical |
| Lista circular | `ListaMusicas` | Reproducción infinita de playlists |
| Pila | `PilaHistorial` | Historial de reproducción |
| Cola | `ColaReproduccion` | Cola de canciones pendientes |
| ABB | `ArbolABB` | Búsqueda, inserción, eliminación, modificación y recorridos |
| AVL | `ArbolAVL` | Búsqueda optimizada con balanceo automático |
| Arreglos/listas auxiliares | Varias clases | Estadísticas y reportes |

## 4. Metodología de prueba

Para evaluar el rendimiento se recomienda realizar pruebas con una biblioteca grande de canciones.

### 4.1 Datos de entrada

| Elemento | Valor |
|---|---|
| Cantidad mínima de canciones | 2,000 canciones |
| Tipo de archivo | MP3 |
| Fuente | Carpeta local con subcarpetas |
| Criterios de búsqueda | Nombre, artista o álbum |
| Medición | Nanosegundos y milisegundos |

### 4.2 Procedimiento

1. Seleccionar una carpeta con al menos 2,000 canciones.
2. Cargar las canciones en SmartPlayer.
3. Insertar las canciones en la biblioteca, ABB y AVL.
4. Registrar tiempo de carga para ABB y AVL.
5. Realizar búsquedas de canciones existentes.
6. Realizar búsquedas de canciones no existentes.
7. Comparar tiempos obtenidos.
8. Analizar la diferencia entre ABB y AVL.

## 5. Tabla de resultados de carga

Completar esta tabla con los resultados obtenidos en la computadora usada para la defensa.

| Prueba | Cantidad de canciones | Tiempo ABB | Tiempo AVL | Observación |
|---|---:|---:|---:|---|
| Carga 1 | 2,000 | Pendiente | Pendiente | Primera medición |
| Carga 2 | 2,000 | Pendiente | Pendiente | Segunda medición |
| Carga 3 | 2,000 | Pendiente | Pendiente | Tercera medición |
| Promedio | 2,000 | Pendiente | Pendiente | Promedio final |

## 6. Tabla de resultados de búsqueda

Completar esta tabla con búsquedas reales.

| Prueba | Criterio buscado | Resultado esperado | Tiempo ABB | Tiempo AVL | Más eficiente |
|---|---|---|---:|---:|---|
| 1 | Canción existente | Encontrada | Pendiente | Pendiente | Pendiente |
| 2 | Canción existente | Encontrada | Pendiente | Pendiente | Pendiente |
| 3 | Canción existente | Encontrada | Pendiente | Pendiente | Pendiente |
| 4 | Canción no existente | No encontrada | Pendiente | Pendiente | Pendiente |
| 5 | Búsqueda parcial | Coincidencias | Pendiente | Pendiente | Pendiente |
| Promedio | Varias búsquedas | Varios resultados | Pendiente | Pendiente | Pendiente |

## 7. Análisis del Árbol Binario de Búsqueda

El Árbol Binario de Búsqueda organiza las canciones de acuerdo con un criterio de ordenamiento, principalmente el nombre de la canción. Su ventaja es que permite búsquedas más ordenadas que una lista lineal.

Sin embargo, su rendimiento depende del orden de inserción. Si las canciones se insertan en un orden que produce desbalance, el árbol puede crecer más hacia un lado y su comportamiento puede acercarse a una lista.

### Complejidad del ABB

| Operación | Caso promedio | Peor caso |
|---|---:|---:|
| Inserción | O(log n) | O(n) |
| Búsqueda | O(log n) | O(n) |
| Eliminación | O(log n) | O(n) |
| Recorrido | O(n) | O(n) |

### Ventajas

- Implementación más directa.
- Permite recorridos InOrden, PreOrden y PostOrden.
- Puede ser eficiente si los datos quedan distribuidos.
- Es útil para explicar ordenamiento jerárquico.

### Desventajas

- Puede desbalancearse.
- En el peor caso se comporta como una lista.
- No garantiza tiempos logarítmicos.

## 8. Análisis del Árbol AVL

El Árbol AVL es un árbol binario de búsqueda balanceado. Después de insertar elementos, calcula la altura de los nodos y aplica rotaciones cuando detecta desbalance.

Su principal ventaja es que mantiene una altura controlada, por lo que la búsqueda se conserva cercana a O(log n).

### Complejidad del AVL

| Operación | Complejidad |
|---|---:|
| Inserción | O(log n) |
| Búsqueda | O(log n) |
| Eliminación | O(log n), si se reequilibra |
| Rotación | O(1) |
| Recorrido | O(n) |

### Ventajas

- Mantiene el árbol balanceado.
- Garantiza mejor rendimiento en búsquedas grandes.
- Evita que el árbol se degrade como una lista.
- Es adecuado como índice optimizado de canciones.

### Desventajas

- Implementación más compleja que ABB.
- La inserción tiene costo adicional por cálculo de altura y rotaciones.
- Puede consumir más procesamiento durante la carga.

## 9. Comparación ABB vs AVL

| Aspecto | ABB | AVL |
|---|---|---|
| Balanceo automático | No | Sí |
| Facilidad de implementación | Más simple | Más complejo |
| Búsqueda promedio | Buena | Muy buena |
| Peor caso | O(n) | O(log n) |
| Costo de inserción | Menor | Mayor por balanceo |
| Uso recomendado | Datos pequeños o moderadamente distribuidos | Datos grandes y búsquedas frecuentes |

## 10. Análisis de listas

La lista doble permite recorrer canciones hacia adelante y hacia atrás, lo cual se relaciona directamente con los botones de siguiente y anterior. En el proyecto se utiliza para biblioteca y playlists porque permite mayor flexibilidad que una lista simple.

### Complejidad

| Operación | Complejidad |
|---|---:|
| Avanzar | O(1) |
| Retroceder | O(1) |
| Insertar al final | O(1) si existe referencia al último nodo |
| Buscar canción | O(n) |
| Eliminar canción buscada | O(n) |

## 11. Análisis de pila

La pila se adapta al historial porque la última canción reproducida debe ser la primera en mostrarse. Esto representa un comportamiento LIFO.

| Operación | Complejidad |
|---|---:|
| Agregar al historial | O(1) |
| Consultar último elemento | O(1) |
| Recorrer historial | O(n) |

## 12. Análisis de cola

La cola representa la cola de reproducción automática. La primera canción agregada debe reproducirse antes que las siguientes, aplicando comportamiento FIFO.

| Operación | Complejidad |
|---|---:|
| Encolar canción | O(1) |
| Desencolar canción | O(1) |
| Consultar siguiente | O(1) |
| Recorrer cola | O(n) |

## 13. Análisis de estadísticas

Las estadísticas se generan recorriendo las canciones, playlists e historial. En general, estas operaciones requieren revisar conjuntos completos de datos, por lo que su complejidad suele ser O(n).

| Estadística | Estructura consultada | Complejidad aproximada |
|---|---|---:|
| Canción más reproducida | Biblioteca / historial | O(n) |
| Artista más escuchado | Biblioteca / historial | O(n) |
| Género más frecuente | Biblioteca | O(n) |
| Playlist más grande | Lista de playlists | O(p) |
| Promedio de duración | Biblioteca | O(n) |
| Tamaño total | Biblioteca | O(n) |
| Duplicados | Biblioteca / estructura auxiliar | O(n) a O(n²), según implementación |

## 14. Resultados esperados

En una carga grande, el ABB puede ser rápido al insertar porque no realiza balanceo. Sin embargo, el AVL debería mantener mejor rendimiento al buscar, especialmente cuando la cantidad de canciones aumenta.

La diferencia puede variar dependiendo de:

- Orden de los nombres de canciones.
- Cantidad de canciones cargadas.
- Velocidad del disco.
- Metadatos disponibles.
- Procesos activos de la computadora.
- Cantidad de coincidencias encontradas.

## 15. Conclusiones

1. El ABB es útil para representar búsqueda jerárquica y recorridos de árboles, pero su rendimiento puede degradarse si queda desbalanceado.
2. El AVL requiere más lógica interna, pero ofrece búsquedas más estables gracias al balanceo automático.
3. La lista doble es adecuada para navegación musical porque permite avanzar y retroceder en O(1).
4. La lista circular resuelve de forma natural la reproducción infinita.
5. La pila representa correctamente el historial de reproducción porque prioriza la canción más reciente.
6. La cola representa correctamente la cola de reproducción porque respeta el orden de llegada.
7. Las estadísticas musicales requieren recorridos completos, por lo que su costo depende de la cantidad de canciones y playlists.
8. Para bibliotecas grandes, AVL es una mejor opción como índice de búsqueda principal, mientras que ABB es útil para comparación académica y visualización.

## 16. Recomendación final

Para la defensa se recomienda llevar capturas o resultados reales con al menos 2,000 canciones cargadas. Este informe debe completarse con los tiempos obtenidos en la computadora usada para presentar el proyecto.
