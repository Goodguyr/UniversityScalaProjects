package week11

class Backlog[A](function: A => Unit){

  var queueStart:LinkedListNode[A] = null
  var queueEnd:LinkedListNode[A] = null

  def addTask(task:A):Unit = {
    if(queueEnd == null){
      queueEnd = new LinkedListNode[A](task, null)
      queueStart = queueEnd
    }
    else{
      queueEnd.next = new LinkedListNode[A](task, null)
      queueEnd = queueEnd.next
    }
  }

  def completeTask():Unit = {
    function(queueStart.value)
    queueStart = queueStart.next
    if(queueStart == null){
      queueEnd = null
    }
  }
}
