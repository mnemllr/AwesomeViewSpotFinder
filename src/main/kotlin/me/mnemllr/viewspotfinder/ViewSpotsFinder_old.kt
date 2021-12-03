package me.mnemllr.viewspotfinder

import me.mnemllr.viewspotfinder.json.Element
import me.mnemllr.viewspotfinder.json.Mesh
import me.mnemllr.viewspotfinder.json.Value

class ViewSpotsFinder_old {

    fun getHighestViewSpots(xHighestSpots: Int, mesh: Mesh): ArrayList<Value> {
        val highestViewSpots = arrayListOf<Value>()
        val descHeights = mesh.values.apply { sortByDescending { it.value } }
        val elementMap = HashMap<Int, Element>(mesh.elements.size)
        for (element in mesh.elements) {
            elementMap[element.id] = element
        }
        highestViewSpots.add(descHeights[0]) // xHighestSpots cannot be 0 and mesh contains at least 1 entry
        outer@ for (i in 1 until descHeights.size) {
            if (highestViewSpots.size == xHighestSpots) break@outer
            for (j in 0 until i) { // check if previous indices (higher heights) are neighbouring
                if (elementMap[descHeights[i].element_id]?.isNeighbour(elementMap[descHeights[j].element_id]) == true) {
                    continue@outer
                }
            }
            highestViewSpots.add(descHeights[i])
        }
        return highestViewSpots
    }
}