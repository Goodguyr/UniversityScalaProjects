package backend.authentificationSystem

import java.util.UUID

import akka.actor.{Actor, ActorRef}
import backend.database.{Database, MySQLDatabase, TestDatabase}
import com.roundeights.hasher.Implicits._


class AuthenticationSystem()  extends Actor{


  val database: Database = new MySQLDatabase

  def checkRegistrationCriteria(username:String, password:String):Unit = {
    if(password.length > 6 && password.exists(_.isUpper) && password.exists(_.isDigit) && username.length > 1 && !database.playerRegistered(username)){
      val salt = UUID.randomUUID()
      val hashedPassword = (password + salt).md5
      database.registerNewPlayer(username, hashedPassword, salt.toString)
      sender() ! RegistrationResult(username, registered = true, "Registration complete!")
    }
    else if(username.length < 4){
      sender() ! RegistrationResult(username, registered = false, "Username is too short, username must contain at least 4 characters!")
    }
    else if(database.playerRegistered(username)){
      sender() ! RegistrationResult(username, registered = false, "Username is taken, try again!")
    }
    else if(password.length < 7){
      sender() ! RegistrationResult(username, registered = false, "Password too short, length of the password must be over 6 characters!")
    }
    else if(!password.exists(_.isUpper)){
      sender() ! RegistrationResult(username, registered = false, "Password has to contain at least 1 capital letter!")
    }
    else if(!password.exists(_.isDigit)){
      sender() ! RegistrationResult(username, registered = false, "Password has to contain at least 1 digit!")
    }
  }

  def getLoginInfo(username:String, password:String):Unit = {
    if(database.playerRegistered(username)){
      val salt = database.getSalt(username)
      val hashedPassword = (password + salt).md5
      if(database.checkInfo(username, hashedPassword)){
        sender() ! Authenticated(username, database.getGameInfo(username))
      }
      else{
        sender() ! FailedLogin(username, "Incorrect password")
      }
    }
    else{
      sender() ! FailedLogin(username, "Incorrect username")
    }
  }


  override def receive: Receive = {
    case register:Register => checkRegistrationCriteria(register.username, register.password)

    case login:Login => getLoginInfo(login.username, login.password)

    case saveGame:SaveGame => database.saveGameInfo(saveGame.username, saveGame.charactersJSON)
  }

}
