package module.booking

import akka.actor.Actor
import com.typesafe.scalalogging.LazyLogging

class BookingActor extends Actor with LazyLogging {

  var stock = 1000

  def receive = {
    case session: String => {
      if (stock != 0) {
        stock = stock - 1
      } else {
        logger.info("Stock Empty")
      }
    }
  }

}