package rockthejvm.functions

import rockthejvm.exercises.Cons

object AllThePatterns extends App {

  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "The Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  val matchAnything = x match {
    case _ =>
  }

  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  val aTuple = (1,2)

  val matchATuple = aTuple match {
    case (1,1) =>
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (2,3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }

  val aList = List(1,2,3,42)
  val listMatch = aList match {
    case List(1, _, _, _) =>
    case List(1, _*) =>
    case 1:: List(_) =>
    case List(1,2,3) :+ 42 =>
  }

  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] =>
    case _ =>
  }

  val multipattern = aList match {
    case List(2) | List(1) =>
  }


  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfString: List[String] => "a list of strings"
    case listOfInts: List[Int] => "a list of ints"
    case _ => ""
  }



}
