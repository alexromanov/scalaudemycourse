package rockthejvm.functions

object PatternsEverywhere extends App {
  try {

  } catch {
    case e: RuntimeException => ""
    case npe: NullPointerException => ""
    case _ => "something"
  }

  // Try is a MATCH

}
