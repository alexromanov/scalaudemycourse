package rockthejvm.exercises

object InheritAndTraits extends App {

  class Animal {
    def eat(): Unit = println("nomnom")
  }

  // Single class inheritance
  class Cat extends Animal

  val cat = new Cat
  cat.eat

  // private modifier only on class level, protected modifier is on class and subclass level

  // constructors
  class Person(name: String, age: Int)
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // overriding
  class Dog extends Animal {
    override def eat(): Unit = println("crunch, crunch")
  }

  /*
  It is possible to override fields in the class constructor
  class Dog(override val creatureType: String) extends Animal
   */

  // type substitution
  val unknownAnimal: Animal = new Dog

  // super - same as in Java
  // preventing overrides
    // 1 - final on member or class (prevents extending)
    // 2 - seal the class = extend classes in this file, but not from other files
}
