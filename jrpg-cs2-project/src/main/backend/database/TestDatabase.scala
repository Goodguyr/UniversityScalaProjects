package backend.database

class TestDatabase extends Database {

  var usernameToSalt:Map[String, String] = Map()
  var userNameToPassword:Map[String, String] = Map()
  var userNameToGameData:Map[String, String] = Map()

  override def registerNewPlayer(username: String, password: String, salt: String): Unit = {
    userNameToPassword += username -> password
    usernameToSalt += username -> salt
    userNameToGameData += username -> "NewPlayersInfo"
  }

  override def playerRegistered(username: String): Boolean = userNameToGameData.keySet.contains(username)

  override def getSalt(username: String): String = usernameToSalt(username)

  override def checkInfo(username: String, password: String): Boolean = {
    if(password == userNameToPassword(username)) true
    else false
  }

  override def saveGameInfo(username: String, gameInfo: String): Unit = userNameToGameData += username -> gameInfo

  override def getGameInfo(username: String): String = userNameToGameData(username)
}
