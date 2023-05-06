package clicker.server

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import clicker.database.DatabaseActor
import clicker.model.GameActor
import clicker.{BuyEquipment, ClickGold, GameState, Save, SaveGames, Update, UpdateGames}
import com.corundumstudio.socketio.listener.{DataListener, DisconnectListener}
import com.corundumstudio.socketio.{AckRequest, Configuration, SocketIOClient, SocketIOServer}
import play.api.libs.json.Json

/** *
  * @param database      Reference to the database actor
  * @param configuration Custom configuration of the game (Used in Bonus Objective. Pass empty string before bonus)
  */
class ClickerServer(val database: ActorRef, configuration: String) extends Actor {

  val actorSystem:ActorSystem = ActorSystem()

  var socketToActor:Map[SocketIOClient, ActorRef] = Map()
  var usernameToSocket: Map[String, SocketIOClient] = Map()
  var socketToUsername: Map[SocketIOClient, String] = Map()

  val config: Configuration = new Configuration {
    setHostname("localhost")
    setPort(8080)
  }

  val server: SocketIOServer = new SocketIOServer(config)

  server.start()

  server.addEventListener("register", classOf[String], new DataListener[String] {
    override def onData(client: SocketIOClient, username: String, ackSender: AckRequest): Unit = {
      var player = actorSystem.actorOf(Props(classOf[GameActor], username, database))
      usernameToSocket += username -> client
      socketToUsername += client -> username
      socketToActor += client -> player
    }
  })

  server.addEventListener("clickGold", classOf[Nothing], new DataListener[Nothing] {
    override def onData(client: SocketIOClient, data: Nothing, ackSender: AckRequest): Unit = {
      var player = socketToActor(client)
      player ! ClickGold
    }
  })

  server.addEventListener("buy", classOf[String], new DataListener[String] {
    override def onData(client: SocketIOClient, data: String, ackSender: AckRequest): Unit = {
      var player = socketToActor(client)
      player ! BuyEquipment(data)
    }
  })

  server.addDisconnectListener(new DisconnectListener {
    override def onDisconnect(client: SocketIOClient): Unit = {
      var username = socketToUsername(client)
      usernameToSocket -= username
      socketToUsername -= client
      socketToActor -= client
    }
  })

  def sendGameState(gameState:String):Unit = {
    var json = Json.parse(gameState)
    var username = (json \ "username").as[String]
    var socket = usernameToSocket(username)

    socket.sendEvent("gameState", gameState)
  }

  override def receive: Receive = {

    case SaveGames => socketToActor.foreach(x => x._2 ! Save)

    case UpdateGames => socketToActor.foreach(x => x._2 ! Update)

    case gameState: GameState => sendGameState(gameState.gameState)
  }



  // Comment in server.stop() to stop your web socket server when the actor system shuts down. This will free
  // the port and allow to to test again immediately. Note that this doesn't work if you stop your server through
  // IntelliJ. If you use IntelliJ's stop button you will have to wait for the port to be freed before restarting
  // your server. By using the TestServer test suite and this method to stop the server you can avoid having to
  // wait before restarting while testing
  override def postStop(): Unit = {
    println("stopping server")
//    server.stop()
  }
}


object ClickerServer {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem()

    import actorSystem.dispatcher
    import scala.concurrent.duration._

    val db = actorSystem.actorOf(Props(classOf[DatabaseActor], "mySQL"))
    val server = actorSystem.actorOf(Props(classOf[ClickerServer], db, ""))

    actorSystem.scheduler.schedule(0.milliseconds, 100.milliseconds, server, UpdateGames)
    actorSystem.scheduler.schedule(0.milliseconds, 1000.milliseconds, server, SaveGames)
  }
}
