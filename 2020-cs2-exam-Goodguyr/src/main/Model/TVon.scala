package Model

class TVon(tv:TV) extends TVstate(tv) {
  override var on: Boolean = true

  override def powerButton: Unit = {
    tv.state = new TVoff(tv)
  }

  override def volumeUp: Unit = tv.volume += 1

  override def volumeDown: Unit = {
    if(tv.volume == 0) println("Stop it, there is no sound already!")
    else tv.volume -= 1
  }

  override def nextChannel: Unit = {
    tv.channel += 1
    updateChannel
  }

  override def prevChannel: Unit = {
    if(tv.channel == 0){
      tv.channel += tv.channelList.length
    }
    else tv.channel -= 1
    updateChannel
  }

  override def updateChannel: Unit = {
    tv.currentChannel = tv.channelList(tv.channel % tv.channelList.length)
  }
}
