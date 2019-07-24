package taglessfinal

import cats.Id

import scala.collection.mutable
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

trait Store[F[_]] {
  def put(key: String, value: String): F[Unit]
  def get(key: String): F[Option[String]]
}

object Store {
  def apply[F[_]](implicit StoreF: Store[F]): Store[F] =
    StoreF

  def put[F[_]: Store](key: String, value: String): F[Unit] =
    Store[F].put(key, value)

  def get[F[_]: Store](key: String): F[Option[String]] =
    Store[F].get(key)
}

object StoreId extends Store[Id] {
  val map = mutable.Map[String, String]()

  override def put(key: String, value: String): Id[Unit] =
    map += (key -> value)

  override def get(key: String): Id[Option[String]] =
    map.get(key)
}

object StoreFuture extends Store[Future] {
  val map = mutable.Map[String, String]()

  override def put(key: String, value: String): Future[Unit] =
    Future.successful(map += (key -> value))

  override def get(key: String): Future[Option[String]] =
    Future.successful(map.get(key))
}

object Main extends App {
  import cats.implicits._

  {
    val store = Store(StoreId)
    val res = for {
      _ <- store.put("a", "aaa")
      _ <- store.put("b", "bbb")
      result <- store.get("a")
    } yield result

    println(res)
  }
  {
    val store = Store(StoreFuture)
    import ExecutionContext.Implicits.global
    val res = for {
      _ <- store.put("a", "aaaaaaaaaaaaa")
      _ <- store.put("b", "bbbbbbbbbbbbb")
      result <- store.get("a")
    } yield result

    println(Await.result(res, Duration.Inf))
  }
}
