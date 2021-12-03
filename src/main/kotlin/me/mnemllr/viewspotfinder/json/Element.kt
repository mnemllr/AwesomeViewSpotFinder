package me.mnemllr.viewspotfinder.json

data class Element(val id: Int, val nodes: Array<Int>) {
    fun isNeighbour(other: Element?): Boolean {
        other ?: return false
        val neighboringNodes = other.nodes.intersect(this.nodes.toSet())
        if (neighboringNodes.isEmpty()) return false
        return true
    }
}
