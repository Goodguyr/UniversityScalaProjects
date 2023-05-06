import bank._
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.duration._

class BankTest extends TestKit(ActorSystem("NewSystem"))
  with ImplicitSender
  with WordSpecLike
  with Matchers
  with BeforeAndAfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "A value actor" must {
    "track a value" in {
      var bankAccount = system.actorOf(Props(classOf[BankAccount]))

      bankAccount ! Deposit(50)
      expectNoMessage(100.millis)

      bankAccount ! CheckBalance
      var balance: Balance = expectMsgType[Balance](1000.millis)

      assert(balance == Balance(50))

      bankAccount ! Withdraw(100)
      expectNoMessage(100.millis)

      bankAccount ! CheckBalance
      balance = expectMsgType[Balance](1000.millis)

      assert(balance == Balance(50))

      bankAccount ! Withdraw(10)
      expectNoMessage(100.millis)

      bankAccount ! CheckBalance
      balance = expectMsgType[Balance](1000.millis)

      assert(balance == Balance(40))
    }
  }
}
