package rockthejvm.functions

import scala.util.{Failure, Success, Try}

object Exceptions extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException)

  val unsafeMethod: String = throw new RuntimeException

  val potentialFailure = Try(unsafeMethod)



}
