import json.Store
import org.scalatest._
import play.api.libs.json._

class jsonTest extends FunSuite {

  val store = new Store(25.0,List("Apples","Bananas"))
  val jsonString:String = store.toJSON()
  val cash:JsValue = Json.toJson(25.0)
  val items:JsValue = Json.toJson(List("Apples","Bananas"))

  val jsonTestString:Map[String, JsValue] = Map(
    "cashInRegister" -> cash,
    "inventory" -> items)

  val jsonTest:String = Json.stringify(Json.toJson(jsonTestString))

  assert(jsonString == jsonTest)

  store.fromJSON(jsonTest)
  assert(store.inventory == List("Apples","Bananas"))
  assert(store.cashInRegister == 25.00)
}
