package monad.free.my


object MyFree extends App {

  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

  implicit object optionFunctor extends Functor[Option] {
    override def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)
  }

  implicit object listFunctor extends Functor[List] {
    override def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)
  }

  //  trait Monad[F[_]] extends Functor[F] {
  //    def flatMap[A, B](f: A => F[B]): F[B]
  //
  //    def pure[A](a: A): F[A]
  //  }

  //  implicit def freeMonad[F: Functor]: Monad[Free[F, _]] = new Monad[Free[F, _]] {
  //    override def flatMap[A, B](f: A => F[B]): F[B] = ???
  //
  //    override def pure[A](a: A): F[A] = ???
  //  }

  sealed trait Free[F[_], A] {
    //    def map[B](f: A => B)(implicit functor: Functor[F]): Free[F, B] = this match {
    //      case FreeC(ff) =>
    //      case Pure(a) =>
    //    }

    def flatMap[B](f: A => Free[F, B])(implicit functor: Functor[F]): Free[F, B] = this match {
      case FreeC(ff) => FreeC(functor.map(ff)(x => x.flatMap(f)))
      case Pure(a) => f(a)
    }
  }

  case class FreeC[F[_], A](f: F[Free[F, A]]) extends Free[F, A]

  case class Pure[F[_], A](a: A) extends Free[F, A]


  println(FreeC[Option, Nothing](None))
  println(FreeC[Option, Nothing](Some(FreeC[Option, Nothing](None))))
}
