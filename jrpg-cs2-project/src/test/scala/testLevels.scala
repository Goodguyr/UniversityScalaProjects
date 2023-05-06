package scala

import CharacterTypes.Warrior
import org.scalatest._

class testLevels extends FunSuite{
  var character = new Warrior()
  var level:Int = character.currentLevel
  assert(level == 1)
  assert(character.currentXp == 0)
  character.addXp()
  assert(character.currentXp == 5)

  assert(character.nextXp(2) == 200)
  assert(character.nextXp(5) == 2000)
}
