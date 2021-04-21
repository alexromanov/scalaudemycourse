package rockthejvm.lectures.part1as

import scala.util.Try

object DarkSugar extends App {

  // 1 methods with single param
  def singleArg(arg: Int): String = s"$arg little ducks..."

  val desc = singleArg {
    42
  }

  val aTry = Try {
  }

  List(1,2,3).map { x =>
    x + 1
  }

  // 2: single abstract method

  trait Action {
    def act(x: Int): Int
  }

  val aInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val aFunky: Action = (x: Int) => x + 1

  // example: runnables
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("Hello Scala")
  })

  val aSweeterThread = new Thread(() => println("Sweet Scala"))

  // assign lambda to one non-implemented method
  abstract class AnAbstractType {
    def implemented: Int = 23
    def f(a: Int): Unit
  }

  val anAbstractInstance = (a: Int) => println("sweet")

  // 3: the :: and #:: methods are special

  val prependedList = 2 :: List(3,4)
  // 2.::List(3,4)
  // List(3,4).::(2)
  // ?!

  // scala spec: last char decides associativity of method

  1 :: 2 :: 3 :: List(3,4)
  List(3,4).::(3).::(2).::(1)

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this
  }

  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  // 4: multi-word method naming
  class TeenGirl(name: String) {
    def `and then said`(gossip: String) = println(s"$name said $gossip")
  }

  val lilly = new TeenGirl("Lilly")
  lilly `and then said` "Scala is so sweet"

  // 5: infix types
  class Composite[A,B]
  val composite: Int Composite String = ???

  class -->[A,B]
  val towards: Int --> String = ???

  // 6: update() is very special, like apply()
  val anArray = Array(1,2,3)
  anArray(2) = 7 // rewritten to anArray.update(2,7)
  // used in mutable collections
  // remember apply() and update()

  // 7: setters for mutable containers
  class Mutable {
    private var internalMember: Int = 0
    def member = internalMember
    def member_=(value: Int): Unit = internalMember = value
  }

  val aMutable = new Mutable
  aMutable.member = 42
}
