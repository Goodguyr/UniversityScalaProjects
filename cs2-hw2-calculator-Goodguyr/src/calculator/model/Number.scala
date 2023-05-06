package calculator.model

class Number() {

  var state:NumState = new NonDecimal(this)

  def enterDecimal(number:String):String = {
    this.state.enterDecimal(number)
  }
}
