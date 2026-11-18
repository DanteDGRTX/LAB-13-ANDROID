package com.example.lab13_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    }
}
