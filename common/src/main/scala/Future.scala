import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object FuturePractice extends App {
  val futures: Future[List[Int]] = Future.traverse((1 to 10).toList) { i =>
    Future {
      i
    }
  }

  Await.result(FutureCreator.light(), Duration.Inf)
}

object FutureCreator {
  def light(): Future[Int] = Future {
    Thread.sleep(100)
    1
  }

  def heavy(): Future[Int] = Future {
    Thread.sleep(1000)
    1
  }

  def fail(): Future[Int] = Future {
    throw new Exception("future failed")
  }
}
