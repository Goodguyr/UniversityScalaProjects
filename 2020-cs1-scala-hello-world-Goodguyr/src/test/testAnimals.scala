import inheritance._
import org.scalatest._

class testAnimals extends FunSuite{
  var cat = new Cat("Leo")
  var dog = new Dog("Dolfijs")
  assert(dog.name == "Dolfijs")
  assert(cat.name == "Leo")
  assert(cat.sound() == "meow")
  assert(dog.sound() == "woof")
}
