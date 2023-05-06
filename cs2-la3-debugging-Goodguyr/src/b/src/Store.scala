package b.src

class Store() {

  /**
    * A list of items available in the store along with the price of each item
    */
  var items: List[Item] = List(
    new Item("milk", 2.0),
    new Item("cereal", 3.0)
  )


  /**
    * Purchase a cart of items from the store
    * @param cart A map of items with the quantity of each item
    */
  def buyStuff(cart: Map[Item, Int]): Unit = {
    for ((item, quantity) <- cart) {
      for (i <- 0 to quantity) {
        item.purchase()
      }
    }
  }

  /**
    * Simulates a customer purchasing one gallon of milk and 3 boxes of cereal from the store
    */
  def simulateCustomer(): Any = {
    var cart: Map[Item, Int] = Map()
    cart += (new Item("milk", 2.0) -> 1)
    cart += (new Item("cereal", 3.0) -> 3)
  }
}
