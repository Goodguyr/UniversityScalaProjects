package backend.authentificationSystem

case class Register(username: String, password: String)
case class RegistrationResult(username: String, registered: Boolean, message: String)
case class Login(username: String, password: String)
case class SaveGame(username: String, charactersJSON: String)
case class FailedLogin(username: String, message: String)
case class Authenticated(username: String, charactersJSON: String)