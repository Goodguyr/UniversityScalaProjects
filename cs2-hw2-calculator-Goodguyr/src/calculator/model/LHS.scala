package calculator.model

class LHS(calculator: Calculator) extends Displayed(calculator) {

  var num = new Number

  override def displayNumber():Double = displayed.toDouble

  override def clearInput():Unit = {
    num.state = new NonDecimal(num)
    displayed = "0"
  }

  override def decimalPressed():Unit = {
    displayed = num.enterDecimal(displayed)
  }

  override def addition():Unit = {
    val add = (x:Double, y:Double) =>  x + y
    calculator.calculator = new CheckForInput(calculator, displayed, add)
  }

  override def subtract():Unit = {
    val sub = (x:Double, y:Double) =>  x - y
    calculator.calculator = new CheckForInput(calculator, displayed, sub)
  }

  override def multiply():Unit = {
    val multi = (x:Double, y:Double) =>  x * y
    calculator.calculator = new CheckForInput(calculator, displayed, multi)
  }

  override def division():Unit = {
    val div = (x:Double, y:Double) =>  x / y
    calculator.calculator = new CheckForInput(calculator, displayed, div)
  }

  override def numberAction(number:Int):Unit = displayed += number.toString
}
