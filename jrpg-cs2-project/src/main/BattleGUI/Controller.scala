package BattleGUI

import javafx.event._
import scalafx.scene.Group
import scalafx.scene.control.Button
import scalafx.scene.shape.Rectangle

/**
A method named takeTurn(String) must be implemented which indicates the name
of a player character who is ready to take a turn. When this method is called,
the player must be given a way to choose one of that character’s battle options,
as well as a target character for that option. Once the user chooses an option and target,
call another method indicating the choices made (Later in the semester,
this method will send the choices to the server for processing.
For now, you can have this method print the choices to the screen/console for testing and for your demo).
While choosing the options, the player being controlled should be indicated in some way
 */




class TakeTurn(player:Character, action:String, model:Model) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    model.state.takeTurn(action)
  }
}

class ChoosePlayer(model:Model, action:String, actionTaker:String) extends EventHandler[ActionEvent] {
  override def handle(t: ActionEvent): Unit = {
    model.state.pickPlayer(model, action, actionTaker)
  }
}

class DisplayActionButtons(group: Group, actions:List[String], name:String, model: Model, memberRectangle:Rectangle) extends EventHandler[ActionEvent] {

  override def handle(t: ActionEvent): Unit = {

    model.state.displayActionButtons(group, actions, name, model, memberRectangle)
  }
}