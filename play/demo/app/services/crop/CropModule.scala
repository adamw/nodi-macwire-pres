package services.crop

import com.softwaremill.macwire.MacwireMacros._

trait CropModule {
  lazy val field      = wire[Field]
  lazy val potatoFarm = wire[PotatoFarm]
  lazy val digger     = wire[Digger]
}
