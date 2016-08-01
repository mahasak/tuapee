package module.booking

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._

case class BookingForm(session: String, sku: String)

object BindBookingForm {
  val form = Form(
    mapping(
      "session" -> of[String]
      , "sku" -> of[String]
    )(BookingForm.apply)(BookingForm.unapply)
  )
}
