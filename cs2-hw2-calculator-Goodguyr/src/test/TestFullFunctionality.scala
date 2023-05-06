package tests

import calculator.controller._
import calculator.model.Calculator
import org.scalatest.FunSuite

class TestFullFunctionality extends FunSuite {

  test("Multiple '=' Test"){
    var calculator = new Calculator

    new NumberAction(calculator, 1).handle(null)
    new NumberAction(calculator, 0).handle(null)
    new SubtractionAction(calculator).handle(null)
    new NumberAction(calculator, 2).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 5).handle(null)
    new EqualAction(calculator).handle(null)
    new EqualAction(calculator).handle(null)
    new EqualAction(calculator).handle(null)

    assert(calculator.displayNumber() == 2.5)

    new ClearAction(calculator).handle(null)

    new NumberAction(calculator, 6).handle(null)
    new NumberAction(calculator, 4).handle(null)
    new DivisionAction(calculator).handle(null)
    new NumberAction(calculator, 2).handle(null)
    new EqualAction(calculator).handle(null)
    new EqualAction(calculator).handle(null)
    new EqualAction(calculator).handle(null)

    assert(calculator.displayNumber() == 8)
  }

  test("Multiple function button presses"){
    var calculator = new Calculator

    new NumberAction(calculator, 1).handle(null)
    new NumberAction(calculator, 0).handle(null)
    new DivisionAction(calculator).handle(null)
    new MultiplicationAction(calculator).handle(null)
    new DivisionAction(calculator).handle(null)
    new AdditionAction(calculator).handle(null)
    new AdditionAction(calculator).handle(null)
    new SubtractionAction(calculator).handle(null)
    new NumberAction(calculator, 3).handle(null)
    new EqualAction(calculator).handle(null)

    assert(calculator.displayNumber() == 7)

    new ClearAction(calculator).handle(null)

    new NumberAction(calculator, 5).handle(null)
    new DecimalAction(calculator).handle(null)
    new DecimalAction(calculator).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 5).handle(null)
    new DivisionAction(calculator).handle(null)
    new AdditionAction(calculator).handle(null)
    new AdditionAction(calculator).handle(null)
    new SubtractionAction(calculator).handle(null)
    new MultiplicationAction(calculator).handle(null)
    new NumberAction(calculator, 1).handle(null)
    new NumberAction(calculator, 0).handle(null)
    new DivisionAction(calculator).handle(null)
    new AdditionAction(calculator).handle(null)
    new AdditionAction(calculator).handle(null)
    new SubtractionAction(calculator).handle(null)
    new NumberAction(calculator, 5).handle(null)
    new EqualAction(calculator).handle(null)
    new EqualAction(calculator).handle(null)

    assert(calculator.displayNumber() == 45)
  }
}
