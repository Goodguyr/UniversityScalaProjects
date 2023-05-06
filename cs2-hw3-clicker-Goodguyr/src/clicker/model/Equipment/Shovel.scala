package clicker.model.Equipment

import clicker.model.GameActor

class Shovel extends Equipment {
  override var price: Double = 10.0
  override var priceIncrease: Double = 1.05
  override var name: String = "Shovel"
  override var id: String = "shovel"
  override var numberOwned: Int = 0

  override def buy(actor: GameActor):Unit = {
    if (actor.currentGold >= price){
      actor.shovelCount += 1
      numberOwned = actor.shovelCount
      actor.currentGold -= price
      price *= priceIncrease
      actor.updateGoldPerClick()
      actor.updateIdleIncome()
    }
  }
}
