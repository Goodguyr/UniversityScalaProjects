package CharacterTypes

class Warrior extends Character(attackPower = 60, defense = 40, magicAttackPower = 0, magicDefense = 10, maxHealth = 100, maxMana = 0) {

  var availableActions:List[String] = List("Physical Strike", "Heal")

  override def levelUp(): Unit = {
    this.currentLevel += 1
    maxHealth += 25
    attackPower += 5
    defense += 3
    magicDefense += 2
  }

  override def magicAttack(enemy: Character): Unit = {}

  def ultimate(enemy:Character): Unit = {
    var oldStat = enemy.defense
    enemy.defense /= 2
    physicalStrike(enemy)
    enemy.defense = oldStat
  }

  def heal(character: Character):Unit ={
    if (currentHealth * 1.1 < character.maxHealth){
      currentHealth += (currentHealth * 0.1).toInt
    }
    else{
      currentHealth = character.maxHealth
    }
  }

  override def battleOptions(): List[String] = {
    if(currentLevel > 14){
      availableActions = availableActions :+ "Ultimate"
    }
    availableActions
  }

  override def takeAction(action: String, character: Character): Unit = {
    if (availableActions.contains(action)){
      if(action == "Heal"){
        heal(character)
      }

      if(action == "Physical Strike"){
        physicalStrike(character)
      }

      if(action == "Ultimate"){
        ultimate(character)
      }
    }
  }

}
