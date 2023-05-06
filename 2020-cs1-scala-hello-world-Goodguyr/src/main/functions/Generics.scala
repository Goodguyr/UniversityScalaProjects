package functions

object Generics {

  def genericMethod[T](inputData: List[Int], comparator: (Int) => T):List[T] = {
    var returnList:List[T] = List()
    for (i <- inputData){
      returnList :+= comparator(i)
    }
    returnList
  }
}
