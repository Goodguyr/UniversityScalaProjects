package calculator.model

class Decimal(num:Number) extends NumState(num){

  override def enterDecimal(displayed: String): String = displayed
}
