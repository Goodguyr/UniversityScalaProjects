package d.tests

import d.src.{Line, Point2D}
import org.scalatest._

class TestD extends FunSuite {

  test("Test case 1 for Objective D") {

    val p1: Point2D = new Point2D(4.0, 4.0)
    val p2: Point2D = new Point2D(0.0, 0.0)

    val line: Line = new Line(p1, p2)

    assert(line.slope == 1.0)
    assert(line.yIntercept == 0.0)
  }

  test("Test case 2 for Objective D") {

    val p1: Point2D = new Point2D(2.0, 4.0)
    val p2: Point2D = new Point2D(2.0, 0.0)

    val line: Line = new Line(p1, p2)

    assert(line.slope == Double.PositiveInfinity || line.slope == Double.NegativeInfinity)
    assert(line.yIntercept == Double.PositiveInfinity || line.yIntercept == Double.NegativeInfinity)
  }

  test("Test case 3 for Objective D") {

    val p1: Point2D = new Point2D(5.0, 3.0)
    val p2: Point2D = new Point2D(2.0, 3.0)

    val line: Line = new Line(p1, p2)

    assert(line.slope == 0.0)
    assert(line.yIntercept == 3.0)
  }

  test("Test case 4 for Objective D") {

    val p1: Point2D = new Point2D(1.0, 1.0)
    val p2: Point2D = new Point2D(2.0, 2.0)

    val line1: Line = new Line(p1, p2)

    val p3: Point2D = new Point2D(5.0, 10.0)
    val p4: Point2D = new Point2D(6.0, 11.0)

    val line2: Line = new Line(p3, p4)

    assert(line1.isParallel(line2))
  }

  test("Test case 5 for Objective D") {

    val p1: Point2D = new Point2D(1.0, 3.0)
    val p2: Point2D = new Point2D(2.0, 3.3)

    val line1: Line = new Line(p1, p2)

    val p3: Point2D = new Point2D(5.0, 4.0)
    val p4: Point2D = new Point2D(10.0, 5.5)

    val line2: Line = new Line(p3, p4)

    assert(line1.isParallel(line2))
  }

  test("Test case 6 for Objective D") {

    val p1: Point2D = new Point2D(1.0, 3.0)
    val p2: Point2D = new Point2D(2.0, 3.0)

    val line1: Line = new Line(p1, p2)

    val p3: Point2D = new Point2D(1.0, 4.0)
    val p4: Point2D = new Point2D(2.0, 4.0)

    val line2: Line = new Line(p3, p4)

    assert(line1.isParallel(line2))
  }


  test("Test case 7 for Objective D") {

    val p1: Point2D = new Point2D(1.0, 3.0)
    val p2: Point2D = new Point2D(2.0, 4.0)

    val line1: Line = new Line(p1, p2)

    val p3: Point2D = new Point2D(1.0, -3.0)
    val p4: Point2D = new Point2D(2.0, -4.0)

    val line2: Line = new Line(p3, p4)

    assert(!line1.isParallel(line2))
  }

}
