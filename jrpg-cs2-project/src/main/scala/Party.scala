package scala

class Party(var members: List[Character]) {

  def defeated(enemy: Party): Unit = {
    var aliveMembers: List[Int] = List()
    var enemyLvl: Int = 0

    for(i <- enemy.members){
      enemyLvl += i.currentLevel
    }

    for(i <- members.indices){
      if(members(i).alive){
        aliveMembers = aliveMembers :+ i
      }
    }

    var rewardXp: Int = (enemyLvl * 30) / aliveMembers.length

    for(i <- aliveMembers){
      members(i).currentXp += rewardXp
    }
  }
}
