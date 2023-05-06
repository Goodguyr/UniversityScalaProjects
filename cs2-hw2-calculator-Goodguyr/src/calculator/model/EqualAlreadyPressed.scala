package calculator.model

class EqualAlreadyPressed(calculator: Calculator, previousNum:String, result:String, action: (Double, Double) => Double) extends Displayed(calculator) {

  var num = new Number

  displayed = result

  override def displayNumber(): Double = displayed.toDouble

  override def equalPressed(): Unit = {
    var result: String = action(displayed.toDouble, previousNum.toDouble).toString
    calculator.calculator = new EqualAlreadyPressed(calculator, previousNum, result, action)
  }

  override def clearInput(): Unit = calculator.calculator = new LHS(calculator)

  override def decimalPressed(): Unit = displayed = num.enterDecimal(displayed)

  override def addition(): Unit = {
    var add = (x: Double, y: Double) => x + y
    calculator.calculator = new CheckForInput(calculator, displayed, add)
  }

  override def subtract(): Unit = {
    var sub = (x: Double, y: Double) => x - y
    calculator.calculator = new CheckForInput(calculator, displayed, sub)
  }

  override def multiply(): Unit = {
    var multi = (x: Double, y: Double) => x * y
    calculator.calculator = new CheckForInput(calculator, displayed, multi)
  }

  override def division(): Unit = {
    var div = (x: Double, y: Double) => x / y
    calculator.calculator = new CheckForInput(calculator, displayed, div)
  }

  override def numberAction(number: Int): Unit = {
    calculator.calculator = new LHS(calculator)
    calculator.calculator.displayed += number.toString
  }
}