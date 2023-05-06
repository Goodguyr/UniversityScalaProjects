import fp.ReturnFunction
import org.scalatest.FunSuite

class StrangeAdditionTest extends FunSuite {
  val add5 = ReturnFunction.strangeAddition(5)
  val add8 = ReturnFunction.strangeAddition(8)
  val add0 = ReturnFunction.strangeAddition(0)

  assert(add5(10) == 15 && add8(25) == 33 && add0(1) == 1)
}
