package rockthejvm.lectures.part2afp

object Monads extends App {
  trait Attempt[+A] {
   def flatMap[B](f: A => Attempt[B]): Attempt[B]
  }

  object Attempt {
    def apply[A](a: => A): Attempt[A] =
      try {
        Success(a)
      } catch {
        case e: Throwable => Fail(e)
      }
  }

  case class Success[+A](value: A) extends Attempt[A] {
    def flatMap[B](f: A => Attempt[B]): Attempt[B] =
      try {
        f(value)
      } catch {
        case e: Throwable => Fail(e)
      }
  }

  case class Fail(e: Throwable) extends Attempt[Nothing] {
    def flatMap[B](f: Nothing => Attempt[B]): Attempt[B] = this
  }

  /**
   *
    left-identity

    unit.flatMap(f) = f(x)
    Attempt(x).flatMap(f) = f(x) // Success case!
    Success(x).flatMap(f) = f(x) // proved.

    right-identity

    attempt.flatMap(unit) = attempt
    Success(x).flatMap(x => Attempt(x)) = Attempt(x) = Success(x)
    Fail(_).flatMap(...) = Fail(e)

    associativity
    attempt.flatMap(f).flatMap(g) == attempt.flatMap*x => f(x).flatMap(g))
    Fail(e).flatMap(f).flatMap(g) = Fail(e)

    Success(v).flatMap(f).flatMap(g) =
      f(v).flatMap(g) OR Fail(e)
    */

    val attempt = Attempt {
      throw new RuntimeException("MY own monad, yes!")
    }

  // lazy monad
  class Lazy[+A](value: => A) {
    // call by need
    private lazy val internal = value
    def use: A = internal
    def flatMap[B](f: (=>A) => Lazy[B]): Lazy[B] = f(internal)
  }

  object Lazy {
    def apply[A](value: => A): Lazy[A] = new Lazy(value)
  }

  val lazyInstance = Lazy {
    println("Today I don't feel like doing anything")
    42
  }

  val flatMappedInstance = lazyInstance.flatMap(x => Lazy {
    10  * x
  })

  val flatMappedInstanc2 = lazyInstance.flatMap(x => Lazy {
    10  * x
  })

  flatMappedInstance.use
  flatMappedInstanc2.use



}
