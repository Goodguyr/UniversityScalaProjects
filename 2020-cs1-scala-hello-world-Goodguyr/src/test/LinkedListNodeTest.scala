import org.scalatest.FunSuite
import week11.LinkedListNode

class LinkedListNodeTest extends FunSuite {
  test("Append test"){
    var myList: LinkedListNode[Int] = new LinkedListNode[Int](2, null)
    myList = new LinkedListNode[Int](1, myList)
    myList.append(3)
    myList.append(4)
    assert(myList.toString === "1, 2, 3, 4")
  }
}
