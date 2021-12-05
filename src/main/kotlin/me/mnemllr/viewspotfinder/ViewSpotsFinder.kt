package me.mnemllr.viewspotfinder

import me.mnemllr.viewspotfinder.json.Element
import me.mnemllr.viewspotfinder.json.Mesh
import me.mnemllr.viewspotfinder.json.Value

class ViewSpotsFinder(mesh: Mesh) {

    private val descendingValues = mesh.values.apply { sortByDescending { it.value } }
    private val idToValueMap = HashMap<Int, Value>(mesh.values.size).apply {
        mesh.values.forEach { value -> this[value.element_id] = value }
    }
    private val idToElementMap = HashMap<Int, Element>(mesh.elements.size).apply {
        mesh.elements.forEach { element -> this[element.id] = element }
    }
    private val allValuesOfNodeMap = HashMap<Int, ArrayList<Value>>(mesh.elements.size).apply {
        for (element in mesh.elements) {
            idToValueMap[element.id] ?: continue
            element.nodes.forEach { nodeId ->
                if (this[nodeId]?.add(idToValueMap[element.id]!!) == null) {
                    this[nodeId] = arrayListOf(idToValueMap[element.id]!!)
                }
            }
        }
    }
    
    fun getHighestViewSpots(xHighestSpots: Int): ArrayList<Value> {
        val highestViewSpots = arrayListOf<Value>()
        outer@ for (value in descendingValues) {
            if (highestViewSpots.size == xHighestSpots) break@outer
            if(hasHigherNeighbour(value)) continue@outer
            highestViewSpots.add(value)
        }
        return highestViewSpots
    }

    private fun hasHigherNeighbour(currentValue: Value): Boolean {
        val elementOfValue = idToElementMap[currentValue.element_id] ?: return true
        val nodesOfElement = elementOfValue.nodes
        for (node in nodesOfElement) {
            val allValuesOfNode = allValuesOfNodeMap[node] ?: return true
            for (valueOfNode in allValuesOfNode) {
                if (valueOfNode.value > currentValue.value) {
                    return true
                }
            }
        }
        return false
    }
}