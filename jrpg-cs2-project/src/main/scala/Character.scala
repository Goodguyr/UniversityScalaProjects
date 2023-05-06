package scala

abstract class Character(var attackPower : Int = 30, var defense : Int = 20, var magicAttackPower : Int = 30, var magicDefense : Int = 20, var maxHealth : Int = 100, var maxMana : Int = 100, var level: Int = 1, var xp: Int = 0, var name:String = "") {

  var alive: Boolean = true
  var currentHealth:Int = maxHealth
  var currentMana:Int = maxMana
  var currentLevel:Int= level
  var currentXp:Int = xp

  def takeDamage(damageReceived: Int): Unit = {
    currentHealth -= damageReceived
    if (currentHealth < 0) {
      currentHealth = 0
      alive = false
    }
  }

  def addXp(): Unit = {
    this.currentXp += 5
    checkLevel()
  }

  def physicalStrike(enemy: Character): Unit = {
    this.addXp()
    var damage: Int = attackPower - enemy.defense
    if (damage < 0) {
      damage = 0
    }
    enemy.takeDamage(damage)
  }

  def magicAttack(enemy: Character): Unit = {
    addXp()
    if (currentMana > 0) {
      var damage: Int = magicAttackPower - enemy.magicDefense
      if (damage < 0) {
        damage = 0
      }
      currentMana -= 5
      enemy.takeDamage(damage)
    }
  }

  def levelUp(): Unit = {
    this.currentLevel += 1
  }

  def defeatCharacter(enemy: Character): Unit = {
    currentXp += enemy.currentLevel * 30
    checkLevel()
  }

  def nextXp(levelTest:Int):Int = {
    (100 * math.pow(levelTest.toDouble, 2.0) - 100 * levelTest).toInt
  }

  def checkLevel():Unit ={
    var levelUpCount = 0
    if((currentXp > nextXp(currentLevel+1)) && (currentXp < nextXp(currentLevel+2))){
      levelUp()
    }

    else if(currentXp > nextXp(currentLevel+2)){
      for(i <- currentLevel until 100){
        if(nextXp(i) > currentXp && currentXp > nextXp(i-1)){
          levelUpCount = i
        }
      }
    }
    for(i <- 1 until levelUpCount - currentLevel){
      levelUp()
    }
  }

  def battleOptions():List[String]

  def takeAction(action:String, character: Character):Unit
}








