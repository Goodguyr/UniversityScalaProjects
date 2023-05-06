package calculator.model

abstract class Displayed(calculator: Calculator) {

  var displayed:String = "0"

  def displayNumber():Double

  def equalPressed(){}

  def clearInput()

  def decimalPressed()

  def addition()

  def subtract()

  def multiply()

  def division()

  def numberAction(number:Int)
}
