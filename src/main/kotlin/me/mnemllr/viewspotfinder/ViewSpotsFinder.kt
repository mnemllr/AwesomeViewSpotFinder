package me.mnemllr.viewspotfinder

import me.mnemllr.viewspotfinder.json.Element
import me.mnemllr.viewspotfinder.json.Mesh
import me.mnemllr.viewspotfinder.json.Value

class ViewSpotsFinder {

    fun getHighestViewSpots(xHighestSpots: Int, mesh: Mesh): ArrayList<Value> {
        val highestViewSpots = arrayListOf<Value>()
        val descendValues = mesh.values.apply { sortByDescending { it.value } }
        val valueMap = HashMap<Int, Value>().apply {
            mesh.values.forEach { value -> this[value.element_id] = value }
        }
        val nodeMap = HashMap<Int, ArrayList<Value>>(mesh.elements.size).apply {
            for (element in mesh.elements) {
                element.nodes.forEach { node->
                    if (this.containsKey(node)) {
                        this[node]?.add(valueMap[element.id]!!)
                        return@forEach
                    }
                    this[node] = arrayListOf(valueMap[element.id]!!)
                }
            }
        }
        val elementMap = HashMap<Int, Element>(mesh.elements.size).apply {
            mesh.elements.forEach { element -> this[element.id] = element }
        }
        outer@ for (value in descendValues) {
            if (highestViewSpots.size == xHighestSpots) break@outer
            val elemNodes = elementMap[value.element_id]?.nodes ?: continue@outer // element id not in map
            for(node in elemNodes) {
                val connectedHeightsOfNode = nodeMap[node] ?: continue@outer // node id not in map
                for (heightOfNode in connectedHeightsOfNode) {
                    if (heightOfNode.value > value.value) continue@outer // neighbour is higher, skip to next
                }
            }
            highestViewSpots.add(value)
        }
        return highestViewSpots
    }
}