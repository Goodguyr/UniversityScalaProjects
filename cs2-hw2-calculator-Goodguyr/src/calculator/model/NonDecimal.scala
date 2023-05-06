package calculator.model

class NonDecimal(num:Number) extends NumState (num){

  override def enterDecimal(displayed:String):String = {
    var result = displayed + "."
    num.state = new Decimal(num)
    result
  }
}

