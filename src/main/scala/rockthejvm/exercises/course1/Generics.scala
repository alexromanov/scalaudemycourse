package rockthejvm.exercises.course1

object Generics extends App {

  class MyList[+A] {
    def add[B >: A](element: B): MyList[B] = ???

    /*
    If I add dog to list of cat it will turn list of cats and dogs to something generic - animal
     */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptys = MyList.empty[Int]

  // variance problem
  class Animal

  class Dog extends Animal

  class Cat extends Animal

  // 1. yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // animalList.add(new Dog) ??? HARD QUESTION

  // 2. No = INVARIANCE
  class InvariantList[A]

  val invariantListAnimal: InvariantList[Animal] = new InvariantList[Animal]

  // 3. CONTRAVARIANCE (HELL NO)
  class Trainer[-A]

  val contraVariantList: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A)

  val cage = new Cage(new Dog)
}
