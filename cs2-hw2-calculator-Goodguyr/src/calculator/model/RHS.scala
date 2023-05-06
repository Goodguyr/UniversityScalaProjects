package calculator.model

class RHS(calculator: Calculator, previousNum:String, action: (Double, Double) => Double, firstNum:String = "") extends Displayed(calculator) {

  var num = new Number

  displayed += firstNum

  override def displayNumber(): Double = {
    displayed.toDouble
  }

  override def equalPressed(): Unit = {
    var actionNum = displayed
    var result:String = action(previousNum.toDouble, displayed.toDouble).toString
    calculator.calculator = new EqualAlreadyPressed(calculator, actionNum, result, action)
  }

  override def clearInput(): Unit = {
    calculator.calculator = new LHS(calculator)
  }

  override def decimalPressed(): Unit =  displayed = num.enterDecimal(displayed)

  override def addition():Unit = {
    var add = (x:Double, y:Double) =>  x + y
    var result = action(previousNum.toDouble, displayed.toDouble).toString
    calculator.calculator = new CheckForInput(calculator, result, add)
  }

  override def subtract():Unit = {
    var sub = (x:Double, y:Double) =>  x - y
    var result = action(previousNum.toDouble, displayed.toDouble).toString
    calculator.calculator = new CheckForInput(calculator, result, sub)
  }

  override def multiply():Unit = {
    var multi = (x:Double, y:Double) =>  x * y
    var result:String = action(previousNum.toDouble, displayed.toDouble).toString
    calculator.calculator = new CheckForInput(calculator, result, multi)
  }

  override def division():Unit = {
    var div = (x:Double, y:Double) =>  x / y
    var result:String = action(previousNum.toDouble, displayed.toDouble).toString
    calculator.calculator = new CheckForInput(calculator, result, div)
  }

  override def numberAction(number: Int): Unit = displayed += number.toString
}