package Model

abstract class TVstate(TV:TV) {
  var on:Boolean

  def updateChannel

  def powerButton

  def volumeUp

  def volumeDown

  def nextChannel

  def prevChannel
}
