package rockthejvm.lectures.part4implicits

object PimpMyLibrary extends App {
  implicit class RichInt(val value: Int) {
    def isEven: Boolean = value % 2 == 0
    def sqrt: Double = Math.sqrt(value)
    def times(function: () => Unit) = {
      def timesAux(n: Int): Unit = {
        if (n <= 0) ()
        else {
          function()
          timesAux(n - 1)
        }
        timesAux(value)
      }
    }

    def *[T](list: List[T]): List[T] = {
      def concatenate(n: Int): List[T] =
        if (n <= 0) List()
        else concatenate(n - 1) ++ list

      concatenate(value)
    }

  }

  new RichInt(42).sqrt
  42.isEven

  // type enrichment

  implicit class RichString(string : String) {
    def asInt: Int = Integer.valueOf(string)
    def encrypt(cypherDistance: Int): String = string.map(c => (c + cypherDistance).asInstanceOf[Char])
  }

  // "3" / 4
  implicit def stringToInt(string: String): Int = Integer.valueOf(string)
  println("6" / 2)

  // equlivalent: implicit class RichAltInt(value: Int)
  class RichAltInt(value: Int)
  implicit def enrich(value: Int): RichAltInt = new RichAltInt(value)

  // danger zone. Prefer implicit classes rather than implicit functions
}
