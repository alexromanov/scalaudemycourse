package rockthejvm.functions

object MapFilter extends App {

  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  println(list.filter(_ % 2 == 0))

  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // iterating
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)

  // for-comprehensions (will be re-written to map flatMap by compiler)
  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color





}
