package backend.database.eventListeners

import backend.authentificationSystem.AuthenticationTestServer
import com.corundumstudio.socketio.{AckRequest, SocketIOClient}
import com.corundumstudio.socketio.listener.DataListener

class LoginListener(server:AuthenticationTestServer) extends DataListener[String] {
  def onData(client: SocketIOClient, data: String, ackSender: AckRequest): Unit = {
    server.login(client, data)
  }
}
