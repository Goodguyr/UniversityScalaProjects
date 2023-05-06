package calculator.model


class Calculator() {

  var calculator:Displayed = new LHS(this)

  // Accessed by View. You should edit this method as you build functionality
  def displayNumber(): Double = {
    this.calculator.displayNumber()
  }

  def equalPressed():Unit = {
    this.calculator.equalPressed()
  }

  def clearInput():Unit = {
    this.calculator.clearInput()
  }

  def decimalPressed():Unit = {
    this.calculator.decimalPressed()
  }

  def addition():Unit = {
    this.calculator.addition()
  }

  def subtract():Unit = {
    this.calculator.subtract()
  }

  def multiply():Unit = {
    this.calculator.multiply()
  }

  def division():Unit = {
    this.calculator.division()
  }

  def numberAction(number:Int):Unit = {
    this.calculator.numberAction(number)
  }
}
