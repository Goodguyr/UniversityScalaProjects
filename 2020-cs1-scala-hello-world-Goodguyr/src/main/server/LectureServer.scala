package server

import com.corundumstudio.socketio.listener.{ConnectListener, DataListener, DisconnectListener}
import com.corundumstudio.socketio.{AckRequest, Configuration, SocketIOClient, SocketIOServer}

class LectureServer {

  var number = 0

  val config: Configuration = new Configuration {
    setHostname("localhost")
    setPort(8080)
  }

  val server:SocketIOServer = new SocketIOServer(config)

  server.addConnectListener(new ConnectListener {
    override def onConnect(client: SocketIOClient): Unit = println("Connected:" + client)
  })

  server.addDisconnectListener(new DisconnectListener {
    override def onDisconnect(client: SocketIOClient): Unit = println("Disconnected:" + client)
  })

  server.addEventListener("increment", classOf[Nothing], new DataListener[Nothing] {
    override def onData(client: SocketIOClient, data:Nothing,  ackSender: AckRequest): Unit = {
      number += 1
    }
  })

  def numberOfMessages:Int = number

  server.start()
}
