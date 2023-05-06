package genetics.geometry


object Polynomial {

  def costFunction(points: List[Point]): Polynomial => Double = (polynomial:Polynomial) => {

    var totalCost = 0.0

    for(point <- points){
      totalCost += (point.y - polynomial.evaluate(point.x)).abs
    }
    totalCost
  }

  def incubator(genes: List[Double]): Polynomial = {
    new Polynomial(genes)
  }

}

/**
  * Represents a polynomial given its coefficients ending with the constant coefficient
  *
  * Ex. new Polynomial(List(1.5, -2.2, 5)) represents 1.5*pow(x, 2) - 2.2*x + 5
  *
  */
class Polynomial(var coefficients: List[Double]) {

  def evaluate(x: Double): Double = {

    var powCounter = coefficients.length - 1
    var result:Double = coefficients.last

    for(i <- 0 until coefficients.length - 1){
      result += coefficients(i) * Math.pow(x, powCounter)
      powCounter -= 1
    }
    result
  }

}
