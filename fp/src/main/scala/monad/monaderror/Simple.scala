package monad.monaderror

import cats._
import cats.data.EitherT
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object Simple extends App {
  val optionRepository = new AbstractRepository[Option] {
    override def findUser(userId: Long): Option[User] = userId match {
      case 1L => Some(User("Yamada"))
      case 2L => Some(User("Tanaka"))
      case _  => None
    }

    override def findLanguage(user: User): Option[String] = user match {
      case User("Yamada") => Some("ja")
      case _              => None
    }
  }

  val eitherRepository = new AbstractRepository[({ type L[F] = Either[Throwable, F] })#L]() {
    override def findUser(userId: Long): Either[Throwable, User] = userId match {
      case 1L => Right(User("Yamada"))
      case 2L => Right(User("Tanaka"))
      case _  => Left(new Exception("user does not exists"))
    }

    override def findLanguage(user: User): Either[Throwable, String] = user match {
      case User("Yamada") => Right("ja")
      case _              => Left(new Exception("language not found"))
    }
  }

  type FE[F] = EitherT[Future, Throwable, F]
  val eitherTRepository = new AbstractRepository[FE] {
    override def findUser(userId: Long): FE[User] = userId match {
      case 1L => EitherT.rightT(User("Yamada"))
      case 2L => EitherT.rightT(User("Tanaka"))
      case _  => EitherT.leftT(new Exception("user does not exists"))
    }

    override def findLanguage(user: User): FE[String] = user match {
      case User("Yamada") => EitherT.rightT("ja")
      case _              => EitherT.leftT(new Exception("language not found"))
    }
  }

  println(optionRepository.findLanguageFromUser(1))
  println(optionRepository.findLanguageFromUser(2))
  println(optionRepository.findLanguageFromUser(3))

  println(eitherRepository.findLanguageFromUser(1))
  println(eitherRepository.findLanguageFromUser(2))
  println(eitherRepository.findLanguageFromUser(3))

  println(Await.result(eitherTRepository.findLanguageFromUser(1).value, Duration.Inf))
  println(Await.result(eitherTRepository.findLanguageFromUser(2).value, Duration.Inf))
  println(Await.result(eitherTRepository.findLanguageFromUser(3).value, Duration.Inf))
}

abstract class AbstractRepository[F[_]](implicit monad: Monad[F]) {
  def findUser(userId: Long): F[User]

  def findLanguage(user: User): F[String]

  def findLanguageFromUser(userId: Long): F[String] = {
    for {
      user <- findUser(userId)
      lang <- findLanguage(user)
    } yield lang
  }
}

case class User(name: String)
