package me.mnemllr.viewspotfinder.util

class StopWatch {
    private val start = System.currentTimeMillis()

    fun elapsedTimeMs() = System.currentTimeMillis() - start
}
