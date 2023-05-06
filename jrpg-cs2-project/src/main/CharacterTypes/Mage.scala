package CharacterTypes

class Mage extends Character(attackPower = 0, defense = 10, magicAttackPower = 60, magicDefense = 40, maxHealth = 100, maxMana = 150) {

  var availableActions:List[String] = List("Magic Attack", "Heal")

  override def levelUp(): Unit = {
    this.currentLevel += 1
    maxHealth += 20
    magicAttackPower += 5
    magicDefense += 3
    defense += 2
  }

  override def physicalStrike(enemy: Character): Unit = {}

  def ultimate(enemy:Character): Unit = {
    var oldStat = enemy.defense
    enemy.magicDefense /= 2
    magicAttack(enemy)
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

      if(action == "Magic Attack"){
        magicAttack(character)
      }

      if(action == "Ultimate"){
        ultimate(character)
      }
    }
  }

}
