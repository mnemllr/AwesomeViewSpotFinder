package me.mnemllr.viewspotfinder

import me.mnemllr.viewspotfinder.exceptions.InvalidArgumentException
import org.junit.Assert
import org.junit.Test

class ArgumentsProviderTest {

    @Test
    fun `Should throw exception when not enough arguments are passed`() {
        Assert.assertThrows(InvalidArgumentException::class.java) {
            ArgumentsProvider(arrayOf())
        }
        Assert.assertThrows(InvalidArgumentException::class.java) {
            ArgumentsProvider(arrayOf("somePath"))
        }
    }

    @Test
    fun `Should throw exception when invalid arguments are passed`() {
        Assert.assertThrows(InvalidArgumentException::class.java) {
            ArgumentsProvider(arrayOf("./mesh.json", "NaN"))
        }
        Assert.assertThrows(InvalidArgumentException::class.java) {
            ArgumentsProvider(arrayOf("./mesh.json", "-1"))
        }
    }

    @Test
    fun `Should parse valid arguments correctly`() {
        val pathToJson = "./samples/mesh.json"
        val spotNumber = 2
        val argsProvider = ArgumentsProvider(arrayOf(pathToJson, "$spotNumber"))
        Assert.assertEquals(pathToJson, "${argsProvider.meshJsonFilePath}")
        Assert.assertEquals(spotNumber, argsProvider.xHighestViewSpots)
    }
}