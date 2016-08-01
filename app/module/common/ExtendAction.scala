package module.common

import java.util.concurrent.TimeUnit

import com.google.common.base.Stopwatch
import com.typesafe.scalalogging.LazyLogging
import play.api.mvc._

import scala.concurrent.Future

case class AnalyticRequest[A](request: Request[A], account: Option[String]) extends WrappedRequest[A](request)

trait ExtendAction extends LazyLogging {

  def AnalyticAction = new ActionBuilder[AnalyticRequest] {
    override def invokeBlock[A](request: Request[A]
                                , block: (AnalyticRequest[A]) => Future[Result]) = processTime(request) {
      block(AnalyticRequest(request, None))
    }
  }

  private def processTime[R, A](request: Request[A])(block: => R): R = {
    val stopwatch = Stopwatch.createStarted()
    try {
      block
    } finally {
      logger.info(s"${request.method} ${request.uri} in ${stopwatch.stop.elapsed(TimeUnit.MILLISECONDS)} ms.")
    }
  }

}
