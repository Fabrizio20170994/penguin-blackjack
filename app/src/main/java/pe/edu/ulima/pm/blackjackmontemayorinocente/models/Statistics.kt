package pe.edu.ulima.pm.blackjackmontemayorinocente.models

import java.util.*

object Statistics {
    private var wins = 0f
    private var loses = 0f

    var onResultRecorded: ((Float, Float, String) -> Unit)? = null

    fun recordResult(win: Boolean) {
        if (win) {
            wins += 1
        } else {
            loses += 1
        }
        if (onResultRecorded != null) onResultRecorded!!(wins, loses, getEfectiveness())
    }

    private fun getEfectiveness(): String {
        return "${"%.1f".format(Locale.ENGLISH,100f*wins/(loses + wins)).toFloat()}%"
    }
}