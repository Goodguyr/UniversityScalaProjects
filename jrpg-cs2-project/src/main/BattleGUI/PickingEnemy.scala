package BattleGUI
import scalafx.scene.Group
import scalafx.scene.shape.Rectangle

class PickingEnemy(model: Model, action:String, actionTaker:String) extends TakeActionState(model) {
  override def takeTurn(actionName: String): Unit = {}

  override def pickPlayer(model:Model, action:String, actionTaker:String): Unit = {
    model.state = new PickingEnemy(model, action, actionTaker)
  }

  override def displayActionButtons(group: Group, actions: List[String], name: String, model: Model, memberRectangle:Rectangle): Unit = {
    println(actionTaker + " is using " + action + " on " + name)
    model.animate(actionTaker, name, 1)
    View.ActionRect.width = 70
    model.state = new PickingPlayer(model)
  }

}
