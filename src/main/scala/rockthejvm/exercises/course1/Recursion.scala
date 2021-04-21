package rockthejvm.exercises.course1

import scala.annotation.tailrec

object Recursion extends App {
  // Without tail recursion
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }

  println(factorial(5))

  // With tail recursion
  def tailFactorial(n: Int): BigInt = {
    @tailrec
    def helper(x: Int, acc: BigInt): BigInt = {
      if (x <= 1) acc
      else helper(x - 1, x * acc)
    }

    helper(n, 1)
  }

  println(tailFactorial(5))

  @tailrec
  def concatString(s: String, n: Int, acc: String): String = {
    if (n <= 0) acc
    else concatString(s, n - 1, s + acc)
  }

  println(concatString("Hello", 3, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(x: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      if (x <= 1) true
      else isPrimeTailRec(x - 1, n % x != 0 && isStillPrime)
    }

    isPrimeTailRec(n / 2, isStillPrime = true)
  }

  def fibonacci(n: Int): Int = {
    def fiboTailRec(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else fiboTailRec(i + 1, last + nextToLast, last)
    }

    if (n <= 2) 1
    else fiboTailRec(2, 1, 1)
  }

  println(fibonacci(8))
}
