package server

import com.corundumstudio.socketio.listener.{ConnectListener, DataListener, DisconnectListener}
import com.corundumstudio.socketio.{AckRequest, Configuration, SocketIOClient, SocketIOServer}
import play.api.libs.json.{JsValue, Json}

class DMServer {

  var usernameToSocket: Map[String, SocketIOClient] = Map()
  var socketToUsername: Map[SocketIOClient, String] = Map()

  val config: Configuration = new Configuration {
    setHostname("localhost")
    setPort(8080)
  }

  val server:SocketIOServer = new SocketIOServer(config)

  server.addEventListener("register", classOf[String], new DataListener[String] {
    override def onData(client: SocketIOClient, data: String, ackSender: AckRequest): Unit = {
      usernameToSocket += (data -> client)
      socketToUsername += (client -> data)
    }
  })

  server.addEventListener("direct_message", classOf[JsValue], new DataListener[JsValue] {
    override def onData(client: SocketIOClient, data: JsValue, ackSender: AckRequest): Unit = {
      var sender:String = socketToUsername(client)
      var message:String = (data \ "message").as[String]
      var recipient = usernameToSocket((data \ "to").as[String])
      var readyMessage = Map("from" -> sender, "message" -> message)
      var jsonReply = Json.toJson(readyMessage)
      recipient.sendEvent("dm", recipient, jsonReply)
    }
  })
}
