package platformer

class RunningPlayer(player: Player) extends State(player){

  override def jumpHeight():Int = 6

  override def movementSpeed(): Int = 12

  override def duck(): Unit = {}

  override def standStill(): Unit = {
    player.state = new StandingPlayer(player)
  }

  override def run(): Unit = {}
}
