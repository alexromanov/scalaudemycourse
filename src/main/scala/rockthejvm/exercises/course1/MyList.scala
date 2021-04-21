package rockthejvm.exercises.course1

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](myTransformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def ++[B >: A](list:MyList[B]): MyList[B]

  def foreach(f: A => Unit): Unit
  def sort(compare: (A,A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A,B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

object Empty extends MyList[Nothing] {
  override def head: Nothing= throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](myTransformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int) = Empty
  def zipWith[B,C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have same length")
    else Empty

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  /*
    [1,2,3].filter(n%2 == 0)
    = [2,3].filter(n%2 == 0)
    = new Cons(2, [3].filter(n%2 == 0))
    = new Cons(2, Empty.filter(n%2 == ))
    = new Cons(2, Empty)
    = [2]

   */
  def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  /*
  [1,2,3].map(n*2)
    = new Cons(2, [2,3].map(n*2))
    = new Cons(2, new Cons(4, [3].map(n*2)))
    = new Cons(2, new Cons(4, new Cons(6, Empty.map(n*2))))
    = new Cons(2, new Cons(4, new Cons(6, Empty))))
   */
  def map[B](transformer: A => B): MyList[B] = {
    new Cons(transformer(h), t.map(transformer))
  }

  /*
    [1,2] ++ [3,4,5]
    = new Cons(1, [2] ++ [3,4,5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
   */
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
   */
  def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A,A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B,C](list: MyList[B], zip: (A,B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, h))(operator)
  }
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOFIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(elem => elem * 2).toString)
  // println(listOfIntegers.map(_ * 2).toString)
  println(listOfIntegers.filter(elem => elem % 2 == 0).toString)
  // println(listOfIntegers.filter(_ % 2 == 0).toString)
  println(listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))))
}
