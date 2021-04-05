package rockthejvm.functions

object AnonymousFunctions extends App {
  val doubler = (x: Int) => x * 2 // lambda (anonymous) function

  val adder = (a: Int, b: Int) => a + b
  val adderA: (Int, Int) => Int = (a: Int, b: Int) => a + b

  val justDo = () => 3
  val justDoA: () => Int = () => 3

  // In case of lambda - call function always with () if function w'o parameters
  println(justDo) // wrong! functions itself
  println(justDo()) // correct

  val stringToInt = { (str: String) =>
    str.toInt
  }

  // sugar
  val niceIncrementor: Int => Int = _ + 1 // equivalent to x => x + 1
  val adderSugar: (Int, Int) => Int = _ + _


}
