package Tests

import org.scalatest._
import physics.PhysicsEngine.isCollision
import physics._
import physics.objects._

class TestCollisions extends FunSuite {

  test("Testing collisions"){
    val vect1:PhysicsVector = new PhysicsVector(2.0, 0, 2.0)
    val vect2:PhysicsVector = new PhysicsVector(2.0,0,2.0)
    val dyn:DynamicObject = new DynamicObject(vect1, vect2)

    val vect3:PhysicsVector = new PhysicsVector(3.9,0,2.0)
    val vect4:PhysicsVector = new PhysicsVector(5.0,0,1.0)
    val stat:StaticObject = new StaticObject(vect3, vect4)

    assert(isCollision(dyn, stat))

    val vect5:PhysicsVector = new PhysicsVector(2.0, 0, 2.0)
    val vect6:PhysicsVector = new PhysicsVector(2.0,0,2.0)
    val dyn2:DynamicObject = new DynamicObject(vect5, vect6)

    val vect7:PhysicsVector = new PhysicsVector(4.2,0,2.0)
    val vect8:PhysicsVector = new PhysicsVector(5.0,0,1.0)
    val stat2:StaticObject = new StaticObject(vect7, vect8)

    assert(!isCollision(dyn2, stat2))
  }
}
