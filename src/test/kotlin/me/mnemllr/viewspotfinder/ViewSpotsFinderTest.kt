package me.mnemllr.viewspotfinder

import me.mnemllr.viewspotfinder.json.Element
import me.mnemllr.viewspotfinder.json.Mesh
import me.mnemllr.viewspotfinder.json.Node
import me.mnemllr.viewspotfinder.json.Value
import org.junit.Assert
import org.junit.Test

class ViewSpotsFinderTest {

    private val node0 = Node(0, 0f, 0f)
    private val node1 = Node(1, 1f, 0f)
    private val node2 = Node(2, 0f, 1f)
    private val node3 = Node(3, 0f, 2f)
    private val node4 = Node(4, 2f, 0f)
    private val node5 = Node(5, 2f, 1f)
    private val node6 = Node(6, 3f, 0f)

    private val element0 = Element(0, arrayOf(0, 1, 2))
    private val element1 = Element(1, arrayOf(1, 2, 3))
    private val element2 = Element(2, arrayOf(1, 4, 5))
    private val element3 = Element(3, arrayOf(4, 5, 6))
    private val value0 = Value(0, 10.0)
    private val value1 = Value(1, 8.0)
    private val value2 = Value(2, 7.0)
    private val value3 = Value(3, 11.0)

    @Test
    fun `getHighestViewSpots should return no viewspots if invalid xHighestSpots number is passed`() {
        val testMesh = Mesh(arrayOf(element0), arrayOf(node0, node1, node2), arrayOf(value0))
        val viewSpotsFinder = ViewSpotsFinder(testMesh)
        var viewSpots = viewSpotsFinder.getHighestViewSpots(0)
        Assert.assertTrue(viewSpots.isEmpty())

        viewSpots = viewSpotsFinder.getHighestViewSpots(-1)
        Assert.assertTrue(viewSpots.isEmpty())
    }

    @Test
    fun `getHighestViewSpots should return not return more viewspots than available`() {
        var testMesh = Mesh(arrayOf(element0), arrayOf(node0, node1, node2), arrayOf(value0))
        var viewSpotsFinder = ViewSpotsFinder(testMesh)
        var viewSpots = viewSpotsFinder.getHighestViewSpots(2)
        Assert.assertEquals(1, viewSpots.size)

        testMesh = Mesh(arrayOf(element0, element0), arrayOf(node0, node1, node2, node3), arrayOf(value0, value1))
        viewSpotsFinder = ViewSpotsFinder(testMesh)
        viewSpots = viewSpotsFinder.getHighestViewSpots(2)
        Assert.assertEquals(1, viewSpots.size)
    }

    @Test
    fun `getHighestViewSpots should return no viewpoints if emtpy mesh is passed`() {
        val testMesh = Mesh(arrayOf(), arrayOf(), arrayOf())
        val viewSpotsFinder = ViewSpotsFinder(testMesh)
        val viewSpots = viewSpotsFinder.getHighestViewSpots(1)
        Assert.assertTrue(viewSpots.isEmpty())
    }

    @Test
    fun `getHighestViewSpots should return no viewpoints if invalid mesh is passed`() {
        // only elements, no nodes, no values
        var testMesh = Mesh(arrayOf(element0), arrayOf(), arrayOf())
        var viewSpotsFinder = ViewSpotsFinder(testMesh)
        var viewSpots = viewSpotsFinder.getHighestViewSpots(1)
        Assert.assertTrue(viewSpots.isEmpty())

        // only value, no elements, no nodes
        testMesh = Mesh(arrayOf(), arrayOf(), arrayOf(value0))
        viewSpotsFinder = ViewSpotsFinder(testMesh)
        viewSpots = viewSpotsFinder.getHighestViewSpots(1)
        Assert.assertTrue(viewSpots.isEmpty())

        // only nodes, no elements, no values
        testMesh = Mesh(arrayOf(), arrayOf(node0, node1, node2), arrayOf())
        viewSpotsFinder = ViewSpotsFinder(testMesh)
        viewSpots = viewSpotsFinder.getHighestViewSpots(1)
        Assert.assertTrue(viewSpots.isEmpty())

        // no values
        testMesh = Mesh(arrayOf(element0), arrayOf(node0, node1, node2), arrayOf())
        viewSpotsFinder = ViewSpotsFinder(testMesh)
        viewSpots = viewSpotsFinder.getHighestViewSpots(1)
        Assert.assertTrue(viewSpots.isEmpty())
    }

    @Test
    fun `getHighestViewSpots should return only one viewpoint if all have same value`() {
        val sameValue = Value(1, value0.value)
        val testMesh = Mesh(arrayOf(element0, element1), arrayOf(node0, node1, node2, node3), arrayOf(value0, sameValue))
        val viewSpotsFinder = ViewSpotsFinder(testMesh)
        val viewSpots = viewSpotsFinder.getHighestViewSpots(1)
        Assert.assertEquals(1, viewSpots.size)
    }

    @Test
    fun `getHighestViewSpots should return correct amount of viewspots`() {
        val testMesh = Mesh(arrayOf(element0, element1, element2, element3),
            arrayOf(node0, node1, node2, node3, node4, node5, node6),
            arrayOf(value0, value1, value2, value3))
        val viewSpotsFinder = ViewSpotsFinder(testMesh)
        var viewSpots = viewSpotsFinder.getHighestViewSpots(4)
        Assert.assertEquals("Mesh contains only 2 local maxima", 2, viewSpots.size)

        viewSpots = viewSpotsFinder.getHighestViewSpots(1)
        Assert.assertEquals("Mesh contains only 2 local maxima", 1, viewSpots.size)
    }

    @Test
    fun `getHighestViewSpots should return correct view spots with 1 dimensional mesh`() {
        val node1d0 = Node(0, 0f, 0f)
        val node1d1 = Node(1, 1f, 0f)
        val node1d2 = Node(2, 2f, 0f)
        val node1d3 = Node(3, 3f, 0f)
        val node1d4 = Node(4, 4f, 0f)
        val element1d0 = Element(0, arrayOf(0, 1))
        val element1d1 = Element(1, arrayOf(1, 2))
        val element1d2 = Element(2, arrayOf(2, 3))
        val element1d3 = Element(3, arrayOf(3, 4))
        val value1d0 = Value(0, 1.0)
        val value1d1 = Value(1, 2.0)
        val value1d2 = Value(2, 3.0)
        val value1d3 = Value(3, 1.0)

        val testMesh = Mesh(arrayOf(element1d0, element1d1, element1d2, element1d3),
            arrayOf(node1d0, node1d1, node1d2, node1d3, node1d4),
            arrayOf(value1d0, value1d1, value1d2, value1d3))
        val viewSpotsFinder = ViewSpotsFinder(testMesh)
        var viewSpots = viewSpotsFinder.getHighestViewSpots(4)
        Assert.assertEquals(1, viewSpots.size)
    }

}
