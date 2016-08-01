package module.booking

import javax.inject.{Inject, Singleton}

import akka.actor.{ActorSystem, Props}
import com.typesafe.scalalogging.LazyLogging
import module.common.ExtendAction
import play.api.mvc.Controller

@Singleton
class BookingController @Inject()(system: ActorSystem) extends Controller
  with ExtendAction
  with LazyLogging {

  val actorSKU0500 = system.actorOf(Props[BookingActor], name = "SKU500")
  val actorSKU1500 = system.actorOf(Props[BookingActor], name = "SKU1500")
  val actorSKU2500 = system.actorOf(Props[BookingActor], name = "SKU2500")

  def read = AnalyticAction { implicit request =>
    BindBookingForm.form.bindFromRequest.fold(
      formWithErrors => Ok("form error")
      , form => try {

        form.sku match {
          case "SKU500" => actorSKU0500 ! form.session
          case "SKU1500" => actorSKU1500 ! form.session
          case "SKU2500" => actorSKU2500 ! form.session
          case _ =>
        }

        Ok("true")
      } catch {
        case e: Throwable => {
          logger.error(e.getMessage(), e)
          Ok(e.getMessage())
        }
      }
    )
  }

}
