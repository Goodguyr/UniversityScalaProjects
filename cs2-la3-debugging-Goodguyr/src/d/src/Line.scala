package d.src

/**
  * Defines a line with slope and y-intercept given 2 points on the line
  * @param p1 A point on the line
  * @param p2 A point on the line != to p1
  */
class Line(p1: Point2D, p2: Point2D) {

  val slope: Double = slope(p1, p2)
  val yIntercept: Double = p1.y - p1.x * this.slope

  /**
    * Computes the slope between two points
    * @param p1 A point
    * @param p2 Another point
    * @return The slope between the two points
    */
  def slope(p1: Point2D, p2: Point2D): Double = {
    (p2.y - p1.y) / (p2.x - p1.x)// Should increase precision aka big double
  }

  /**
    * Checks if this line is parallel to another line
    * @param otherLine The line to check against
    * @return true if this line is parallel to the input line, false otherwise
    */
  def isParallel(otherLine: Line): Boolean = {
    this.slope == otherLine.slope
  }

}
