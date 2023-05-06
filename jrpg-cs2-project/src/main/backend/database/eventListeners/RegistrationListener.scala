package backend.database.eventListeners

import backend.authentificationSystem.AuthenticationTestServer
import com.corundumstudio.socketio.listener.DataListener
import com.corundumstudio.socketio.{AckRequest, SocketIOClient}

class RegistrationListener (server:AuthenticationTestServer) extends DataListener[String] {
  def onData(client: SocketIOClient, data: String, ackSender: AckRequest): Unit = {
    server.register(client, data)
  }
}
