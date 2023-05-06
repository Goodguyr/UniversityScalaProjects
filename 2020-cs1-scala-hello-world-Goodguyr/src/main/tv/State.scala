package tv

abstract class State(TV:TV) {

  var muted:Boolean = false

  var volume:Int = 5

  def volumeUp()

  def volumeDown()

  def mute()

  def power()

  def currentVolume(): Int
}
