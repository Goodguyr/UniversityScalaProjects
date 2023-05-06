package platformer

class Player{
  var state:State = new StandingPlayer(this)

  def duck():Unit = {
    this.state.duck()
  }

  def standStill():Unit = {
    this.state.standStill()
  }

  def run():Unit = {
    this.state.run()
  }

  def jumpHeight():Int = {
    this.state.jumpHeight()
  }

  def movementSpeed():Int = {
    this.state.movementSpeed()
  }
}
