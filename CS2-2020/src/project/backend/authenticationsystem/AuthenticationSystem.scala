package project.backend.authenticationsystem

import akka.actor.Actor


class AuthenticationSystem  extends Actor{

  val database: Database = dbType match {
    case "mySQL" => new MySQLDatabase()
    case "test" => new TestDatabase
  }


  override def receive: Receive = {
    case register:Register => TODO

    case registrationResult:RegistrationResult => TODO

    case login:Login => TODO

    case saveGame:SaveGame => saveGame

    case failedLogin:FailedLogin => TODO

    case authenticated:Authenticated => TODO
  }

}


