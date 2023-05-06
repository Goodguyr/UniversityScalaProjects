package backendTests

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import backend.authentificationSystem.{Authenticated, AuthenticationSystem, FailedLogin, Login, Register, RegistrationResult, SaveGame}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.duration._

class AuthenticationSystemTest extends TestKit(ActorSystem("TestClicks"))
  with ImplicitSender
  with WordSpecLike
  with Matchers
  with BeforeAndAfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  val auth = system.actorOf(Props(classOf[AuthenticationSystem]))

  "A JRPG Game" must {
    "react to user registration with no username" in {
      auth ! Register("", "")

      var result: RegistrationResult = expectMsgType[RegistrationResult](10000.millis)

      assert(result.registered == false && result.message == "Username is too short, username must contain at least 4 characters!")
    }

    "react to user registration with password without capital letters" in {
      auth ! Register("PepeTheFrog", "qwerty12345")

      var result: RegistrationResult = expectMsgType[RegistrationResult](10000.millis)

      assert(result.registered == false && result.message == "Password has to contain at least 1 capital letter!")
    }

    "react to user registration with password without a digit" in {
      auth ! Register("PepeTheFrog", "Qwertywhdbw")

      var result: RegistrationResult = expectMsgType[RegistrationResult](10000.millis)

      assert(result.registered == false && result.message == "Password has to contain at least 1 digit!")
    }

    "react to user registration with a short password" in {
      auth ! Register("PepeTheFrog", "qwer")

      var result: RegistrationResult = expectMsgType[RegistrationResult](10000.millis)

      assert(result.registered == false && result.message == "Password too short, length of the password must be over 6 characters!")
    }

    "react to correct user registration" in {
      auth ! Register("PepeTheLion", "Qwerty12345")

      var result: RegistrationResult = expectMsgType[RegistrationResult](10000.millis)

      assert(result.registered == true && result.message == "Registration complete!")
    }

    "react to user registration with taken username" in {
      auth ! Register("PepeTheLion", "Qwerty12345")

      var result: RegistrationResult = expectMsgType[RegistrationResult](10000.millis)

      assert(result.registered == false && result.message == "Username is taken, try again!")
    }

    "login into an account with incorrect password" in {
      auth ! Login("PepeTheLion", "incorrectPassword")

      var result: FailedLogin = expectMsgType[FailedLogin](10000.millis)

      assert(result.message == "Incorrect password")
    }

    "login into an account with incorrect username" in {
      auth ! Login("incorrectUsername", "incorrectPassword")

      var result: FailedLogin = expectMsgType[FailedLogin](10000.millis)

      assert(result.message == "Incorrect username")
    }

    "login into an account succesfully" in {
      auth ! Login("PepeTheLion", "Qwerty12345")

      var result: Authenticated = expectMsgType[Authenticated](10000.millis)

      assert(result.username == "PepeTheLion" && result.charactersJSON == "NewPlayersInfo")
    }

    "save data correctly and receive it when logging back in" in {
      auth ! SaveGame("PepeTheLion", "OldGameInfo")

      expectNoMessage(10000.millis)

      auth ! Login("PepeTheLion", "Qwerty12345")

      var result: Authenticated = expectMsgType[Authenticated](10000.millis)

      assert(result.charactersJSON == "OldGameInfo", result.username == "PepeTheLion")
    }
  }
}