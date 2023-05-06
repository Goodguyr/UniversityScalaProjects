package physics

import physics.objects.{DynamicObject, GameObject, StaticObject}

/**
  * Controls and computes the simulated physics for a game. Manages dynamic object movement, gravity, and
  * object collisions
  */
object PhysicsEngine {

  def isCollision(cube1: GameObject, cube2: GameObject):Boolean = {
    var width1 = cube1.location.x + cube1.dimensions.x
    var width2 = cube2.location.x + cube2.dimensions.x
    var height1 = cube1.location.z + cube1.dimensions.z
    var height2 = cube2.location.z + cube2.dimensions.z

    if (cube1.location.x < width2 && width1 > cube2.location.x && cube1.location.z < height2 && height1 > cube2.location.z){
      true
    }
    else false
  }

  def innerCollision(cube1: DynamicObject, cube2: GameObject):Boolean = {
    var width1 = cube1.previousLocation.x + cube1.dimensions.x
    var width2 = cube2.location.x + cube2.dimensions.x
    var height1 = cube1.previousLocation.z + cube1.dimensions.z
    var height2 = cube2.location.z + cube2.dimensions.z

    if (cube1.previousLocation.x < width2 && width1 > cube2.location.x && cube1.previousLocation.z < height2 && height1 > cube2.location.z){
      true
    }
    else false
  }

  def updateObject(obj1: DynamicObject, timeSinceUpdate: Double, gravity: Double): Unit = {
    obj1.velocity.z -= (gravity * timeSinceUpdate)

    obj1.previousLocation.z = obj1.location.z
    obj1.previousLocation.x = obj1.location.x
    obj1.location.z += (obj1.velocity.z * timeSinceUpdate)
    obj1.location.x += (obj1.velocity.x * timeSinceUpdate)

    if(obj1.location.z < 0.0) {
      obj1.location.z = 0.0
      obj1.velocity.z = 0.0
      obj1.onGround()
    }
  }

  def checkStaticCollision(staticObject: StaticObject, dynamicObject: DynamicObject): Unit = {
    if (isCollision(staticObject, dynamicObject)){
      var face: Int = 0

      if (innerCollision(dynamicObject, staticObject)) face = 6

      else if(dynamicObject.previousLocation.z >= staticObject.location.z + staticObject.dimensions.z) face = 1

      else if (dynamicObject.location.x + dynamicObject.dimensions.x > staticObject.location.x
        &&
        dynamicObject.location.x < staticObject.location.x) face = 3

      else if (dynamicObject.location.x < staticObject.location.x + staticObject.dimensions.x
        &&
        dynamicObject.location.x + dynamicObject.dimensions.x > staticObject.dimensions.x + staticObject.location.x) face = 2

      dynamicObject.collideWithStaticObject(staticObject)
      staticObject.collideWithDynamicObject(dynamicObject, face)
    }
  }

  def updateWorld(world: World, timeSinceUpdate: Double): Unit = {
    for (i <- world.dynamicObjects) {
      updateObject(i, timeSinceUpdate, world.gravity)
      for(g <- world.staticObjects) {
        checkStaticCollision(g, i)
      }
    }
  }
}

