package tests

import genetics.GeneticAlgorithm
import genetics.geometry.SingleValue
import org.scalatest.FunSuite

class TestSingleValue extends FunSuite {
  
  val EPSILON: Double = 0.05

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }

  test("Genetic Algorithm Finds a Simple Number") {
    val hiddenNumber = 50.0
    val computed = GeneticAlgorithm.geneticAlgorithm(SingleValue.incubator, SingleValue.costFunction(hiddenNumber), 1)
    val difference = (hiddenNumber - computed.value).abs
    println("Result: " + computed.value + " diference: " + difference)
    assert(equalDoubles(hiddenNumber, computed.value))
  }

  test("Genetic Algorithm Finds a Random Number") {
    val hiddenNumber = 42.42
    val computed = GeneticAlgorithm.geneticAlgorithm(SingleValue.incubator, SingleValue.costFunction(hiddenNumber), 1)
    val difference = (hiddenNumber - computed.value).abs
    println("Result: " + computed.value + " diference: " + difference)
    assert(equalDoubles(hiddenNumber, computed.value))
  }

  test("Genetic Algorithm Finds a Random Big Number") {
    val hiddenNumber = 4242.42
    val computed = GeneticAlgorithm.geneticAlgorithm(SingleValue.incubator, SingleValue.costFunction(hiddenNumber), 1)
    val difference = (hiddenNumber - computed.value).abs
    println("Result: " + computed.value + " diference: " + difference)
    assert(equalDoubles(hiddenNumber, computed.value))
  }

  test("Genetic Algorithm Finds a Random Negative Number") {
    val hiddenNumber = -374.22
    val computed = GeneticAlgorithm.geneticAlgorithm(SingleValue.incubator, SingleValue.costFunction(hiddenNumber), 1)
    val difference = (hiddenNumber - computed.value).abs
    println("Result: " + computed.value + " diference: " + difference)
    assert(equalDoubles(hiddenNumber, computed.value))
  }
}
