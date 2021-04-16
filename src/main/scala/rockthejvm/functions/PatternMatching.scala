package rockthejvm.functions

object PatternMatching extends App {

  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in US!"
    case Person(n, a) => s"Hi, my name is $n and I am $a age old!"
    case _ => "I don't know who I am"
  }

  println(greeting)

  /*
    1. Cases are matched in order
    2. what if no cases match? MatchError
    3. type of the PM expression? unified type for all cases

   */

  trait Expr

  case class Number(n: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr

  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " " + show(e2)
    case Prod(e1, e2) => {
      def maybeShowParentheses(exp: Expr) = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => "{" + show(exp) + "}"
      }

      maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
    }
  }
}
