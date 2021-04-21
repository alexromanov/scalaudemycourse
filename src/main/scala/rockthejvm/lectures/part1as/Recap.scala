package rockthejvm.lectures.part1as

import scala.annotation.tailrec

object Recap extends App {
  val aCondition: Boolean = false
  val aConditionedVal = if (aCondition) 42 else 65
  // instructions vs expressions

  // compiler infers types for us
  val aCodeBlock = {
    if (aCondition) 54
    56
  }

  // Unit = void
  val theUnit = println("hell, Scala")

  def aFunction(x: Int): Int = x + 1

  // recursion: stack and tail

  @tailrec
  def factorial(n: Int, acc: Int): Int =
    if (n <= 0) acc
    else factorial(n - 1, n * acc)

  // OOP
  class Animal
  class Dog extends Animal
  val aDog: Animal = new Dog

  trait Carnivore {
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = print("Some")
  }

  // method notations
  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog

  abstract class MyList[+A] // variance and problems


}
