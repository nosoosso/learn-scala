package monad.state

import cats.data.StateT

import scala.concurrent.{Await, Future}
import cats.instances.future._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object StateTFuture extends App {
  val saito = Human2("Saito", 13, true)

  val resultFuture = Human2.modifyHuman.run(saito)
  val result = Await.result(resultFuture, Duration.Inf)

  println(result)
}

case class Human2(name: String, age: Int, man: Boolean)


object Human2 {
  type FutureState[S, A] = StateT[Future, S, A]

  def setName(newName: String): FutureState[Human2, Unit] = {
    StateT { human: Human2 =>
      Future.successful((human.copy(name = newName), ()))
    }
  }

  def addAge(num: Int): FutureState[Human2, Unit] = {
    StateT.modify { human =>
      human.copy(age = human.age + num)
    }
  }

  def toggleSex: FutureState[Human2, Unit] = {
    StateT.modify { human =>
      human.copy(man = !human.man)
    }
  }

  def modifyHuman: FutureState[Human2, Unit] = {
    for {
      _ <- setName("Imada")
      _ <- addAge(20)
      _ <- toggleSex
    } yield ()
  }
}
