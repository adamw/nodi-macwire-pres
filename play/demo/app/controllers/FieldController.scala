package controllers

import play.api.mvc.{Action, Controller}
import services.crop.Field

class FieldController(field: Field) extends Controller {
  def harvest = Action {
    field.harvest()
    Ok(views.html.index(field.toString))
  }
}
