# LAB 13 Android — Animaciones con Jetpack Compose

Proyecto de Android Studio con Jetpack Compose. Incluye los ejercicios de animación, un prototipo de videojuego y una sección práctica basada en el [codelab oficial de animaciones](https://developer.android.com/codelabs/jetpack-compose-animation?hl=es-419):

- `AnimatedVisibility` muestra y oculta un cuadro con `fadeIn`, `fadeOut`, `expandVertically` y `shrinkVertically`.
- `animateColorAsState` anima el cambio de color entre azul y verde con una especificación `tween`.
- `animateDpAsState` anima el tamaño y el desplazamiento de un cuadro; se usa `Modifier.size` seguido de `Modifier.offset` para explorar el orden de los modificadores.
- `AnimatedContent` alterna entre los estados Cargando, Contenido y Error con transiciones `fadeIn` y `fadeOut` de duraciones distintas.
- **Nebula Dash** combina animaciones de posición, escala, visibilidad y color en una ronda de reflejos de 20 segundos, con marcador y cuenta regresiva animados.
- La práctica extra cubre `animateContentSize`, `updateTransition`, `rememberInfiniteTransition` y un gesto horizontal implementado con `Animatable`.
