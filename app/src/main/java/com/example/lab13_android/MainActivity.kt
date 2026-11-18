package com.example.lab13_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private enum class ContentDemoState {
    Loading,
    Content,
    Error,
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF4F7FB),
                ) {
                    VisibilityAnimationExercise()
                }
            }
        }
    }
}

@Composable
private fun VisibilityAnimationExercise() {
    var isBoxVisible by rememberSaveable { mutableStateOf(false) }
    var isGreen by rememberSaveable { mutableStateOf(false) }
    var isLargeAndShifted by rememberSaveable { mutableStateOf(false) }
    var contentDemoState by rememberSaveable { mutableStateOf(ContentDemoState.Loading) }
    val animatedBoxColor by animateColorAsState(
        targetValue = if (isGreen) Color(0xFF2E9D63) else Color(0xFF5267D5),
        animationSpec = tween(durationMillis = 900),
        label = "animatedBoxColor",
    )
    val animatedSize by animateDpAsState(
        targetValue = if (isLargeAndShifted) 152.dp else 96.dp,
        animationSpec = tween(durationMillis = 700),
        label = "animatedBoxSize",
    )
    val animatedOffset by animateDpAsState(
        targetValue = if (isLargeAndShifted) 96.dp else 0.dp,
        animationSpec = tween(durationMillis = 700),
        label = "animatedBoxOffset",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Text(
            text = "Animación de visibilidad",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF172B4D),
            textAlign = TextAlign.Center,
        )
        Text(
            text = "Pulsa el botón para mostrar u ocultar el cuadro con una transición suave.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF52627A),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = { isBoxVisible = !isBoxVisible },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(if (isBoxVisible) "Ocultar cuadro" else "Mostrar cuadro")
        }

        AnimatedVisibility(
            visible = isBoxVisible,
            modifier = Modifier.fillMaxWidth(),
            enter = fadeIn(animationSpec = tween(durationMillis = 600)) +
                expandVertically(
                    expandFrom = Alignment.Top,
                    animationSpec = tween(durationMillis = 600),
                ),
            exit = fadeOut(animationSpec = tween(durationMillis = 400)) +
                shrinkVertically(
                    shrinkTowards = Alignment.Top,
                    animationSpec = tween(durationMillis = 400),
                ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF5267D5)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "¡Hola, DANTE-GONZALES!\nEl cuadro apareció con AnimatedVisibility",
                    modifier = Modifier.padding(24.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Text(
            text = "Cambio de color con animateColorAsState",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF172B4D),
            textAlign = TextAlign.Center,
        )
        Text(
            text = "Alterna entre azul y verde. La transición tween dura 900 ms.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF52627A),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = { isGreen = !isGreen },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(if (isGreen) "Cambiar a azul" else "Cambiar a verde")
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(animatedBoxColor),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = if (isGreen) "Verde" else "Azul",
                modifier = Modifier.padding(24.dp),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
        }

        Text(
            text = "Animación de tamaño y posición",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF172B4D),
            textAlign = TextAlign.Center,
        )
        Text(
            text = "animateDpAsState anima el tamaño y el desplazamiento durante 700 ms.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF52627A),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = { isLargeAndShifted = !isLargeAndShifted },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(if (isLargeAndShifted) "Restablecer cuadro" else "Mover y ampliar cuadro")
        }
        Text(
            text = "Orden aplicado: size() y luego offset(). Invierte ambos modificadores en el código para comparar la medición y la colocación.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF52627A),
            textAlign = TextAlign.Center,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Box(
                modifier = Modifier
                    .size(animatedSize)
                    .offset(x = animatedOffset)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFFE28A35)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = if (isLargeAndShifted) "Grande" else "Pequeño",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Text(
            text = "Cambio de contenido con AnimatedContent",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF172B4D),
            textAlign = TextAlign.Center,
        )
        Text(
            text = "Selecciona un estado para animar la transición entre mensajes.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF52627A),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = { contentDemoState = ContentDemoState.Loading },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Mostrar estado: Cargando")
        }
        Button(
            onClick = { contentDemoState = ContentDemoState.Content },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Mostrar estado: Contenido")
        }
        Button(
            onClick = { contentDemoState = ContentDemoState.Error },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Mostrar estado: Error")
        }
        AnimatedContent(
            targetState = contentDemoState,
            transitionSpec = {
                fadeIn(animationSpec = tween(durationMillis = 600)) togetherWith
                    fadeOut(animationSpec = tween(durationMillis = 350))
            },
            label = "contentStateTransition",
        ) { targetState ->
            val (message, backgroundColor) = when (targetState) {
                ContentDemoState.Loading -> "Cargando datos…" to Color(0xFF5267D5)
                ContentDemoState.Content -> "Contenido cargado correctamente." to Color(0xFF2E9D63)
                ContentDemoState.Error -> "Ocurrió un error al cargar el contenido." to Color(0xFFC44343)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(backgroundColor),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = message,
                    modifier = Modifier.padding(24.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            }
        }

        NebulaDashGame()
    }
}

