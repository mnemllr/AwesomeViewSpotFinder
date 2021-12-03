package me.mnemllr.viewspotfinder.exceptions

class InvalidArgumentException(message: String = "") :
    Exception("$message\nUsage: java -jar AwesomeViewSpotFinder.jar <path_to_mesh_file> <number_of_view_spots>")
