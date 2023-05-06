package actors


import akka.actor.{Actor}

case class Answer(reply:String)

case object CallingYou

class NobodysHome extends Actor {

  override def receive: Receive = {
    case CallingYou => sender ! Answer("Thank you for sending this message")
  }
}
