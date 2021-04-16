package rockthejvm.functions

import scala.util.Random

object Sequences extends App {

  val aSequence = Seq(1,2,3,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence ++ Seq(7,8,9))
  println(aSequence(2))
  println(aSequence.sorted)

  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(print)
  (1 to 10).foreach(x => print(x))

  val aList = List(1,2,3)
  val prepended = 42 +: aList :+ 89
  println(prepended)

  val fiveApples = List.fill(5)("apple")
  println(fiveApples)

  println(aList.mkString("-"))

  // arrays
  val numbers = Array(1,2,3,4)
  val three = Array.ofDim[Int](3)
  three.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2) = 0
  numbers.mkString(" ")

  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  // vectors
  val vector: Vector[Int] = Vector(1,2,3)

  // Vectors vs Lists

  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))
}
