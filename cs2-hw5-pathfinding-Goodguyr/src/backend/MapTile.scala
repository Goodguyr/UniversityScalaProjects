package backend

object MapTile {

  def generateRow(row: String): List[MapTile] = {
    row.map((ch: Char) => MapTile(ch.toString)).toList
  }

  def apply(tileType: String): MapTile = {
    tileType match {
      case "g" => new MapTile("grass", true)
      case "f" => new MapTile("forest", true)
      case "m" => new MapTile("mountain", true)
      case "w" => new MapTile("water", false)
    }
  }

}

class MapTile(val tileType: String, val passable: Boolean) {

  var adjacencyList: List[(Int,Int)] = List()

  var id = (0,0)
  var x = id._1
  var y = id._2

  var visited = false

  def addEdge(coordinates: (Int, Int)): Unit = {
    adjacencyList = adjacencyList :+ coordinates
  }
}
