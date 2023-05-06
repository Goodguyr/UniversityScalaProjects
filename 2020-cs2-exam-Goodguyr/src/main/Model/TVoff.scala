package Model

class TVoff(tv:TV) extends TVstate(tv) {
  override var on: Boolean = false

  override def powerButton: Unit = {
    tv.state = new TVon(tv)
  }

  override def volumeUp: Unit = {
    //Send "Fail" message
  }

  override def volumeDown: Unit = {
    //Send "Fail" message
  }

  override def nextChannel: Unit = {
    //Send "Fail" message
  }

  override def prevChannel: Unit = {
    //Send "Fail" message
  }

  override def updateChannel: Unit = {
    //Send "Fail" message
  }
}
