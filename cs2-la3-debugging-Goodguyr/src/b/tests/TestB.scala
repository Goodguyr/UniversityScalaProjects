package b.tests

import b.src.Store
import org.scalatest._

class TestB extends FunSuite {

  test("Testing for Objective B") {

    val store: Store = new Store()
    store.simulateCustomer()

    for(item <- store.items){
      if(item.description == "milk"){
        assert(item.timesPurchased == 1)
      } else if(item.description == "cereal"){
        assert(item.timesPurchased == 3)
      }
    }

  }

}
