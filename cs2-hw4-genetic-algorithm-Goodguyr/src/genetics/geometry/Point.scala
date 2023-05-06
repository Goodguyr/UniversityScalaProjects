package genetics.geometry

class Point(var x: Double, var y: Double) {

  //distance + x and y squared

  override def toString: String = {
    f"($x%1.3f, $y%1.3f)"
  }

}
