package rockthejvm.functions

object WhatAFunction extends App {

  val stringToIntConverter = new ((String) => Int) {
    override def apply(string: String): Int = string.toInt
  }

  val adder = new ((Int, Int) => Int) {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function Types Function2[A, B, R] === (A,B) => R

  def concatenator: (String, String) => String  = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }

  // Function that takes Int and return other Function
  // Function1[Int, Function1[Int, Int]]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  // shorter version of currying function
  val superAdd = (x : Int) => (y: Int) => x + y

  var adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(4)(3))
}
