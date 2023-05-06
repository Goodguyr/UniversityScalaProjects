package c.src

import scala.io.Source

class StockPrices(val name: String, pricesFilename: String) {

  val prices: Map[String, Double] = parsePricesFile(pricesFilename)

  /**
    * Parses a file containing stock prices at particular dates in the format "date,price,volume" and returns
    * a Map which maps dates to prices. All dates are in the format YYYY-MM-DD
    *
    * @param filename The name of the file to be parsed
    * @return A Map of dates (as Strings) to prices (as Doubles)
    *
    *         Note: This method shows off some advanced Scala syntax. If you can't read this method, don't panic
    */
  def parsePricesFile(filename: String): Map[String, Double] = {
    (for (line <- Source.fromFile(filename).getLines()) yield {
      val splits: Array[String] = line.split(",")
      splits(0) -> splits(1).toDouble
    }).toMap
  }


  /**
    * Retrieves the price of the stock on a particular date
    *
    * @param date The date to lookup
    * @return The price of the stock on that date
    */
  def getPrice(date: String): Double = {
    this.prices.getOrElse(date, 0.0)//l in one of the tests , should check inputs
  }


  /**
    * Checks if the price of the stock went on the day after the input date
    *
    * @param date Date to check
    * @return true if the price increased from the given day to the next, false otherwise
    */
  def didPriceIncrease(date1: String, date2: String): Boolean = {
    val startingPrice: Double = this.getPrice(date1)
    val endingPrice: Double = this.getPrice(date2)
    endingPrice > startingPrice
  }

}
