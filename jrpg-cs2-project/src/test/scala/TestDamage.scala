package scala

import CharacterTypes.{Mage, Warrior}
import org.scalatest._

class TestDamage extends FunSuite{

  test("Testing damage") {
    var testPlayer = new Warrior()
    testPlayer.takeDamage(10)
    assert(testPlayer.currentHealth == 90, "Failed, health is not 90")
    assert(testPlayer.alive)
    testPlayer.takeDamage(100)
    assert(testPlayer.currentHealth == 0)
    assert(!testPlayer.alive)
  }

  test("Testing attacks"){
    var player1 = new Warrior()
    var player2 = new Mage()
    player1.physicalStrike(player2)
    assert(player2.currentHealth == 50)
    player2.magicAttack(player1)
    assert(player1.currentHealth == 50)
    assert(player2.currentMana == 145)
    var player3 = new Mage()
    player3.magicAttack(player2)
    assert(player2.currentHealth == 30)
    var player4 = new Mage()
    player4.magicAttack(player2)
    assert(player2.currentHealth == 10)
  }
}


