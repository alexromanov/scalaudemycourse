package rockthejvm.exercises.course1

object AbstractDataTypes extends App {

  // abstract

  abstract class Animal {
    val creatureType: String

    def eat(): Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    override def eat(): Unit = println("crunch crunch")
  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "croc"

    override def eat(): Unit = println("nomnomnom")

    override def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // both can have abstract and non-abstract methods and members
  // traits do not have contructor parameters
  // you can extend one class, but mix in multiple traits
  // traits are behavior, abstract class is a type of thing

}
