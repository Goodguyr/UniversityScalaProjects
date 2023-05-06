package a.tests

import a.src.StringSplitter
import org.scalatest._

class TestA extends FunSuite {

  test("Testing for Objective A") {

    var testCases : Map[String, Int] = Map()
    testCases += ("true;true;true" -> 100)
    testCases += ("false;false;false" -> 0)
    testCases += ("true;false;true;true;true" -> 80)
    testCases += ("false;false;true;false" -> 25)
    testCases += ("true;false" -> 50)
    testCases += ("true;false;true;false" -> 50)

    for((input, expectedOutput) <- testCases){
      assert(StringSplitter.computePercentTrue(input) == expectedOutput)
    }

  }

}
