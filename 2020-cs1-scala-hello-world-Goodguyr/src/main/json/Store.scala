package json

import play.api.libs.json.{JsValue, Json}

class Store (var cashInRegister: Double, var inventory: List[String]) {
  //returns JSON string representing an object with keys "cashInRegister" and "Inventory"
  def toJSON(): String = {
    var cashJSON: JsValue = Json.toJson(cashInRegister)
    var inventoryJson: JsValue = Json.toJson(inventory)
    var jsonReturn: Map[String, JsValue] = Map(
      "cashInRegister" -> cashJSON,
      "inventory" -> inventoryJson)
    Json.stringify(Json.toJson(jsonReturn))
  }

  def fromJSON(jsonString: String): Unit = {
    var parsed = Json.parse(jsonString)
    this.cashInRegister = (parsed \ "cashInRegister").as[Double]
    this.inventory = (parsed \ "inventory").as[List[String]]
  }
}