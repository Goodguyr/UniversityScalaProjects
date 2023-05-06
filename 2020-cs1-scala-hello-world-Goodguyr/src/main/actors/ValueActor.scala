package actors

import akka.actor.Actor

class ValueActor(startingValue:Int) extends Actor {

  var startingNum:Int = startingValue

  def receive: Receive = {

    case increase: Increase =>
      startingNum += increase.num

    case GetValue => sender() ! Value(startingNum)
  }
}
