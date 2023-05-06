package BattleGUI

import scalafx.scene.Group
import scalafx.scene.shape.Rectangle

abstract class TakeActionState(model: Model) {

  def takeTurn(actionName: String)

  def pickPlayer(model:Model, action:String, actionTaker:String)

  def displayActionButtons(group: Group, actions:List[String], name:String, model: Model, memberRectangle:Rectangle)

}
