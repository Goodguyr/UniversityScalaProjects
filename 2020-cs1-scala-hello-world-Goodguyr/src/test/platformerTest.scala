import org.scalatest._
import platformer.Player

class platformerTest extends FunSuite{

  var player = new Player()
  assert(player.movementSpeed() == 5 && player.jumpHeight() == 3, "Testing initial state")

  player.standStill()
  assert(player.movementSpeed() == 5 && player.jumpHeight() == 3, "Testing standing state")

  player.duck()
  assert(player.movementSpeed() == 1 && player.jumpHeight() == 4, "Testing ducking state")

  player.run()
  assert(player.movementSpeed() == 1 && player.jumpHeight() == 4, "Testing ducking to running")

  player.standStill()
  player.run()
  assert(player.movementSpeed() == 12 && player.jumpHeight() == 6)
}
