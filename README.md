# AwesomeViewSpotFinder
AwesomeViewSpotFinder finds and prints out the N-highest ViewSpots from a given landscape mesh in 
JSON format. A ViewSpot element is defined as a local maximum, i.e. the neighboring elements must 
not be higher than the ViewSpot element.  Neighbors are elements that share at least one node 
with the ViewSpot element.

## Usage
`java -jar AwesomeViewSpotsFinder.jar <pathTo/meshFile.json> <numberOfViewSpots>`

`<pathTo/meshFile.json>`: relative or absolute path to a valid mesh file in JSON format

`<numberOfViewSpots>`: integer number greater than 0

## How to build
1. Get IntelliJ IDEA for your OS [here](https://www.jetbrains.com/idea/download/#section=linux) 
2. Open IDEA: 'Menu' -> 'File' -> 'New' -> 'Project from Version Control...'
3. Select Git as Version Control and paste in the URL https://github.com/mnemllr/AwesomeViewSpotFinder
4. Build in IDE (green hammer symbol) or use command line (in project directory) `./gradlew assemble`
5. Find Output JAR file under /build/libs

## How to test
1. Execute TestRuns by right click on src/test -> 'Run Tests in AwesomeViewSpotFinder' or `./gradlew test`
      1. Execute TestRuns of specific classes by right click on \<TestClass\> -> 'Run \<TestClass\>' or 
   `./gradlew test --tests <TestClass>`
2. Find test results under build/test-results

## How to add unit test
1. Right click in the src/test/kotlin directory: 'New' -> 'Kotlin Class/File'
2. Use existing test classes as template
