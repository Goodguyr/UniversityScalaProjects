package Model

class TV {
  var state:TVstate = new TVoff(this)

  val channelList:List[String] = List("Disney", "TV3", "Discovery", "BBC", "TV1")

  var volume = 0
  var channel = 0
  var currentChannel:String = channelList(0)

  def doWhatRemoteTellsMe(action:String):Unit = {
    action match {
      case "KEY_POWER" => powerButton
      case "KEY_CHUP" => nextChannel
      case "KEY_CHDOWN" => prevChannel
      case "KEY_VOLUP" => volumeUp
      case "KEY_VOLDOWN" => volumeDown
    }
  }

  def powerButton:Unit = {
    state.powerButton
  }

  def volumeUp:Unit = {
    state.volumeUp
  }

  def volumeDown:Unit = {
    state.volumeDown
  }

  def nextChannel:Unit = {
    state.nextChannel
  }

  def prevChannel:Unit = {
    state.prevChannel
  }

}
