package backend.database

trait Database {

  def registerNewPlayer(username: String, password:String, salt:String)
  def playerRegistered(username: String): Boolean
  def getSalt(username:String):String
  def checkInfo(username:String, password: String): Boolean
  def saveGameInfo(username:String, gameInfo:String)
  def getGameInfo(username:String):String
}
