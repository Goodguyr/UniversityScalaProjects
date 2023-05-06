package week13

import week11.Queue

class Graph[A] {

  var nodes: Map[Int, A] = Map()
  var adjacencyList: Map[Int, List[Int]] = Map()


  def addNode(index: Int, a: A): Unit = {
    nodes += index -> a
    adjacencyList += index -> List()
  }

  def addEdge(index1: Int, index2: Int): Unit = {
    adjacencyList += index1 -> (index2 :: adjacencyList(index1))
    adjacencyList += index2 -> (index1 :: adjacencyList(index2))
  }

  /*def hasLoop():Boolean = {
    var visited:List[List[Int]] = List()
    var prevKey = 0
    for(i <- adjacencyList.keys){
      if (prevKey != 0){
        for(n <- adjacencyList(i)){
          for(k <- adjacencyList(prevKey)){
            if(n != k){

            }
          }
        }

      }
      prevKey = i
    }
  }
*/
  def connected(index1: Int, index2: Int): Boolean = {
    adjacencyList(index1).contains(index2)
  }

  def isPath(path: List[Int]): Boolean = {
    // initialize prev to an invalid node id
    var prev = nodes.keys.min - 1
    var valid = true
    for (index <- path) {
      if (prev != nodes.keys.min - 1) {
        if (!connected(prev, index)) {
          valid = false
        }
      }
      prev = index
    }
    valid
  }

  def areConnected(index1: Int, index2: Int): Boolean = {
    var visited: Set[Int] = Set(index1)
    var toExplore:Queue[Int] = new Queue()
    toExplore.enqueue(index1)

    while(!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()
      for (node <- adjacencyList(nodeToExplore)) {
        if (!visited.contains(node)) {
          toExplore.enqueue(node)
          visited += node
        }
      }
    }

    visited.contains(index2)
  }

  def distance(index1: Int, index2: Int): Int = {
    // TODO: Return the distance between index1 and index2 in this graph
    // You may assume that the two nodes are connected
    0
  }
}
