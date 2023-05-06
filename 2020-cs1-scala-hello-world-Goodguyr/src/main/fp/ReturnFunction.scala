package fp

object ReturnFunction {
  def strangeAddition(number:Int): Int => Int = {
    num:Int => {
      number + num
    }
  }
}
