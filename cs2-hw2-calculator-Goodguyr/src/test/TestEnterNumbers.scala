package tests

import calculator.controller._
import calculator.model.Calculator
import org.scalatest.FunSuite

class TestEnterNumbers extends FunSuite {

  val EPSILON: Double = 0.000001

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }

  // Example test case
  test("Test #1") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 2).handle(null)
    new NumberAction(calculator, 3).handle(null)
    new NumberAction(calculator, 4).handle(null)

    assert(equalDoubles(calculator.displayNumber(), 234.0))
  }

  test("Test #2") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 0).handle(null)
    new NumberAction(calculator, 0).handle(null)
    new NumberAction(calculator, 0).handle(null)
    new NumberAction(calculator, 1).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 5).handle(null)


    assert(equalDoubles(calculator.displayNumber(), 1.5))
  }

  test("Test #3") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 6).handle(null)
    new DecimalAction(calculator).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 7).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 8).handle(null)

    assert(equalDoubles(calculator.displayNumber(), 6.78))
  }

  test("Test #4") {
    val calculator: Calculator = new Calculator()

    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 9).handle(null)
    new NumberAction(calculator, 0).handle(null)
    new NumberAction(calculator, 2).handle(null)

    assert(equalDoubles(calculator.displayNumber(), 0.902))
  }
}
