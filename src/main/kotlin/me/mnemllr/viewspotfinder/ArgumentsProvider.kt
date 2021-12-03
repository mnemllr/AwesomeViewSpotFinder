package me.mnemllr.viewspotfinder

import me.mnemllr.viewspotfinder.exceptions.InvalidArgumentException
import java.nio.file.Path
import java.nio.file.Paths

class ArgumentsProvider(args: Array<String>) {
    val meshJsonFilePath: Path
    val xHighestViewSpots: Int

    init {
        if (args.size != 2) throw InvalidArgumentException("Invalid number of arguments")
        try {
            meshJsonFilePath = Paths.get(args[0])
        } catch (e: Exception) {
            throw InvalidArgumentException("Invalid path to JSON mesh file")
        }
        val passedViewPointNumber = args[1].toIntOrNull()
        if (passedViewPointNumber == null || passedViewPointNumber <= 0) {
            throw InvalidArgumentException("\"${args[1]}\" is an invalid view spot number")
        }
        xHighestViewSpots = passedViewPointNumber
    }
}
