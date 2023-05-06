package clicker.tests

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import clicker.{BuyEquipment, ClickGold, GameState, Update}
import clicker.database.DatabaseActor
import clicker.model.GameActor
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import play.api.libs.json.{JsValue, Json}

import scala.concurrent.duration._

class TestIdle extends TestKit(ActorSystem("TestIdle"))
  with ImplicitSender
  with WordSpecLike
  with Matchers
  with BeforeAndAfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }


  "A Clicker Game" must {
    "earn the correct idle income" in {

      val database = system.actorOf(Props(classOf[DatabaseActor], "test"))
      val gameActor = system.actorOf(Props(classOf[GameActor], "username", database))

      for(i <- 1 to 20){
        gameActor ! ClickGold
        gameActor ! ClickGold
        gameActor ! ClickGold
        gameActor ! ClickGold
        gameActor ! ClickGold
        gameActor ! ClickGold
        gameActor ! ClickGold
        gameActor ! ClickGold
        gameActor ! ClickGold
        gameActor ! ClickGold
      }
      gameActor ! BuyEquipment("excavator")

      expectNoMessage(900.millis)

      gameActor ! Update
      var gs: GameState = expectMsgType[GameState](200.millis)

      gameActor ! Update
      gs = expectMsgType[GameState](200.millis)

      var gameStateJSON: String = gs.gameState

      var  parsedData:JsValue = Json.parse(gameStateJSON)
      var gold:Double = (parsedData \ "gold").as[Double]
      var excavatorAmount:Int = (((parsedData \ "equipment") \ "excavator") \ "numberOwned").as[Int]

      assert(gold > 9 && gold < 11 && excavatorAmount == 1)

      expectNoMessage(1900.millis)
      gameActor ! Update
      gs = expectMsgType[GameState](300.millis)
      gameStateJSON = gs.gameState
      parsedData = Json.parse(gameStateJSON)
      gold = (parsedData \ "gold").as[Double]
      assert(gold >= 29 && gold <= 31 && excavatorAmount == 1)
    }
  }


}
