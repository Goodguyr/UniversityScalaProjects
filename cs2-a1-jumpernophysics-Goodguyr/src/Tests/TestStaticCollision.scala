package Tests

import org.scalatest.FunSuite
import physics.PhysicsEngine._
import physics._
import physics.objects._

class TestStaticCollision extends FunSuite{

  var vect3:PhysicsVector = new PhysicsVector(2.0, 0.0, 5.0)
  var vect4:PhysicsVector = new PhysicsVector(5.0, 0.0, 1.0)
  var stat:StaticObject = new StaticObject(vect3, vect4)

  test("Test collision with bottom"){
    var vect1:PhysicsVector = new PhysicsVector(3.0, 0.0, 4.1)
    var vect2:PhysicsVector = new PhysicsVector(1.0, 0.0, 1.0)
    var dyn:DynamicObject = new DynamicObject(vect1, vect2)
    dyn.previousLocation.z = 3.0
    checkStaticCollision(stat, dyn)

    assert(stat.face == 0)
  }

  test("Test collision with top"){
    var vect1:PhysicsVector = new PhysicsVector(4.0, 0.0, 5.9)
    var vect2:PhysicsVector = new PhysicsVector(1.0, 0.0, 1.0)
    var dyn:DynamicObject = new DynamicObject(vect1, vect2)
    dyn.previousLocation.z = 8.0
    checkStaticCollision(stat, dyn)

    assert(stat.face == 1)
  }

  test("Test collision from left"){
    var vect1:PhysicsVector = new PhysicsVector(1.1, 0.0, 5.0)
    var vect2:PhysicsVector = new PhysicsVector(1.0, 0.0, 1.0)
    var dyn:DynamicObject = new DynamicObject(vect1, vect2)
    checkStaticCollision(stat, dyn)
    
    assert(stat.face == 3)
  }
  
  test("Test collision from right"){
    var vect1:PhysicsVector = new PhysicsVector(6.9, 0.0, 5.0)
    var vect2:PhysicsVector = new PhysicsVector(1.0, 0.0, 1.0)
    var dyn:DynamicObject = new DynamicObject(vect1, vect2)
    checkStaticCollision(stat, dyn)
    
    assert(stat.face == 2)
  }

  test("Test inner collision"){
    var vect1:PhysicsVector = new PhysicsVector(4.0 , 0.0, 4.5)
    var vect2:PhysicsVector = new PhysicsVector(1.0, 0.0, 1.0)
    var dyn:DynamicObject = new DynamicObject(vect1, vect2)
    dyn.previousLocation.z = 4.5
    dyn.previousLocation.x = 4.0
    checkStaticCollision(stat, dyn)
    
    assert(stat.face == 6)
  }
}