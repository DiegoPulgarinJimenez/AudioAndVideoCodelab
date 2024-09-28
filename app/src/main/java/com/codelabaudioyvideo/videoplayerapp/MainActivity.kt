package com.codelabaudioyvideo.videoplayerapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class MainActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar la vista del reproductor
        playerView = findViewById(R.id.player_view)

        // Crear una instancia de ExoPlayer
        player = ExoPlayer.Builder(this).build()

        // Conectar el reproductor a la vista
        playerView.player = player

        // Definir la URI del video (puedes cambiar la URL por un video local)
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.samplevideo2)

        // val videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")

        // Preparar el reproductor con el archivo de video
        val mediaItem = androidx.media3.common.MediaItem.fromUri(videoUri)
        player.setMediaItem(mediaItem)
        player.prepare()

        // Comenzar la reproducción automáticamente
        player.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()
        player.playWhenReady = false
        player.pause()
    }

    override fun onResume() {
        super.onResume()
        player.playWhenReady = true
        player.play()
    }

    override fun onStop() {
        super.onStop()
        // Liberar el reproductor al detener la actividad
        player.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
