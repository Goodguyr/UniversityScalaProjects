package calculator.model

class CheckForInput(calculator: Calculator, previous:String, action:(Double, Double) => Double) extends Displayed (calculator){

  override def numberAction(number: Int): Unit = calculator.calculator = new RHS(calculator, previous, action, number.toString)

  override def displayNumber(): Double = displayed.toDouble

  override def clearInput(): Unit = calculator.calculator = new LHS(calculator)

  override def decimalPressed(): Unit = {
    calculator.calculator = new RHS(calculator, previous, action)
    calculator.calculator.decimalPressed()
  }

  override def addition(): Unit = {
    var add = (x:Double, y:Double) =>  x + y
    calculator.calculator = new CheckForInput(calculator, previous, add)
  }

  override def subtract(): Unit = {
    var sub = (x:Double, y:Double) =>  x - y
    calculator.calculator = new CheckForInput(calculator, previous, sub)
  }

  override def multiply(): Unit = {
    var multi = (x:Double, y:Double) =>  x * y
    calculator.calculator = new CheckForInput(calculator, previous, multi)
  }

  override def division(): Unit = {
    var div = (x:Double, y:Double) =>  x / y
    calculator.calculator = new CheckForInput(calculator, previous, div)
  }
}
