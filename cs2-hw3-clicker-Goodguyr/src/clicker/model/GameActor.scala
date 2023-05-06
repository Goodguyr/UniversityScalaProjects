package clicker.model

import akka.actor.{Actor, ActorRef}
import clicker.model.Equipment.{Equipment, Excavator, GoldMine, Shovel}
import clicker._
import clicker.database.Database
import play.api.libs.json.{JsValue, Json}

class GameActor(username: String, database: ActorRef) extends Actor {

  var lastUpdateTime:Long = System.nanoTime()
  var currentGold:Double = 0.0
  var shovelCount:Int = 0
  var excavatorCount:Int = 0
  var mineCount:Int = 0
  var goldPerClick:Int = 1
  var idleIncome:Int = 0

  var shovel = new Shovel
  var excavator = new Excavator
  var goldMine = new GoldMine

  var equipmentList:List[Equipment] = List(shovel, excavator, goldMine)

  database ! StartedGame(this.username)

  def updateGoldPerClick():Unit = goldPerClick = 1 + shovelCount + (excavatorCount * 5)

  def purchaseEquipment(id:String): Unit = {
    for(item <- equipmentList){
      if(id == item.id) item.buy(this)
    }
  }

  def updateIdleIncome():Unit = {
    idleIncome = 0
    for(i <- 0 until excavatorCount) idleIncome += 10
    for(i <- 0 until mineCount) idleIncome += 100
  }

  def updateGold():Unit = {
    val oldTime:Long = lastUpdateTime
    lastUpdateTime = System.nanoTime()
    val difference = lastUpdateTime - oldTime
    currentGold += (difference * Math.pow(10, -9)) * idleIncome
  }

  def postValues():String = {
    updateGold()
    var itemInfo:List[Map[String, JsValue]] = List()

    for(item <- equipmentList){
      var oneItemInfo:Map[String, JsValue] = Map(
        "id" -> Json.toJson(item.id),
        "name" -> Json.toJson(item.name),
        "numberOwned" -> Json.toJson(item.numberOwned),
        "cost" -> Json.toJson(item.price)
      )
      itemInfo = itemInfo :+ oneItemInfo
    }

    val equipment:Map[String, JsValue] = Map(
      "shovel" -> Json.toJson(itemInfo.head),
      "excavator" -> Json.toJson(itemInfo(1)),
      "mine" -> Json.toJson(itemInfo(2))
    )

    var mapReply:Map[String, JsValue] = Map(
      "username" -> Json.toJson(username),
      "gold" -> Json.toJson(currentGold),
      "lastUpdateTime" -> Json.toJson(lastUpdateTime),
      "equipment" -> Json.toJson(equipment)
    )
    val jsonReply = Json.toJson(mapReply)
    Json.stringify(jsonReply)
  }

  def updateValues(data:String):Unit = {
    var jsonData:JsValue = Json.parse(data)
    currentGold = (jsonData \ "gold").as[Double]
    lastUpdateTime = (jsonData \ "lastUpdateTime").as[Long]
    shovelCount = (((jsonData \ "equipment") \ "shovel") \ "numberOwned").as[Int]
    excavatorCount = (((jsonData \ "equipment") \ "excavator") \ "numberOwned").as[Int]
    mineCount = (((jsonData \ "equipment") \ "mine") \ "numberOwned").as[Int]
    var shovel = new Shovel
    var excavator = new Excavator
    var goldMine = new GoldMine
    shovel.numberOwned = (((jsonData \ "equipment") \ "shovel") \ "numberOwned").as[Int]
    shovel.price = (((jsonData \ "equipment") \ "shovel") \ "cost").as[Double]
    excavator.numberOwned = (((jsonData \ "equipment") \ "shovel") \ "numberOwned").as[Int]
    excavator.price = (((jsonData \ "equipment") \ "excavator") \ "cost").as[Double]
    goldMine.numberOwned = (((jsonData \ "equipment") \ "mine") \ "numberOwned").as[Int]
    goldMine.price = (((jsonData \ "equipment") \ "mine") \ "cost").as[Double]
    equipmentList = List(shovel, excavator, goldMine)
    updateIdleIncome()
    updateGoldPerClick()
    updateGold()
  }

  override def receive: Receive = {

    case ClickGold => currentGold += goldPerClick

    case buyEquipment: BuyEquipment => purchaseEquipment(buyEquipment.equipmentId)

    case Update => sender() ! GameState(postValues())

    case Save => database ! SaveGame(username, postValues())

    case gameState:GameState => updateValues(gameState.gameState)
  }

}
