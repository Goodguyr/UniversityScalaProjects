package a.src

object StringSplitter {

  /**
    * Computes the percentage of input values which are true as an Int ranging from 0 to 100. All fractions are
    * rounded down. Ex. if the percentage is 44.99%, the returned value should be 44
    * @param line A string containing values of either "true" or "false" separated by semicolons
    * @return An Int representation of the percentage of true values in the input
    */
  def computePercentTrue(line: String): Int = {
    val splits: Array[String] = line.split(";")
    var trueCount = 0
    for (value <- splits) {
      val valueAsBoolean: Boolean = value.toBoolean
      if (valueAsBoolean) {
        trueCount += 1
      }
    }

    val result = trueCount / splits.length
    (result * 100.0).toInt
  }

}

