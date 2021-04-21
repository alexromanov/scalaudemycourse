package rockthejvm.lectures.part3concurrency

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object FuturesPromises extends App {
  def calculateMeaningOfLife: Int = {
    Thread.sleep(2000)
    42
  }

  val aFuture = Future {
    calculateMeaningOfLife
  }

  println(aFuture.value) // Option[Try[Int]]
  println("Waiting on the future")

  aFuture.onComplete {
    case Success(meaningOfLife) => println(s"Meaming of life is: $meaningOfLife")
    case Failure(exception) => println(s"I failed with $exception")
  }
}
