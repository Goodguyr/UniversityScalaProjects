package c.tests

import c.src.StockPrices
import org.scalatest._

class TestC extends FunSuite {

  val filename: String = "data/MSFT.csv"

  test("Testing for Objective C") {

    val sp: StockPrices = new StockPrices("Microsoft", filename)

    assert(sp.didPriceIncrease("2015-02-03", "2015-02-04"))
    assert(sp.didPriceIncrease("2015-01-02", "2015-12-31"))
    assert(sp.didPriceIncrease("2015-04-17", "2015-06-19"))

    assert(!sp.didPriceIncrease("2015-05-01", "2015-10-16"))
    assert(!sp.didPriceIncrease("2015-0l-27", "2015-01-28"))
    assert(!sp.didPriceIncrease("2015-02-23", "2015-04-20"))

  }



}
