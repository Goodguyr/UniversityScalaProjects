package scala

import CharacterTypes.Warrior
import org.scalatest._

class testExperience extends FunSuite{

  var character = new Warrior()
  character.currentXp = 250
  character.checkLevel()
  assert(character.currentLevel == 2)

  character.currentXp = 2700

  character.checkLevel()
  assert(character.currentLevel == 5)
}
