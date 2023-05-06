package clicker.model.Equipment

import clicker.model.GameActor

class GoldMine extends Equipment {
  override var price: Double = 1000.0
  override var priceIncrease: Double = 1.1
  override var name: String = "Gold Mine"
  override var id: String = "shovel"
  override var numberOwned: Int = 0

  override def buy(actor: GameActor): Unit = {
    if (actor.currentGold >= price){
      actor.mineCount += 1
      numberOwned = actor.mineCount
      actor.currentGold -= price
      price *= priceIncrease
      actor.updateGoldPerClick()
      actor.updateIdleIncome()
    }
  }
}
