package rockthejvm.lectures.part1as

object AdvancedPatternMatching extends App {

  val numbers = List(1)
  val description = numbers match {
    case head :: Nil => println(s"The only element is $head.")
    case _ =>
  }

  /*
    - constants
    - wildcards
    - case classes
    - tuples
    - some special magic
   */

  class Person(val name: String, val age: Int)

  // can rename to any string
  object Person {
    def unapply(person: Person): Option[(String, Int)] = {
      if (person.age < 21) None
      else Some((person.name, person.age))
    }

    def unapply(age: Int): Option[String] =
      Some(if (age < 21) "minor" else "major")
  }

  val bob = new Person("Bob", 25)
  val greeting = bob match {
    case Person(a,b) => s"Hi my name is $a and I am $b years old"
  }

  val status = bob.age match {
    case Person(st) => s"My Legal status is $st"
  }

  // Exercise
  object even {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  object singleDigit {
    def unapply(arg: Int): Boolean = arg > - 10 && arg < 10
  }

  val n: Int = 45
  val matchP = n match {
    case singleDigit() => "single digit"
    case even() => "an even number"
    case _ => "no property"
  }

   // infix patterns
  case class Or[A,B](a: A, b: B) // Either
  val either = Or(2, "two")
  val num = either match {
    case number Or string => s"$number is written as $string"
  }

  // decomposing sequences
  val vararg = numbers match {
    case List(1, _*) => "starting with 1"
  }

  abstract class MyList[+A] {
    def head: A = ???
    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val decomposed = myList match {
    case MyList(1,2, _*) => "starting with 1,2"
    case _ => "something else"
  }

   // custom return types for unapply
  // isEmpty: Boolean, get: Something

  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      override def isEmpty: Boolean = false
      override def get: String = person.name
    }
  }

  println(bob match {
    case PersonWrapper(n) => s"This person's name is $n"
    case _ => "A alien"
  })
}
