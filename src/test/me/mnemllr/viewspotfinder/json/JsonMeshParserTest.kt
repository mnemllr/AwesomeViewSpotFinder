package me.mnemllr.viewspotfinder.json

import org.junit.Assert
import org.junit.Test
import java.nio.file.Paths

class JsonMeshParserTest {

    val testMeshJsonPath = "./samples/testMesh.json"
    val node0 = Node(0, 0f, 0f)
    val node1 = Node(1, 1f, 1f)
    val node2 = Node(2, 0f, 1f)
    val element0 = Element(0, arrayOf(0, 1, 2))
    val value0 = Value(0, 10.0)
    val expectedMesh = Mesh(arrayOf(element0), arrayOf(node0, node1, node2), arrayOf(value0))

    @Test
    fun `should parse mesh json correctly`() {
        val jsonMeshParser = JsonMeshParser()
        val jsonPath = Paths.get(testMeshJsonPath)
        val returnedMesh = jsonMeshParser.parse(jsonPath)
        Assert.assertEquals(expectedMesh.elements.first().id, returnedMesh.elements.first().id)
        Assert.assertTrue(expectedMesh.elements.first().nodes.contentEquals(returnedMesh.elements.first().nodes))
        Assert.assertEquals(expectedMesh.nodes.size, returnedMesh.nodes.size)
        Assert.assertEquals(expectedMesh.nodes.first(), returnedMesh.nodes.first())
        Assert.assertTrue(expectedMesh.values.contentEquals(returnedMesh.values))
    }
}