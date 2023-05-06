package clicker.tests

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import clicker.{BuyEquipment, ClickGold, GameState, Save, Update}
import clicker.database.DatabaseActor
import clicker.model.GameActor
import org.scalatest._
import play.api.libs.json.{JsValue, Json}

import scala.concurrent.duration._

class TestSave extends TestKit(ActorSystem("TestSave"))
  with ImplicitSender
  with WordSpecLike
  with Matchers
  with BeforeAndAfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }


  "A Clicker Game" must {
    "save and load properly" in {

      val database = system.actorOf(Props(classOf[DatabaseActor], "test"))
      val gameActor = system.actorOf(Props(classOf[GameActor], "username", database))

      for(i <- 1 to 14){
        gameActor ! ClickGold
      }
      gameActor ! BuyEquipment("shovel")
      expectNoMessage(300.millis)

      gameActor ! Update
      var gs: GameState = expectMsgType[GameState](300.millis)

      gameActor ! Save

      var gameStateJSON: String = gs.gameState
      var  parsedData:JsValue = Json.parse(gameStateJSON)
      var gold:Double = (parsedData \ "gold").as[Double]
      var shovelAmount:Int = (((parsedData \ "equipment") \ "shovel") \ "numberOwned").as[Int]

      assert(gold == 4.0 && shovelAmount == 1)

      val gameActor_2 = system.actorOf(Props(classOf[GameActor], "username", database))

      gameActor_2 ! Update
      var gs2 = expectMsgType[GameState](500.millis)

      var gameStateJSON2 = gs2.gameState

      var parsedData2 = Json.parse(gameStateJSON2)
      var gold2 = (parsedData2 \ "gold").as[Double]
      var shovelAmount2 = (((parsedData2 \ "equipment") \ "shovel") \ "numberOwned").as[Int]

      assert(gold2 == 4.0 && shovelAmount2 == 1)
    }
  }
}
