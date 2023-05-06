package bank

import akka.actor.Actor

class BankAccount extends Actor {

  var balance:Int = 0

  override def receive: Receive = {
    case deposit: Deposit =>
      balance += deposit.amount

    case withdraw:Withdraw =>
      if(withdraw.amount > balance){}
      else{
        balance -= withdraw.amount
      }

    case CheckBalance => sender() ! Balance(balance)
  }
}
