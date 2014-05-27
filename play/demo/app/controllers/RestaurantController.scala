package controllers

import play.api.mvc._
import services.Restaurant

class RestaurantController(restaurant: Restaurant) extends Controller {
  def index = Action {
    Ok(views.html.index(restaurant.orderSteakWithPotatoes()))
  }
}
