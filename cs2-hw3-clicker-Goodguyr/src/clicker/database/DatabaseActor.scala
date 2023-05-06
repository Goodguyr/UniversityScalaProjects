package clicker.database

import akka.actor.Actor
import clicker.{GameState, SaveGame, StartedGame}

/***
  * @param dbType Indicates the type of database to be used. Use "mySQL" to connect to a MySQL server, or "test" to
  *               use data structures in a new class that extends the Database trait.
  */
class DatabaseActor(dbType: String) extends Actor {

  val database: Database = dbType match {
    case "mySQL" => new MySQLDatabase()
    case "test" => new TestDatabase
  }

  def checkIfRegistered(username:String):Unit = {
    if (database.playerExists(username)){
      sender() ! GameState(database.loadGameState(username))
    }
    else database.createPlayer(username)
  }

  override def receive: Receive = {

    case saveGame:SaveGame => database.saveGameState(saveGame.username, saveGame.gameState)

    case startedGame: StartedGame => checkIfRegistered(startedGame.username)
  }
}
