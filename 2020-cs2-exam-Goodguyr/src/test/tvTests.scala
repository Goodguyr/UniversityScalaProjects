package test

import Model.{Server, TV}
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, FunSuite, Matchers, WordSpecLike}

class tvTests extends FunSuite{
  test("test states"){
    var tv = new TV
    assert(!tv.state.on)

    tv.doWhatRemoteTellsMe("KEY_POWER")

    assert(tv.state.on)

    tv.doWhatRemoteTellsMe("KEY_VOLUP")
    assert(tv.volume == 1)

    assert(tv.currentChannel == "Disney")

    tv.doWhatRemoteTellsMe("KEY_CHUP")
    assert(tv.currentChannel == "TV3")

    tv.doWhatRemoteTellsMe("KEY_VOLDOWN")
    tv.doWhatRemoteTellsMe("KEY_VOLDOWN")
    tv.doWhatRemoteTellsMe("KEY_VOLDOWN")
    assert(tv.volume == 0)

    tv.doWhatRemoteTellsMe("KEY_POWER")
    tv.doWhatRemoteTellsMe("KEY_VOLUP")
    tv.doWhatRemoteTellsMe("KEY_VOLUP")
    tv.doWhatRemoteTellsMe("KEY_VOLUP")
    assert(tv.volume == 0)
  }
}
