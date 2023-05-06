package clicker.view

import io.socket.client.{IO, Socket}
import io.socket.emitter.Emitter
import javafx.application.Platform
import javafx.event.{ActionEvent, EventHandler}
import play.api.libs.json.{JsValue, Json}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Scene, text}
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.layout.{HBox, VBox}

class HandleMessagesFromServer() extends Emitter.Listener {
  override def call(objects: Object*): Unit = {

    // Use runLater when interacting with the GUI
    Platform.runLater(() => {
      // This method is called whenever a new game state is received from the server
      val jsonGameState = objects.apply(0).toString
      print(jsonGameState)

      // TODO: Display the game state on your GUI
      // You must display: current gold, and the name, number owned, and cost for each type of equipment
      var parsed: JsValue = Json.parse(jsonGameState)

      var gold: Double = (parsed \ "gold").as[Double]
      DesktopGUI.goldValue.text = "You own " + gold + "gold"

      var equipment:JsValue = (parsed \ "equipment").get

      var shovelInfo:JsValue = (equipment \ "shovel").get
      var shovelAmount:Int = shovelInfo("numberOwned").as[Int]
      var shovelCost: Double = shovelInfo("cost").as[Double]
      DesktopGUI.shovelValue.text = "You own " + shovelAmount.toInt + " shovels"
      DesktopGUI.shovelPrice.text = "Shovel costs " + shovelCost + "$"

      var excavatorInfo: JsValue = (equipment \ "excavator").get
      var excavatorAmount:Int = excavatorInfo("numberOwned").as[Int]
      var excavatorCost: Double = excavatorInfo("cost").as[Double]
      DesktopGUI.excavatorValue.text = "You own " + excavatorAmount.toInt + " excavators"
      DesktopGUI.excavatorPrice.text = "Excavator costs " + excavatorCost + "$"

      var mineInfo:JsValue = (equipment \ "mine").get
      var mineAmount:Int = (mineInfo \ "numberOwned").as[Int]
      var mineCost:Double = mineInfo("cost").as[Double]
      DesktopGUI.mineValue.text = "You own " + mineAmount.toInt + " mines"
      DesktopGUI.minePrice.text = "Mine costs " + mineCost + "$"

    })

      // You can access any variables/methods in the DesktopGUI object from this class
      // ex. DesktopGUI.goldTextField.text = goldFromGameState
    }
}


object DesktopGUI extends JFXApp {

  var socket: Socket = IO.socket("http://localhost:8080")
  socket.on("gameState", new HandleMessagesFromServer)
  socket.connect()

  // Change "test" to any username you'd like to start a new game
  socket.emit("register", "newPlayer")

  // Call this method whenever the user clicks your gold button
  def clickGold(): Unit = {
    socket.emit("clickGold")
  }

  // Call this method whenever the user clicks to purchase equipment
  // The parameter is the id of the equipment type to purchase
  def buyEquipment(equipmentId: String): Unit = {
    socket.emit("buy", equipmentId)
  }

  // TODO: Setup your GUI
  // You may create and place all GUI elements (TextFields, Buttons, etc) then only change the text on
  // each element when a new game state is received
  // You may assume that there will be exactly 3 types of equipment with ids of "shovel", "excavator", and "mine"

  val goldButton: Button = new Button {
    minWidth = 100
    minHeight = 105
    style = "-fx-font: 28 ariel;"
    text = "gold"
    onAction = new ButtonListener
  }

  val shovelButton: Button = new Button {
    minWidth = 100
    minHeight = 105
    style = "-fx-font: 28 ariel;"
    text = "buy shovel"
    onAction = new PurchaseListener("shovel")
  }

  val excavatorButton: Button = new Button {
    minWidth = 100
    minHeight = 105
    style = "-fx-font: 28 ariel;"
    text = "buy excavator"
    onAction = new PurchaseListener("excavator")
  }

  val mineButton: Button = new Button {
    minWidth = 100
    minHeight = 105
    style = "-fx-font: 28 ariel;"
    text = "buy mine"
    onAction = new PurchaseListener("mine")
  }

  val leftBox = new VBox(){
    children = List(goldButton, shovelButton, excavatorButton, mineButton)
  }

  val goldValue: TextField = new TextField {
    minWidth = 300
    minHeight = 60
    editable = false
    style = "-fx-font: 18 ariel;"
  }

  val mineValue: TextField = new TextField {
    minWidth = 300
    minHeight = 60
    editable = false
    style = "-fx-font: 18 ariel;"
  }

  val minePrice: TextField = new TextField {
    minWidth = 300
    minHeight = 60
    editable = false
    style = "-fx-font: 18 ariel;"
  }

  val excavatorValue: TextField = new TextField {
    minWidth = 300
    minHeight = 60
    editable = false
    style = "-fx-font: 18 ariel;"
  }

  val excavatorPrice: TextField = new TextField {
    minWidth = 300
    minHeight = 60
    editable = false
    style = "-fx-font: 18 ariel;"
  }

  val shovelValue: TextField = new TextField {
    minWidth = 300
    minHeight = 60
    editable = false
    style = "-fx-font: 18 ariel;"
  }

  val shovelPrice: TextField = new TextField {
    minWidth = 300
    minHeight = 60
    editable = false
    style = "-fx-font: 18 ariel;"
  }

  val rightBox = new VBox(){
    children = List(goldValue, shovelValue, shovelPrice, excavatorValue, excavatorPrice, mineValue, minePrice)
  }

  this.stage = new PrimaryStage {
    title = "CSE Clicker"
    scene = new Scene(500,420) {
      content = List(
      new HBox(){
        children = List(leftBox, rightBox)
      }
      )
    }

  }

  class ButtonListener extends EventHandler[ActionEvent] {
    override def handle(event: ActionEvent): Unit = {
      clickGold()
    }
  }

  class PurchaseListener(item:String) extends EventHandler[ActionEvent]{
    override def handle(t: ActionEvent): Unit = {
      buyEquipment(item)
    }
  }
}
