import controllers.{FieldController, RestaurantController}
import com.softwaremill.macwire.MacwireMacros._
import services.crop.CropModule
import services.Restaurant
import services.livestock.LivestockModule

object Application extends CropModule with LivestockModule {
  lazy val mainController    = wire[RestaurantController]
  lazy val fieldController   = wire[FieldController]
  lazy val restaurant        = wire[Restaurant]
}
