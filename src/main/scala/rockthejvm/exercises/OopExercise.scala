package rockthejvm.exercises

object OopExercise {

  class Writer(firstname: String, surname: String, val year: Int) {
    def fullName(): String = firstname + " " + surname

  }

  class Novel(name: String, year: Int, author: Writer) {
    def authorAge(): Int = year - author.year
    def isWrittenBy(author: Writer): Boolean = author == this.author
    def copy(newYearOfRelease: Int): Novel = new Novel(this.name, newYearOfRelease, this.author)
  }

  class Counter(val count: Int) {
    def increment() = new Counter(count + 1) // immutability (when modify return new instance)
    def decrement() = new Counter(count - 1)

    def increment(n: Int): Counter = {
      if (n <= 0) this
      else increment().increment(n - 1)
    }

    def decrement(n: Int): Counter = {
      if (n <= 0) this
      else decrement().decrement(n - 1)
    }
  }
}