@Composable
private fun NebulaDashGame() {
    var isGameRunning by rememberSaveable { mutableStateOf(false) }
    var score by rememberSaveable { mutableStateOf(0) }
    var secondsRemaining by rememberSaveable { mutableStateOf(20) }
    var targetIndex by rememberSaveable { mutableStateOf(0) }

    LaunchedEffect(isGameRunning) {
        if (isGameRunning) {
            while (secondsRemaining > 0) {
                delay(1_000)
                secondsRemaining = (secondsRemaining - 1).coerceAtLeast(0)
            }
            isGameRunning = false
        }
    }

    val scoreBadgeColor by animateColorAsState(
        targetValue = when {
            score >= 50 -> Color(0xFF19B99A)
            score >= 20 -> Color(0xFF7C61E8)
            else -> Color(0xFF4053A8)
        },
        animationSpec = tween(durationMillis = 350),
        label = "scoreBadgeColor",
    )

    Text(
        text = "NEBULA DASH · PROTOTIPO",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Black,
        color = Color(0xFF172B4D),
        textAlign = TextAlign.Center,
    )
    Text(
        text = "Atrapa el núcleo antes de que se cierre el portal.",
        style = MaterialTheme.typography.bodyLarge,
        color = Color(0xFF52627A),
        textAlign = TextAlign.Center,
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        GameHudCard(
            label = "PUNTOS",
            modifier = Modifier.weight(1f),
            backgroundColor = scoreBadgeColor,
        ) {
            AnimatedContent(
                targetState = score,
                transitionSpec = {
                    fadeIn(tween(180)) togetherWith fadeOut(tween(120))
                },
                label = "scoreCounter",
            ) { displayedScore ->
                Text(
                    text = displayedScore.toString(),
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        GameHudCard(
            label = "TIEMPO",
            modifier = Modifier.weight(1f),
            backgroundColor = Color(0xFF26345E),
        ) {
            AnimatedContent(
                targetState = secondsRemaining,
                transitionSpec = {
                    fadeIn(tween(180)) togetherWith fadeOut(tween(120))
                },
                label = "countdown",
            ) { displayedSeconds ->
                Text(
                    text = "${displayedSeconds}s",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF111A3A), Color(0xFF402B78), Color(0xFF1B526F)),
                ),
            ),
    ) {
        val xFraction = (targetIndex % 4) / 3f
        val yFraction = ((targetIndex / 4) % 3) / 2f
        val targetX by animateDpAsState(
            targetValue = (maxWidth - 76.dp) * xFraction,
            animationSpec = tween(durationMillis = 420),
            label = "targetX",
        )
        val targetY by animateDpAsState(
            targetValue = 66.dp + (maxHeight - 144.dp) * yFraction,
            animationSpec = tween(durationMillis = 420),
            label = "targetY",
        )
        val targetColor by animateColorAsState(
            targetValue = when (targetIndex % 3) {
                0 -> Color(0xFF63E6FF)
                1 -> Color(0xFFFFD166)
                else -> Color(0xFFFF70C5)
            },
            animationSpec = tween(durationMillis = 300),
            label = "coreColor",
        )
        val targetScale by animateFloatAsState(
            targetValue = if (targetIndex % 2 == 0) 1f else 0.82f,
            animationSpec = tween(durationMillis = 300),
            label = "coreScale",
        )

        Text(
            text = if (isGameRunning) "TOCA EL NÚCLEO" else "PORTAL DE NEBULOSA",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 18.dp),
            color = Color.White.copy(alpha = 0.82f),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
        )

        AnimatedVisibility(
            visible = isGameRunning,
            modifier = Modifier.offset(x = targetX, y = targetY),
            enter = fadeIn(tween(300)) + scaleIn(initialScale = 0.45f, animationSpec = tween(300)),
            exit = fadeOut(tween(200)) + scaleOut(targetScale = 0.45f, animationSpec = tween(200)),
        ) {
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .graphicsLayer {
                        scaleX = targetScale
                        scaleY = targetScale
                    }
                    .clip(CircleShape)
                    .background(targetColor)
                    .clickable {
                        if (isGameRunning) {
                            score += 10
                            targetIndex = (targetIndex + 1) % 12
                        }
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "✦",
                    color = Color(0xFF111A3A),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Black,
                )
            }
        }

        AnimatedContent(
            targetState = isGameRunning to (secondsRemaining == 0),
            modifier = Modifier.align(Alignment.BottomCenter),
            transitionSpec = {
                fadeIn(tween(250)) togetherWith fadeOut(tween(150))
            },
            label = "gamePrompt",
        ) { (running, finished) ->
            Text(
                text = when {
                    running -> "¡Rápido! Cada núcleo vale 10 puntos"
                    finished -> "Ronda terminada · Puntuación: $score"
                    else -> "Pulsa iniciar para comenzar la ronda"
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp),
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
            )
        }
    }

    Button(
        onClick = {
            if (!isGameRunning) {
                score = 0
                secondsRemaining = 20
                targetIndex = 0
                isGameRunning = true
            }
        },
        modifier = Modifier.fillMaxWidth(),
        enabled = !isGameRunning,
    ) {
        Text(if (isGameRunning) "Partida en curso…" else "Iniciar partida")
    }
    Text(
        text = "Animaciones combinadas: aparición y escala del núcleo, desplazamiento y tamaño, cambio de color, contador y marcador animados.",
        style = MaterialTheme.typography.bodySmall,
        color = Color(0xFF52627A),
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun GameHudCard(
    label: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.75f),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp,
        )
        content()
    }
}
