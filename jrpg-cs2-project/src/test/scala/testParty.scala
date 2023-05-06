package scala

import CharacterTypes.Warrior
import org.scalatest._

class testParty extends FunSuite{

  var char1 = new Warrior()
  var char2 = new Warrior()
  var char3 = new Warrior()
  var char4 = new Warrior()
  var party1 = new Party(List(char1, char2, char3))
  var party2 = new Party(List(char4, char2))

  char1.alive = false
  char4.alive = false
  char4.currentLevel = 150

  party1.defeated(party2)
  assert(char3.currentXp == 2250)

  char4.alive = true
  char2.currentXp = 0
  party1.defeated(party2)
  assert(char2.currentXp == 2250)
}
