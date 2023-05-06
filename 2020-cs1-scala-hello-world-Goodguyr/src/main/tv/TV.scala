package tv

class TV() {

  var state:State = new TVoff(this)

  def volumeUp(): Unit = {
    this.state.volumeUp()
  }

  def volumeDown(): Unit = {
    this.state.volumeDown()
  }

  def mute(): Unit = {
    this.state.mute()
  }

  def power(): Unit = {
    this.state.power()
  }

  def currentVolume(): Int = {
    this.state.currentVolume()
  }
}
