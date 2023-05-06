package clicker.model.Equipment

import clicker.model.GameActor

abstract class Equipment {

  var price:Double
  var priceIncrease:Double
  var name:String
  var id:String
  var numberOwned:Int

  def buy(actor:GameActor)
}
