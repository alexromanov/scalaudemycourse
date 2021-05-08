package rockthejvm.lectures.part4implicits

object OrganisingImplicits extends App {
  implicit val reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  println(List(1, 3, 2, 6, 4).sorted)

  // implicit sort parameter is coming from scala.PreDef

  /*
    Implicits (used as implicit parameters:
      - val/var
      - object
      - accessor methods = defs with no parentheses
   */

  case class Person(name: String, age: Int)

  val persons = List(
    Person("Steve", 30),
    Person("Amy", 22),
    Person("John", 66)
  )

  //  object Person {
  //    implicit val alphabetOrdering: Ordering[Person] = Ordering.fromLessThan((a,b) => a.name.compareTo(b.name) < 0)
  //  }
  //
  //  implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan((a,b) => a.age < b.age)

  object AlphabeticNameOrdering {
    implicit val alphabetOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
  }

  object AgeOrdering {
    implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.age < b.age)
  }

  import AlphabeticNameOrdering._

  println(persons.sorted)

  /*
    Implicit scope
      - normal scope = LOCAL SCOPE
      - imported scope
      - companions of all types involved  in the method signature
        - List
        - Ordering
        - all the types involved = A or any supertype
   */

  // Purchase sorting
  /*
    - totalPrice
    - by unit count
    - by unit price
   */
  case class Purchase(nUnits: Int, unitPrice: Double) {
    def totalPrice: Double = nUnits * unitPrice
  }

  implicit val totalPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.totalPrice < _.totalPrice)
  implicit val unitCountOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.nUnits < _.nUnits)
  implicit val unitPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.unitPrice < _.unitPrice)

  object Purchase {
    implicit val totalPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => a.nUnits * a.unitPrice < b.nUnits * b.unitPrice)
  }

  object UnitCountOrdering {
    implicit val unitCountOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.nUnits < _.nUnits)
  }

  object UnitPriceOrdering {
    implicit val unitPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.unitPrice < _.unitPrice)
  }
}
