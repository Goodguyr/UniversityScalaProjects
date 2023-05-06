package backend.database

import java.sql.{Connection, DriverManager, ResultSet}


class MySQLDatabase extends Database {

  val url:String = "jdbc:mysql://localhost/mysql?serverTimezone=UTC"
  val username:String = "root"
  var secret = new Secret
  val password:String = secret.password
  var connection: Connection = DriverManager.getConnection(url, username, password)

  setupTable()

  def setupTable(): Unit = {
    val statement = connection.createStatement()
    statement.execute("CREATE TABLE IF NOT EXISTS JRPG_Users (username TEXT, password TEXT, salt TEXT, gameState TEXT)")
  }

  override def registerNewPlayer(username: String, password: String, salt: String): Unit = {
    val statement = connection.prepareStatement("INSERT INTO JRPG_Users VALUE (?, ?, ?, ?)")
    statement.setString(1, username)
    statement.setString(2, password)
    statement.setString(3, salt)
    statement.setString(4, "NewPlayersInfo")
    statement.execute()
  }

  override def playerRegistered(username: String): Boolean = {
    val statement = connection.prepareStatement("SELECT * FROM JRPG_Users WHERE username=?")
    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()
    result.next()
  }

  override def getSalt(username: String): String = {
    val statement = connection.prepareStatement("SELECT * FROM JRPG_Users WHERE username=?")
    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()
    result.next()
    result.getString("salt")
  }

  override def checkInfo(username: String, password: String): Boolean = {
    val statement = connection.prepareStatement("SELECT * FROM JRPG_Users WHERE username=?")
    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()
    result.next()
    val passwordFromDB = result.getString("password")
    if(passwordFromDB == password){
      true
    }
    else{
      false
    }
  }

  override def saveGameInfo(username: String, gameInfo: String): Unit = {
    val statement = connection.prepareStatement("UPDATE JRPG_Users SET gameState = ? WHERE username = ?")
    statement.setString(1, gameInfo)
    statement.setString(2, username)
    statement.execute()
  }

  override def getGameInfo(username: String): String = {
    val statement = connection.prepareStatement("SELECT * FROM JRPG_Users WHERE username=?")
    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()
    result.next()
    result.getString("gameState")
  }
}
