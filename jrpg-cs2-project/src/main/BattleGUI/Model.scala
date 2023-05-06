package BattleGUI

import javafx.event.{ActionEvent, EventHandler}
import play.api.libs.json._
import scalafx.scene.Group
import scalafx.scene.control.Button
import scalafx.scene.effect.BlendMode.{Green, Red}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.Text
import scalafx.Includes._

class Model () {

  var state:TakeActionState = new PickingPlayer(this)


  def partyData(party1: Party, party2: Party):JsValue = {
    var party1Characters:Array[Map[String, JsValue]] = Array()
    var party2Characters:Array[Map[String, JsValue]] = Array()

    for(member <- party1.members){
      var memberInfo:Map[String, JsValue] = Map(
        "name" -> Json.toJson(member.name),
        "hp" -> Json.toJson(member.currentHealth),
        "maxHp" -> Json.toJson(member.maxHealth),
        "battleOptions" -> Json.toJson(member.battleOptions())
      )
      party1Characters = party1Characters :+ memberInfo
    }

    for(member <- party2.members){
      var memberInfo:Map[String, JsValue] = Map(
        "name" -> Json.toJson(member.name),
        "hp" -> Json.toJson(member.currentHealth),
        "maxHp" -> Json.toJson(member.maxHealth),
        "battleOptions" -> Json.toJson(member.battleOptions())
      )
      party2Characters = party2Characters :+ memberInfo
    }

    val response:Map[String, JsValue] = Map(
      "playerParty" -> Json.toJson(Map("characters" -> Json.toJson(party1Characters))),
      "enemyParty" -> Json.toJson(Map("characters" -> Json.toJson(party2Characters))))
    Json.toJson(response)
  }

  def update(data:JsValue):List[Group] = {

    var partyGroup:Group = new Group{}
    var enemyGroup:Group = new Group{}

    var playerPartyCharacters = (data \ "playerParty" \ "characters").as[Array[Map[String, JsValue]]]
    var enemyPartyCharacters = (data \ "enemyParty" \ "characters").as[Array[Map[String, JsValue]]]

    var memberCount:Int = 1

    for(member <- playerPartyCharacters){
      var name = member("name").as[String]
      var hp = member("hp").as[Int]
      var maxHp = member("maxHp").as[Int]
      var battleOptions = member("battleOptions").as[List[String]]

      val memberRectangle = new Rectangle {
        translateX = 250 * memberCount
        translateY = 450 + 40 * memberCount
        width = 70
        height = 70
        fill = Color.Blue
      }

      val nameButton: Button = new Button {
        translateX = 250 * memberCount
        translateY = 450 + 40 * memberCount + 100
        style = "-fx-font: 28 ariel;"
        text = name
        onAction = new DisplayActionButtons(partyGroup, battleOptions, name, Model.this, memberRectangle)
      }

      if(hp < 1){
        memberRectangle.fill = Color.Gray
      }

      memberCount += 1
      partyGroup.children.add(memberRectangle)
      partyGroup.children.add(nameButton)
    }

    memberCount = 1

    for(member <- enemyPartyCharacters){
      var name = member("name").as[String]
      var text = new Text("ass")
      var hp = member("hp").as[Int]
      var maxHp = member("maxHp").as[Int]
      var battleOptions = member("battleOptions").as[List[String]]

      val memberRectangle = new Rectangle {
        translateX = 250 * memberCount
        translateY = 100 + 40 * memberCount
        width = 70
        height = 70
        fill = Color.Red
      }

      val nameButton: Button = new Button {
        translateX = 250 * memberCount
        translateY = 100 + 40 * memberCount + 100
        style = "-fx-font: 28 ariel;"
        text = name
        id = name
        //textFill = Color.BlueViolet
        onAction = new DisplayActionButtons(partyGroup, battleOptions, name, Model.this, memberRectangle)
      }

      if(hp < 1){
        memberRectangle.fill = Color.Gray
      }
      memberCount += 1
      enemyGroup.children.add(memberRectangle)
      enemyGroup.children.add(nameButton)
    }

    List(enemyGroup, partyGroup)
  }

    def animate(playerName:String, attackedName:String, damageAmount:Int):Unit = {

      if (damageAmount > 0){

      }
      else if(damageAmount < 0){

      }
      else{

      }
    }
}
