package tests

import calculator.controller._
import calculator.model.Calculator
import org.scalatest.FunSuite

class TestFourFunctions extends FunSuite {

 test("Multiplication Test"){
   val calculator = new Calculator

   new NumberAction(calculator, 2).handle(null)
   new NumberAction(calculator, 5).handle(null)
   new MultiplicationAction(calculator).handle(null)
   new NumberAction(calculator, 4).handle(null)
   new EqualAction(calculator).handle(null)

   assert(calculator.displayNumber() == 100.0)
 }

  test("Division Test"){
    val calculator = new Calculator

    new NumberAction(calculator, 5).handle(null)
    new NumberAction(calculator, 5).handle(null)
    new DivisionAction(calculator).handle(null)
    new NumberAction(calculator, 10).handle(null)
    new EqualAction(calculator).handle(null)

    assert(calculator.displayNumber() == 5.5)
  }

  test("Subtraction Test"){
    val calculator = new Calculator

    new NumberAction(calculator, 1).handle(null)
    new NumberAction(calculator, 5).handle(null)
    new SubtractionAction(calculator).handle(null)
    new NumberAction(calculator, 2).handle(null)
    new NumberAction(calculator, 5).handle(null)
    new EqualAction(calculator).handle(null)

    assert(calculator.displayNumber() == -10.0)
  }

  test("Addition Test"){
    val calculator = new Calculator

    new NumberAction(calculator, 6).handle(null)
    new NumberAction(calculator, 4).handle(null)
    new AdditionAction(calculator).handle(null)
    new NumberAction(calculator, 6).handle(null)
    new EqualAction(calculator).handle(null)

    assert(calculator.displayNumber() == 70.0)
  }
}
