package br.com.fiap.audioplayer

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.audioplayer.ui.theme.AudioPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    AudioPlayer(context = this)
                }
            }
        }
    }
}

@Composable
fun AudioPlayer(context: Context) {

    var player = remember {
        mutableStateOf(MediaPlayer.create(context, R.raw.jjk))
    }

    Box(contentAlignment = Alignment.Center) {
        Row() {

            IconButton(onClick = {
                if (player.value == null) {
                    player.value = MediaPlayer.create(context, R.raw.jjk)
                }
                player.value.start()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_play_arrow_24),
                    contentDescription = "Botão de play"
                )
            }

            IconButton(onClick = {
                player.value.pause()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_pause_24),
                    contentDescription = "Botão de pause"
                )
            }

            IconButton(onClick = {
                player.value.stop()
                player.value.reset()
                player.value.release()
                player.value = null
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_stop_24),
                    contentDescription = "Botão de stop"
                )
            }

        }
    }
}