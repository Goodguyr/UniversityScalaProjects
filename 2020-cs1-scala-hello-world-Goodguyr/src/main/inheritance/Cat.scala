package inheritance

class Cat(val name: String) extends Animal(name: String) {
  override def sound(): String = {
    "meow"
  }
}
