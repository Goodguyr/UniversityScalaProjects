package tv

class TVoff(TV:TV) extends State(TV){

  volume = 0

  muted = false

  override def volumeUp(){}

  override def volumeDown(){}

  override def mute(){}

  override def power(): Unit ={
    TV.state = new TVon(TV)
  }

  override def currentVolume(): Int = {volume}
}
