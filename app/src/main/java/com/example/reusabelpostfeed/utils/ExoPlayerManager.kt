package com.example.reusabelpostfeed.utils

import androidx.media3.exoplayer.ExoPlayer

object ExoPlayerManager {

    private var currentPlayer: ExoPlayer? = null

    fun play(player: ExoPlayer) {
        if (currentPlayer != player) {
            stopCurrent()
            currentPlayer = player
            player.playWhenReady = true
        }
    }

    fun stopCurrent() {
        currentPlayer?.let { it.playWhenReady = false }
        currentPlayer = null
    }

    fun pause(player: ExoPlayer) {
        if (currentPlayer == player) {
            player.playWhenReady = false
            currentPlayer = null
        }
    }

    fun releaseAll() {
        currentPlayer?.release()
        currentPlayer = null
    }
}