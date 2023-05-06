package backend.database.eventListeners

import backend.authentificationSystem.AuthenticationTestServer
import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.listener.DisconnectListener

class LeaveListener(server:AuthenticationTestServer) extends DisconnectListener {
  def onDisconnect(client: SocketIOClient): Unit = {
    server.saveGameData(client)
  }
}
