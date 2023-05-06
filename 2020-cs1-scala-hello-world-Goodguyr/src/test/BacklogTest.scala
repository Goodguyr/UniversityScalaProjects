import org.scalatest.FunSuite
import week11.Backlog

class BacklogTest extends FunSuite{

  class Email(message:String){
    val text = message
    var checked = false
  }

  def checkMail(email:Email):Unit = {
    email.checked = true
  }

  var backlog = new Backlog[Email](checkMail)

  var email = new Email("1")

  backlog.addTask(email)
  backlog.addTask(new Email("2"))
  backlog.addTask(new Email("3"))
  backlog.addTask(new Email("4"))
  backlog.addTask(new Email("5"))

  backlog.completeTask()
  backlog.completeTask()

  assert(!backlog.queueStart.value.checked && backlog.queueStart.value.text == "3" && email.checked)
}
