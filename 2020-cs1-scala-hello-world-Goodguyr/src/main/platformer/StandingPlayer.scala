package platformer

class StandingPlayer(player:Player) extends State(player){
  override def jumpHeight():Int = 3

  override def movementSpeed(): Int = 5

  override def duck(): Unit = {
    player.state = new DuckingPlayer(player)
  }

  override def standStill(): Unit = {}

  override def run(): Unit = {
    player.state = new RunningPlayer(player)
  }
}
