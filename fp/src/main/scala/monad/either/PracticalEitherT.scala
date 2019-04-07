package monad.either


import cats.data.EitherT
import cats.instances.future._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object PracticalEitherT extends App {
  val succeeded = ArticleService.copyArticleBody(1, 2)
  val s = Await.result(succeeded.value, Duration.Inf)
  println(s)

  val failed1 = ArticleService.copyArticleBody(1, 3)
  val f1 = Await.result(failed1.value, Duration.Inf)
  println(f1)

  val failed2 = ArticleService.copyArticleBody(1, 4)
  val f2 = Await.result(failed2.value, Duration.Inf)
  println(f2)

  // output:
  // Right(Article(2,Hello,false))
  // Left(Article is locked: articleId=3)
  // Left(Article not found: articleId=4)
}


object ArticleService {
  /**
    * 記事を２つ取得し、片方の内容をもう片方にコピーする。
    * このときコピー先の記事がロック状態だった場合、コピーに失敗する。
    * また、指定したidの記事が存在しなかった場合も失敗する。
    */
  def copyArticleBody(fromId: Int, toId: Int): EitherT[Future, ArticleError, Article] = {
    def getArticle(articleId: Int): EitherT[Future, ArticleError, Article] = {
      val result = ArticleApi
        .findBy(articleId)

      EitherT.fromOptionF(result, NotFoundError(articleId))
    }

    def validateNotLocked(article: Article): EitherT[Future, ArticleError, Unit] = {
      article.locked match {
        case true => EitherT.leftT(LockedError(article.articleId))
        case false => EitherT.rightT(())
      }
    }

    def copyAndUpdate(from: Article, to: Article): EitherT[Future, ArticleError, Article] = {
      val merged = to.copy(body = from.body)

      val result = ArticleApi
        .update(merged)

      EitherT.right(result)
    }


    for {
      fromArticle <- getArticle(fromId)
      toArticle <- getArticle(toId)
      _ <- validateNotLocked(toArticle)
      updated <- copyAndUpdate(fromArticle, toArticle)
    } yield updated

  }
}


object ArticleApi {
  val articles: Seq[Article] = Seq(
    Article(1, "Hello", false),
    Article(2, "Bye", false),
    Article(3, "See you.", true)
  )

  def findBy(articleId: Int): Future[Option[Article]] = {
    Future.successful(articles.find(_.articleId == articleId))
  }

  def update(article: Article): Future[Article] = {
    // dummy implementation
    Future.successful(article)
  }
}

case class Article(articleId: Int, body: String, locked: Boolean)

sealed trait ArticleError

case class NotFoundError(articleId: Int) extends ArticleError {
  override def toString: String = s"Article not found: articleId=${articleId}"
}

case class LockedError(articleId: Int) extends ArticleError {
  override def toString: String = s"Article is locked: articleId=${articleId}"
}
