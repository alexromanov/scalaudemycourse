package rockthejvm.exercises.course1

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangOutWith(person: Person): String = s"${this.name} hangs out with ${person.name}"

    def unary_! : String = s"$name, what the heck!"

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name"

    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)

    def unary_+ : Person = new Person(name, favoriteMovie, this.age + 1)

    def learns(subject: String): String = s"$name learns $subject"

    def learnsScala: String = this learns "Scala"

    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // infix notation, operator notation (only for functions with one parameter
  // (syntactic sugar)

  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(1.+(2))

  // prefix notation
  val x = -1
  val y = 1.unary_- // unary prefix works only with - + ~ !
  println(!mary)

  // postfix notation
  // println(mary isAlive) // only for method without parameters

  // apply (equivalent)
  println(mary.apply())
  println(mary())
}
