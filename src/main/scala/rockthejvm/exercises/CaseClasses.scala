package rockthejvm.exercises

object CaseClasses extends App {
  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  // 3. println(instalce) = println(instance.toString)
  // 4. equals and hashCode are implemented OOTB

  // 5. CCS have copty method
  val jim3 = jim.copy(age = 45)

  // 6. CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 7. CCs are serializable
  // 8. CCs have extractor patterns (pattern matching)



}
