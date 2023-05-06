package tests

import genetics.GeneticAlgorithm
import genetics.aimbot.{AimBot, PhysicsVector}
import org.scalatest.FunSuite

class TestAimBot extends FunSuite {

  val EPSILON: Double = 0.05

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }

}