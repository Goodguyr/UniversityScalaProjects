import org.scalatest.FunSuite
import week12.BinarySearchTree

class TestSearchTree extends FunSuite {

  test("Test"){

    val compare = (a: Int, b: Int) => a < b
    val binaryTree = new BinarySearchTree[Int](compare)
    binaryTree.insert(5)
    binaryTree.insert(4)
    binaryTree.insert(6)
    binaryTree.insert(3)
    binaryTree.insert(7)
    binaryTree.insert(2)
    binaryTree.insert(8)
    binaryTree.insert(1)

    assert(binaryTree.toList == List(1,2,3,4,5,6,7,8))
  }
}
