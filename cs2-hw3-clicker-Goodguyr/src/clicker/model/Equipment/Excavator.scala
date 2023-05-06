package clicker.model.Equipment

import clicker.model.GameActor

class Excavator extends Equipment {
  override var price: Double = 200.0
  override var priceIncrease: Double = 1.1
  override var name: String = "Excavator"
  override var id: String = "excavator"
  override var numberOwned: Int = 0

  override def buy(actor: GameActor): Unit = {
    if (actor.currentGold >= price){
      actor.excavatorCount += 1
      numberOwned = actor.excavatorCount
      actor.currentGold -= price
      price *= priceIncrease
      actor.updateGoldPerClick()
      actor.updateIdleIncome()
    }
  }
}
