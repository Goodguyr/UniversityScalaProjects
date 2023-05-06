package backend

import backend.physics.PhysicsVector
import com.sun.tools.javac.comp.Todo
import play.api.libs.json.Json.MacroOptions

import scala.collection.mutable

object PathFinding {

  def findPath(start: GridLocation, end: GridLocation, map: List[List[MapTile]]): List[GridLocation] = {
    def assignId(): Unit = {
      var rowCounter = 0

      for (row <- map) {
        var tileCounter = 0
        for (tile <- row) {
          tile.id = (tileCounter, rowCounter)
          tileCounter += 1
        }
        rowCounter += 1
      }
    }

    assignId()

    def makeGraph(): Unit = {
      var rowCounter = 0

      for (row <- map) {
        var tileCounter = 0
        for (tile <- row) {
          if (tileCounter >= 1 && rowCounter <= map.length - 1 && tileCounter <= row.length - 1 && rowCounter >= 1) {
            tile.addEdge((tileCounter, rowCounter + 1))
            tile.addEdge((tileCounter, rowCounter - 1))
            tile.addEdge((tileCounter + 1, rowCounter))
            tile.addEdge((tileCounter - 1, rowCounter))
          }
          if (tileCounter == 0 && rowCounter == 0) {
            tile.addEdge((tileCounter, rowCounter + 1))
            tile.addEdge((tileCounter + 1, rowCounter))
          }
          if (tileCounter == 0 && rowCounter == map.length) {
            tile.addEdge((tileCounter, rowCounter - 1))
            tile.addEdge((tileCounter + 1, rowCounter))
          }
          if (tileCounter == row.length && rowCounter == map.length) {
            tile.addEdge((tileCounter, rowCounter - 1))
            tile.addEdge((tileCounter - 1, rowCounter))
          }
          if (tileCounter == row.length && rowCounter == 0) {
            tile.addEdge((tileCounter, rowCounter + 1))
            tile.addEdge((tileCounter - 1, rowCounter))
          }
          else if (rowCounter == 0 || rowCounter == map.length) {
            tile.addEdge((tileCounter - 1, rowCounter))
            tile.addEdge((tileCounter + 1, rowCounter))
            if (rowCounter == 0) tile.addEdge((tileCounter, rowCounter + 1))
            else tile.addEdge((tileCounter, rowCounter - 1))
          }
          else if (tileCounter == 0 || tileCounter == row.length) {
            tile.addEdge((tileCounter, rowCounter + 1))
            tile.addEdge((tileCounter, rowCounter - 1))
            if (tileCounter == row.length) tile.addEdge((tileCounter - 1, rowCounter))
            else tile.addEdge((tileCounter + 1, rowCounter))
          }
          tileCounter += 1
        }
        rowCounter += 1
      }
    }

    makeGraph

    def findNode(x: Int, y: Int): MapTile = {
      var node = new MapTile("", true)
      for (row <- map) {
        for (tile <- row) {
          if (tile.x == x && tile.y == y) {
            node = tile
          }
        }
      }
      node
    }

    def visitedToGridLoc(visited: MapTile): GridLocation = {
      var gridLoc: GridLocation = new GridLocation(0, 0)

      gridLoc = new GridLocation(visited.x, visited.y)
      gridLoc
    }

    var source = findNode(start.x, start.y)
    var destination = findNode(end.x, end.y)

    source.visited = true

    def BFS(endNode: MapTile): List[GridLocation] = {
      var route: List[GridLocation] = List()

      var parents: Map[MapTile, GridLocation] = Map()

      var toVisit: List[MapTile] = List()

      toVisit = toVisit :+ source

      while (toVisit.nonEmpty) {
        var deletedNode = findNode(toVisit.last.x, toVisit.last.y)
        toVisit = toVisit.init

        for (adjacentNode <- deletedNode.adjacencyList) {
          var node = findNode(adjacentNode._1, adjacentNode._2)
          if (node == endNode) {
            parents += node -> new GridLocation(deletedNode.x, deletedNode.y)

            route = new GridLocation(node.x, node.y) :: route

            while (route.head.x != source.x && route.head.y != source.y) {
              var currentValue = route.head
              for (key <- parents.keys) {
                if (key == findNode(currentValue.x, currentValue.y)) {
                  route = new GridLocation(key.x, key.y) :: route
                  currentValue = parents(key)
                }
              }
            }
          }
          else if (node.passable && !node.visited) {
            parents += node -> new GridLocation(deletedNode.x, deletedNode.y)
            node.visited = true
            toVisit = node :: toVisit
          }
        }
      }
      route
    }
    BFS(destination)
  }


  def getVelocity(path: List[GridLocation], currentLocation: PhysicsVector): PhysicsVector = {
    var currentTile: PhysicsVector = new PhysicsVector(currentLocation.x.floor, currentLocation.y.floor)
    var velocity: PhysicsVector = new PhysicsVector(0.0, 0.0)
    var magnitude = 5.0
    var position = 0

    if (path.nonEmpty){
      for (step <- path.indices){
        if (currentTile.x == path(step).x.floor && currentTile.y == path(step).y.floor){
          position = step
        }
      }
      if (position != path.length - 1){
        velocity.x = (path(position + 1).x.floor + 0.5 - currentLocation.x) * magnitude
        velocity.y = (path(position + 1).y.floor + 0.5 - currentLocation.y) * magnitude
      }
      if (position == (path.length - 1)){
        velocity.x = path.last.x.floor * magnitude + 0.5
        velocity.y = path.last.y.floor * magnitude + 0.5
        if ((path.last.x.floor + 0.5 - currentLocation.x).abs < 0.1 && (path.last.y.floor + 0.5 - currentLocation.y).abs < 0.1) {
          velocity = new PhysicsVector(0.0, 0.0)
        }
      }
    }
    velocity
  }
}