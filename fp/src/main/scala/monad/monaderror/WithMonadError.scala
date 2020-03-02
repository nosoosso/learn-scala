package monad.monaderror

import cats._
import cats.data.EitherT
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object WithMonadError extends App {
  val eitherRepository = new AbstractRepository2[({ type L[F] = Either[Throwable, F] })#L]

  type FE[F] = EitherT[Future, Throwable, F]
  val eitherTRepository = new AbstractRepository2[FE]

  println(eitherRepository.findLanguageFromUser(1))
  println(eitherRepository.findLanguageFromUser(2))
  println(eitherRepository.findLanguageFromUser(3))

  println(Await.result(eitherTRepository.findLanguageFromUser(1).value, Duration.Inf))
  println(Await.result(eitherTRepository.findLanguageFromUser(2).value, Duration.Inf))
  println(Await.result(eitherTRepository.findLanguageFromUser(3).value, Duration.Inf))
}

class AbstractRepository2[F[_]](implicit monad: MonadError[F, Throwable]) {
  def findUser(userId: Long): F[User2] = {
    userId match {
      case 1L => monad.pure(User2("Yamada"))
      case 2L => monad.pure(User2("Tanaka"))
      case _  => monad.raiseError(new Exception("user does not exists"))
    }
  }

  def findLanguage(user: User2): F[String] = {
    user match {
      case User2("Yamada") => monad.pure("ja")
      case _               => monad.raiseError(new Exception("language not found"))
    }
  }

  def findLanguageFromUser(userId: Long): F[String] = {
    for {
      user <- findUser(userId)
      lang <- findLanguage(user)
    } yield lang
  }
}

case class User2(name: String)
