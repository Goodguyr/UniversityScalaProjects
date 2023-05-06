package platformer

abstract class State(player: Player) {

  def duck()

  def standStill()

  def run()

  def jumpHeight():Int

  def movementSpeed():Int
}
