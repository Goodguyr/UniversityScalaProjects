package mvc

class Model {

  var messageList:List[String] = List()

  def sendMessage(message: String): Unit = {
    messageList = message :: messageList

  }

  def allMessages(): List[String] = {
    messageList
  }

}
