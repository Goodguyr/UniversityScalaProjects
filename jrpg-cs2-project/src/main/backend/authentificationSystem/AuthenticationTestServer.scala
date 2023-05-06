package backend.authentificationSystem

import java.util

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.io.Tcp.Event
import backend.database.MySQLDatabase
import backend.database.eventListeners.{LeaveListener, LoginListener, RegistrationListener}
import com.corundumstudio.socketio.{AckRequest, Configuration, SocketIOClient, SocketIOServer}
import play.api.libs.json._


class AuthenticationTestServer(authenticationSystem: ActorRef) extends Actor {

  // Very little setup is provided for authentication. If you are completing authentication for your team
  // you must also design and implement a socket server and front end for your demo that will allow you to
  // to prove that all the required functionality is implemented

  var usernameToSocket: Map[String, SocketIOClient] = Map()
  var socketToUsername: Map[SocketIOClient, String] = Map()

  val config: Configuration = new Configuration {
    setHostname("localhost")
    setPort(8080)
  }

  val server: SocketIOServer = new SocketIOServer(config)

  server.start()

  def register(client: SocketIOClient, data:String):Unit ={
    val parsed = Json.parse(data)
    val username = (parsed \ "username").as[String]
    val password = (parsed \ "password").as[String]
    socketToUsername += client -> username
    usernameToSocket += username -> client
    authenticationSystem ! Register(username, password)
  }

  server.addEventListener("register", classOf[String], new RegistrationListener(this))

  def login(client: SocketIOClient, data:String):Unit = {
    val parsed = Json.parse(data)
    val username = (parsed \ "username").as[String]
    val password = (parsed \ "password").as[String]
    socketToUsername += client -> username
    usernameToSocket += username -> client
    authenticationSystem ! Login(username, password)
  }

  server.addEventListener("login", classOf[String], new LoginListener(this))

  def saveGameData(client: SocketIOClient):Unit = {
    val username = socketToUsername(client)
    val gameState = "SavedJsonGameState"
    authenticationSystem ! SaveGame(username, gameState)
  }

  server.addDisconnectListener(new LeaveListener(this))

  def checkRegistrationEvent(username:String, result:Boolean, message:String):Unit = {
    var socket = usernameToSocket(username)
    socket.sendEvent("registrationResult", message)
  }

  def loginFailed(username:String, message:String):Unit = {
    var socket = usernameToSocket(username)
    socket.sendEvent("loginFailed", message)
  }

  def successfulLogin(username:String, gameState:String):Unit = {
    val socket = usernameToSocket(username)
    socket.sendEvent("successfulLogin", gameState)
  }

  override def receive: Receive = {

    case authenticated:Authenticated => successfulLogin(authenticated.username, authenticated.charactersJSON)

    case failedLogin:FailedLogin => loginFailed(failedLogin.username, failedLogin.message)

    case registrationResult: RegistrationResult => checkRegistrationEvent(registrationResult.username, registrationResult.registered, registrationResult.message)
  }


  override def postStop(): Unit = {
    println("stopping server")
    server.stop()
  }
}

object AuthenticationTestServer{
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem()

    val auth = actorSystem.actorOf(Props(classOf[AuthenticationSystem]))
    val server = actorSystem.actorOf(Props(classOf[AuthenticationTestServer], auth))
  }
}