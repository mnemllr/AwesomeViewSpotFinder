/*
package me.mnemllr.viewspotfinder

import com.google.gson.Gson
import main
import me.mnemllr.viewspotfinder.json.Element
import me.mnemllr.viewspotfinder.json.Mesh
import me.mnemllr.viewspotfinder.json.Node
import me.mnemllr.viewspotfinder.json.Value
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream


class MainTest {

    // some test elements, values, nodes
    val element0 = Element(0, arrayOf(0, 1, 2))
    val value0 = Value(element0.id, 10.0)
    val node0 = Node(0, 0, 0)
    val node1 = Node(1, 1, 1)
    val node2 = Node(2, 0, 1)
    val node3 = Node(3, 1, 0)
    val node4 = Node(4, 0, 2)
    val element1 = Element(1, arrayOf(3, 4, 2))
    val value1 = Value(element1.id, 8.0)

    private val outputCapture = ByteArrayOutputStream()

    @Before
    fun setup() {
        System.setOut(PrintStream(outputCapture))
        // File("JsonTestFile.txt").writeText(history.entries.joinToString("\n")
    }

    @After
    fun cleanUp() {
        System.setOut(System.out)
    }

    @Test
    fun `should return highest value if only one viewpoint is requested`() {
        val testMesh = Mesh(arrayOf(element0), arrayOf(node0, node1, node2), arrayOf(value0))
        val testMeshFile = File("testMesh.json").writeText(Gson().toJson(testMesh))
        main(arrayOf("./testMesh.json", "1"))
        Assert.assertEquals(Gson().toJson(arrayOf(value0)), outputCapture.toString().trim())
    }
}*/
