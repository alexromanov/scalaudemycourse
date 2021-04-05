package rockthejvm.exercises

object Objects extends App {

  // Scala does not have class-level functionality (static)
  // Use objects instead

  object Person {
    // static / class level functionality
    val N_EYES = 2
    def canFly: Boolean = true // methods also can be defined, but only without parameters

    // factory method
    def from(mother: Person, father: Person): Person = new Person("Bobbie")
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    // instance-level functionality
  }

  // class and object are companions

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala Object = SINGLETON INSTANCE
  val mary = Person
  val john = Person
  println(mary == john)

  // val bob = Person.apply(mary, john)

  // Scala Applications = Scala object with def main(args: Array[String]): Unit

}
