package tests

import genetics.GeneticAlgorithm
import genetics.geometry.{Point, Polynomial}
import org.scalatest.FunSuite

class TestPolynomial extends FunSuite {

  val EPSILON: Double = 0.1

  def equalLists(tests:List[Double], guessed:List[Double]):Boolean = {
    var counter = 0
    for(i <- tests.indices){
      if((tests(i) - guessed(i)).abs < EPSILON){
        counter += 1
      }
    }

    if(counter == tests.length){
      true
    }
    else{
      false
    }
  }

  test("Genetic Algorithm Compute Polynomial Regression for y=x^2") {
    val targetPoint = List(new Point(-2, 4), new Point(-1, 1), new Point(1, 1))
    val computed = GeneticAlgorithm.geneticAlgorithm(Polynomial.incubator, Polynomial.costFunction(targetPoint), 3)
    assert(equalLists(List(1.0, 0.0, 0.0), computed.coefficients))
  }

  test("Genetic Algorithm Compute Polynomial Regression for y=3x") {
    val targetPoint = List(new Point(-2, -6), new Point(0, 0), new Point(5, 15))
    val computed = GeneticAlgorithm.geneticAlgorithm(Polynomial.incubator, Polynomial.costFunction(targetPoint), 2)
    assert(equalLists(List(3.0, 0.0), computed.coefficients))
  }

  test("Genetic Algorithm Compute Polynomial Regression for y=5") {
    val targetPoint = List(new Point(-2, 5), new Point(0, 5), new Point(5, 5))
    val computed = GeneticAlgorithm.geneticAlgorithm(Polynomial.incubator, Polynomial.costFunction(targetPoint), 1)
    assert(equalLists(List(5.0), computed.coefficients))
  }

  test("Genetic Algorithm Compute Polynomial Regression for y=4x^2-4x+3") {
    val targetPoint = List(new Point(0, 3), new Point(0.5, 2), new Point(2, 11))
    val computed = GeneticAlgorithm.geneticAlgorithm(Polynomial.incubator, Polynomial.costFunction(targetPoint), 3)
    println(computed.coefficients)
    assert(equalLists(List(4.0, -4.0, 3.0), computed.coefficients))
  }

  test("Genetic Algorithm Compute Polynomial Regression for y=x^3+5x^2-5x+8"){
    val targetPoint = List(new Point(-3, 41), new Point(-2, 30), new Point(0, 8), new Point(2, 26))
    val computed = GeneticAlgorithm.geneticAlgorithm(Polynomial.incubator, Polynomial.costFunction(targetPoint), 4)
    assert(equalLists(List(1, 5.0, -5.0, 8), computed.coefficients))
  }

  test("Genetic Algorithm Compute Polynomial Regression for y=2x^3+8x^2-x+10"){
    val targetPoint = List(new Point(-3, 31), new Point(-1, 17), new Point(0, 10), new Point(2, 56))
    val computed = GeneticAlgorithm.geneticAlgorithm(Polynomial.incubator, Polynomial.costFunction(targetPoint), 4)
    assert(equalLists(List(2, 8.0, -1.0, 10), computed.coefficients))
  }
}
