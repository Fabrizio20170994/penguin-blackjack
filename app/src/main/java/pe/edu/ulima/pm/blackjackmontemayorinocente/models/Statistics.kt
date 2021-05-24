package pe.edu.ulima.pm.blackjackmontemayorinocente.models

object Statistics {
    private var wins = 0
    private var loses = 0

    var onResultRecorded: ((Int, Int, String) -> Unit)? = null

    fun recordResult(win: Boolean) {
        if (win) {
            wins += 1
        } else {
            loses += 1
        }
        if (onResultRecorded != null) onResultRecorded!!(wins, loses, getEfectiveness())
    }

    private fun getEfectiveness(): String {
        return "${100*wins/(loses + wins)}%"
    }
}