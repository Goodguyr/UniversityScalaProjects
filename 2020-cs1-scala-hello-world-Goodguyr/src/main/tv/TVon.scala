package tv

class TVon(TV:TV) extends State(TV) {

  muted = false

  override def volumeUp(): Unit = {
    if(volume < 10) {
      volume += 1
    }
  }

  override def volumeDown(): Unit = {
    if(volume > 0){
      volume -= 1
    }
  }

  override def mute(): Unit = {
    if(muted == true){
      muted = false
    }
    else {

      muted = true
    }
  }

  override def power(): Unit = TV.state = new TVoff(TV)

  override def currentVolume(): Int = {
    volume
  }
}
