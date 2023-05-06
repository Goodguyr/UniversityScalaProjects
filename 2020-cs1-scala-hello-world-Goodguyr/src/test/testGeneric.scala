import functions.Generics
import org.scalatest.FunSuite

class testGeneric extends FunSuite {
  var inputList: List[Int] = List(4, 3, 5, 7, 3)
  var outputList: List[String] = List()
  var compare: (Int) => String = (n: Int) => n.toString
  var answer:List[String] = Generics.genericMethod(inputList, compare)

  assert(answer == List("4", "3", "5", "7", "3"))
}
