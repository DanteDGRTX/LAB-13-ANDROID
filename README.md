# LAB 13 Android — Animaciones con Jetpack Compose

Proyecto de Android Studio con Jetpack Compose. Incluye cuatro ejercicios de animación y un prototipo de videojuego:

- `AnimatedVisibility` muestra y oculta un cuadro con `fadeIn`, `fadeOut`, `expandVertically` y `shrinkVertically`.
- `animateColorAsState` anima el cambio de color entre azul y verde con una especificación `tween`.
- `animateDpAsState` anima el tamaño y el desplazamiento de un cuadro; se usa `Modifier.size` seguido de `Modifier.offset` para explorar el orden de los modificadores.
- `AnimatedContent` alterna entre los estados Cargando, Contenido y Error con transiciones `fadeIn` y `fadeOut` de duraciones distintas.
- **Nebula Dash** combina animaciones de posición, escala, visibilidad y color en una ronda de reflejos de 20 segundos, con marcador y cuenta regresiva animados.

## Ejecutar

1. Abre esta carpeta en Android Studio y espera la sincronización de Gradle.
2. Ejecuta la configuración `app` en un emulador o dispositivo Android.
3. Usa los botones de cada sección para probar las animaciones y pulsa **Iniciar partida** en Nebula Dash para atrapar los núcleos antes de que termine el tiempo.

Tecnologías: Kotlin, Jetpack Compose, Material 3 y Android Gradle Plugin.
