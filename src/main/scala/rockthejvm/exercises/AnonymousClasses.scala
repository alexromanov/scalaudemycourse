package rockthejvm.exercises

object AnonymousClasses extends App {
  abstract class Animal {
    def eat: Unit
  }

  val funny: Animal = new Animal {
    override def eat: Unit = println("ahaa")
  }

}
