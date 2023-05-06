package Tests

import org.scalatest._
import physics.PhysicsVector
import physics.objects.DynamicObject
import physics.PhysicsEngine.updateObject

class TestObjectUpdate extends FunSuite {

  test("Test dynamic object movement"){
    val vect1:PhysicsVector = new PhysicsVector(2.0, 0, 11.0)
    val vect2:PhysicsVector = new PhysicsVector(2.0,0,2.0)
    val dynamicObj:DynamicObject = new DynamicObject(vect1, vect2)
    val gravity:Double = 10.0
    var updateTime:Double = 1.0

    updateObject(dynamicObj, updateTime, gravity)
    assert(dynamicObj.location.z == 1.0)

    val vect3:PhysicsVector = new PhysicsVector(2.0, 0, 53.5)
    val vect4:PhysicsVector = new PhysicsVector(2.0,0,2.0)
    val dynamicObj2:DynamicObject = new DynamicObject(vect3, vect4)
    updateTime = 2.0
    updateObject(dynamicObj2, updateTime, gravity)
    assert(dynamicObj2.location.z == 13.5)

    val vect5:PhysicsVector = new PhysicsVector(2.0, 0, 237.0)
    val vect6:PhysicsVector = new PhysicsVector(2.0,0,2.0)
    val dynamicObj3:DynamicObject = new DynamicObject(vect5, vect6)
    updateTime = 4.0
    updateObject(dynamicObj3, updateTime, gravity)
    assert(dynamicObj3.location.z == 77.0)
  }
}
