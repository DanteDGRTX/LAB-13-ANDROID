package com.example.lab13_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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

    Column(
        modifier = Modifier
            .fillMaxSize()
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
    }
}
