package calculator.controller

import calculator.model.Calculator
import javafx.event.{ActionEvent, EventHandler}

/**
  * EventHandlers f0r each of the button on the calculator. NumberAction takes
  * an Int representing the which number button was pressed. Implement each of
  * these handle methods to call the appropriate method in your Calculator API.
  * You may assume that each of these classes has a reference to the same
  * Calculator object
  *
  * For testing use only these classes to ensure your tests will run with the
  * submissions on the server
  */

class EqualAction(calculator: Calculator) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    // Example: If you write a method in calculator named equalPressed() you would
    //          implement this method to call equalPressed() on the input calculator
    calculator.equalPressed()
  }
}

class ClearAction(calculator: Calculator) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    calculator.clearInput()
  }
}

class DecimalAction(calculator: Calculator) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    calculator.decimalPressed()
  }
}

class NumberAction(calculator: Calculator, number: Int) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    calculator.numberAction(number)
  }
}

class AdditionAction(calculator: Calculator) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    calculator.addition()
  }
}

class SubtractionAction(calculator: Calculator) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    calculator.subtract()
  }
}

class MultiplicationAction(calculator: Calculator) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    calculator.multiply()
  }
}

class DivisionAction(calculator: Calculator) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    calculator.division()
  }
}
