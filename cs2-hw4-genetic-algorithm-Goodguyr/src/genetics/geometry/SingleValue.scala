package genetics.geometry

object SingleValue {

  def costFunction(number: Double): SingleValue => Double = (number2: SingleValue ) => {
    (number - number2.value).abs
  }

  def incubator(genes: List[Double]): SingleValue = {
    new SingleValue(genes.head)
  }

}

class SingleValue(var value: Double){}
