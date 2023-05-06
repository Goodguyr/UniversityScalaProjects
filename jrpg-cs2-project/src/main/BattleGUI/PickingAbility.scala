package BattleGUI

import scalafx.scene.Group
import scalafx.scene.control.Button
import scalafx.scene.shape.Rectangle

class PickingAbility(model:Model) extends TakeActionState (model){
  override def takeTurn(actionName: String): Unit = {}

  override def pickPlayer(model:Model, action:String, actionTaker:String): Unit = {
    model.state = new PickingEnemy(model, action, actionTaker)
  }

  override def displayActionButtons(group: Group, actions:List[String], name:String, model: Model, memberRectangle:Rectangle): Unit = {
    var actionCount = 1

    if (View.ActionRect == null){
      View.ActionRect = memberRectangle
      View.ActionRect.width = 100
    }
    else{
      View.ActionRect.width = 70
      View.ActionRect = memberRectangle
      View.ActionRect.width = 100
      View.stage.show()
    }

    model.state = new PickingAbility(model)

    for(action <- actions){
      var button = new Button{
        translateX = 100
        translateY = 800 + 80 * actionCount
        style = "-fx-font: 28 ariel;"
        text = action
        onAction = new ChoosePlayer(model, action, name)
        actionCount += 1
      }
      group.children.add(button)
    }
  }

}
