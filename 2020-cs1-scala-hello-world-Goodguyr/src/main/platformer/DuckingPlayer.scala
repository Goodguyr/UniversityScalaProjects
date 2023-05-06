package platformer

class DuckingPlayer(player: Player) extends State(player){
  override def jumpHeight():Int = 4

  override def movementSpeed(): Int = 1

  override def duck(): Unit = {}

  override def standStill(): Unit = {
    player.state = new StandingPlayer(player)
  }

  override def run(): Unit = {}
}
