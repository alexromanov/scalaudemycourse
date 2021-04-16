package rockthejvm.functions

object TuplesAndMaps extends App {
  val aTuple = (2, "hello, Scala")

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye java"))
  println(aTuple.swap)

  val aMap: Map[String, Int] = Map()
  val phoneBook = Map("Jim" -> 555, ("Mary", 111)).withDefaultValue(-1)

  val newPairing = "John" -> 222
  val newPhoneBook = phoneBook + newPairing

  println(phoneBook.toList)
  println(List(("Ja", 222)).toMap)

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }

  def friends(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)

    network + (personA -> (friendsA + personB)) + (personB -> (friendsB + personA))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], acc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) acc
      else removeAux(friends.tail, unfriend(acc, person, friends.head))
    }

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }

  def noFriends(network: Map[String, Set[String]]): Int = {
    network.count(pair => pair._2.isEmpty)
  }
}
