package BattleGUI
import javafx.collections.ObservableList
import scalafx.beans.Observable
import scalafx.scene
import scalafx.scene.{Group, Node}
import scalafx.scene.control.Button
import scalafx.scene.shape.Rectangle

class PickingPlayer(model:Model) extends TakeActionState (model){
  override def takeTurn(actionName: String): Unit = {}

  override def pickPlayer(model:Model, action:String, actionTaker:String):Unit = {}

  override def displayActionButtons(group: Group, actions: List[String], name: String, model: Model, memberRectangle:Rectangle): Unit = {
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
        id = "ActionButtons"
        text = action
        onAction = new ChoosePlayer(model, action, name)
        actionCount += 1
      }
      group.children.add(button)
    }
  }

}
