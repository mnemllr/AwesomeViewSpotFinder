import com.google.gson.GsonBuilder
import me.mnemllr.viewspotfinder.ArgumentsProvider
import me.mnemllr.viewspotfinder.ViewSpotsFinder
import me.mnemllr.viewspotfinder.json.JsonMeshParser

fun main(args: Array<String>) {
    val startTimeMs = System.currentTimeMillis()
    val argsProvider = ArgumentsProvider(args)
    val meshJson = JsonMeshParser().parse(argsProvider.meshJsonFilePath)
    val xHighestViewSpots = ViewSpotsFinder().getHighestViewSpots(argsProvider.xHighestViewSpots, meshJson)
    println(GsonBuilder().setPrettyPrinting().create().toJson(xHighestViewSpots))
    val elapsedTimeMs = System.currentTimeMillis() - startTimeMs
    println("${xHighestViewSpots.size} highest ViewSpots in ${meshJson.elements.size} elements: $elapsedTimeMs ms")
}
