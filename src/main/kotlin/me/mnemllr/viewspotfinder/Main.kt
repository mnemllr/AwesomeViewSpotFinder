import com.google.gson.GsonBuilder
import me.mnemllr.viewspotfinder.ArgumentsProvider
import me.mnemllr.viewspotfinder.ViewSpotsFinder
import me.mnemllr.viewspotfinder.json.JsonMeshParser
import me.mnemllr.viewspotfinder.util.StopWatch

fun main(args: Array<String>) {
    val stopWatch = StopWatch()
    val argsProvider = ArgumentsProvider(args)
    val meshJson = JsonMeshParser().parse(argsProvider.meshJsonFilePath)
    val xHighestViewSpots = ViewSpotsFinder(meshJson).getHighestViewSpots(argsProvider.xHighestViewSpots)
    println(GsonBuilder().setPrettyPrinting().create().toJson(xHighestViewSpots))
    println("${xHighestViewSpots.size} highest ViewSpots in ${meshJson.elements.size} elements: ${stopWatch.elapsedTimeMs()} ms")
}
