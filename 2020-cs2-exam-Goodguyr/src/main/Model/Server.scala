package Model

import java.util.EventListener

import com.corundumstudio.socketio.listener.DataListener
import com.corundumstudio.socketio.{AckRequest, Configuration, SocketIOClient, SocketIOServer}
import play.api.libs.json._

class Server {

  var socketToTV:Map[SocketIOClient, TV] = Map()

  val server: SocketIOServer = new SocketIOServer(config)

  val config: Configuration = new Configuration {
    setHostname("localhost")
    setPort(8080)
  }

  server.addConnectListener(
    (socket: SocketIOClient) => {
      var tv = new TV
      socketToTV += socket -> tv
    }
  )

  server.addDisconnectListener(
    (socket: SocketIOClient) => {
      socketToTV -= socket
    }
  )

  server.addEventListener("buttonClick", classOf[String], new DataListener[String] {
    override def onData(client: SocketIOClient, data: String, ackSender: AckRequest): Unit = {
      var parsed:JsValue = Json.parse(data)
      var action = (parsed \ "Data").as[String]
      processAction(action, client)
    }
  })

  server.start()

  def processAction(action:String, socketIOClient: SocketIOClient):Unit = {
    var response:Map[String, String] = Map()
    var allowedAction:List[String] = List("KEY_POWER", "KEY_CHUP", "KEY_CHDOWN", "KEY_VOLUP", "KEY_VOLDOWN")
    var tv = socketToTV(socketIOClient)

    tv.doWhatRemoteTellsMe(action)

    if(tv.volume == 0 && tv.state.on && action == "KEY_VOLDOWN"){
      response += "Response" -> "Fail"
    }
    else if(allowedAction.contains(action) && tv.state.on){
      response += "Response" -> "OK"
    }
    else if(allowedAction.contains(action)){
      response += "Response" -> "Fail"
    }
    else{
      response += "Response" -> "Error"
    }

    socketIOClient.sendEvent("responseFromServer", Json.stringify(Json.toJson(response)))
  }
}
