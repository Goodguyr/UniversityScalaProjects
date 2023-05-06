package BattleGUI

import CharacterTypes.{Mage, Warrior}
import play.api.libs.json.Json
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

object View extends JFXApp {
  var char1 = new Warrior()
  char1.name = "Harry"
  var char2 = new Mage()
  char2.name = "John"
  var char3 = new Warrior()
  char3.name = "David"
  var char4 = new Mage()
  char4.name = "Eric"
  var party1 = new Party(List(char1, char2, char3))
  var party2 = new Party(List(char4, char2))

  var ActionRect:Rectangle = null


  var model = new Model
  this.stage = new JFXApp.PrimaryStage {
    title.value = "Battle GUI"
    scene = new Scene(1400,1100){
      fill = Green
      this.content = model.update(model.partyData(party1, party2))
    }
  }
}
