import actors.{Answer, CallingYou, NobodysHome}
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import scala.concurrent.duration._

class TestNobodysHome extends TestKit(ActorSystem("Test"))
with ImplicitSender
with WordSpecLike
with Matchers
with BeforeAndAfterAll {
  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "A NobodysHome Actor" must {
    "send a message when receiving a call" in {
      var nobodysHome = system.actorOf(Props(classOf[NobodysHome]))

      nobodysHome ! CallingYou
      var reply = expectMsgType[Answer](300.millis)

      assert(reply.reply == "Thank you for sending this message")
    }
  }
}
