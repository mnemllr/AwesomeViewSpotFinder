package me.mnemllr.viewspotfinder.json

import com.google.gson.Gson
import me.mnemllr.viewspotfinder.exceptions.InvalidJsonMeshException
import java.nio.file.Files
import java.nio.file.Path

class JsonMeshParser {
    fun parse(path: Path): Mesh {
        val fileString = String(Files.readAllBytes(path))
        val jsonMesh = Gson().fromJson(fileString, Mesh::class.java).also {
            if (it.elements.isEmpty() || it.values.isEmpty() || it.nodes.isEmpty()) {
                throw InvalidJsonMeshException("JSON values \"elements\", \"values\" and \"nodes\" must contain at least one entry")
            }
/*            if (it.elements.size != it.values.size || it.elements.size != it.nodes.size) {
                throw InvalidJsonMeshException("JSON values \"elements\", \"values\" and \"nodes\" must contain same amount of entries")
            }*/
        }
        return jsonMesh
    }
}
