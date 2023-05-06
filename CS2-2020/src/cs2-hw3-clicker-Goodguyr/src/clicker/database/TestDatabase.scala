package clicker.database

import com.corundumstudio.socketio.SocketIOClient

import scala.io.Source

class TestDatabase extends Database {

  var usernameData:Map[String, String] = Map()

  override def playerExists(username: String): Boolean = usernameData.keySet.contains(username)

  override def saveGameState(username: String, gameState: String): Unit = usernameData += username -> gameState

  override def loadGameState(username: String): String = usernameData(username)

  override def createPlayer(username: String): Unit = {
    val newGame: String = Source.fromFile("newGame.json").mkString
      .replace("USERNAME", username)
      .replace("TIMESTAMP", System.nanoTime().toString)
    usernameData += username -> newGame
  }
}
