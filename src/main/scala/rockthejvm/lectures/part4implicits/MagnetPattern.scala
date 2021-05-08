package rockthejvm.lectures.part4implicits

import scala.concurrent.Future

object MagnetPattern extends App {
  // method overloading
  class P2PRequest
  class P2PResponse
  class Serializer[T]

  trait Actor {
    def receive(statusCode: Int): Int
    def receive(request: P2PRequest): Int
    def receive(response: P2PResponse): Int
    def receive[T : Serializer](message: T): Int
    def receive[T : Serializer](message: T, statusCode: Int): Int
    def receive(future: Future[P2PRequest]): Int
    //def receive(future: Future[P2PResponse]): Int
  }

  // 1 - type erasure
  // 2 - lifting doesn't work for all overloads
  // 3 - code duplication
  // 4 - type inference and default args


  trait MessageMagnet[Result] {
    def apply(): Result
  }

  def receive[R](magnet: MessageMagnet[R]): R = magnet()

  implicit class FromP2PRequest(request: P2PRequest) extends MessageMagnet[Int] {
    def apply(): Int = {
      // logic for handling a P2P
      println("Handling P2PRequest")
      42
    }
  }


  implicit class FromP2PResponse(request: P2PResponse) extends MessageMagnet[Int] {
    def apply(): Int = {
      // logic for handling a P2P
      println("Handling P2PResponse")
      24
    }
  }

  receive(new P2PRequest)
  receive(new P2PResponse)
}
