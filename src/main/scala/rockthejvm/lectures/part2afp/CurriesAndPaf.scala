package rockthejvm.lectures.part2afp

object CurriesAndPaf extends App {
  val superAdder: Int => Int => Int =
    x => y => x + y

  val add3 = superAdder(3)
  println(add3(5))
  println(superAdder(3)(5)) // curried function

  def curriedAdder(x: Int)(y: Int): Int = x + y // curried method

  val add4: Int => Int = curriedAdder(4)

  // lifting = ETA-EXPANSION
  // functions !+ methods (JVM limitation)

  def inc(x: Int) = x + 1
  List(1,2,3).map(x => inc(x)) // ETA expansion

  // Partial function applications

  val add5 = curriedAdder(5) _ // Int => Int

  // Exercise

  val simpleAddFunction = (x: Int, y: Int) => x + y
  def simpleAddMethod(x: Int, y: Int) = x + y
  def curriedAddMethod(x: Int)(y: Int) = x + y

  val add7 = (x: Int) => simpleAddFunction(7, x)
  val add7_2 = simpleAddFunction.curried(7)
  val add7_3 = curriedAddMethod(7) _ // PAF
  val add7_4 = curriedAddMethod(7)(_) // PAF - alternative syntax
  val add7_5 = simpleAddMethod(7, _: Int) // alternative syntax for turning methods into function values // y=> simpleAddMethod(7, y)

  def concatenator(a: String, b: String, c: String) = a + b + c
  def insertName = concatenator("Hello, I'm ", _:String, ", how are you?")
  println(insertName("Alex"))

  val fillInTheBlanks = concatenator("Hello, ", _: String, _:String)

  // EXERCISES

  // 1
  def curriedFormatter(s: String)(number: Double): String = s.format(number)
  val numbers = List(Math.PI, Math.E, 1, 9.8, 1.3e-12)
  val simpleFormat = curriedFormatter("%4.2f") _
  val seriousFormat = curriedFormatter("%8.6f") _
  val preciseFormat = curriedFormatter("%14.12f") _

  // 2

  def byName(n: => Int) = n + 1
  def byFunction(f: () => Int) = f() + 1
  def method: Int = 42
  def parenMethod(): Int = 42

  byName(23) // ok
  byName(method) // ok
  byName(parenMethod())
  byName(parenMethod) // ok but beware ==> byName(parenMethod())

  // byName(() => 42) // not ok
 // byName(parentMethod _) not ok

  // byFunction(45) // not ok
  // byFunction(method) // not ok
  // byFunction(parenMethod)
  byFunction(() => 42)
  byFunction(parenMethod _)








}
