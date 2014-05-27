package services.livestock

import com.softwaremill.macwire.MacwireMacros._

trait LivestockModule {
  lazy val cowPasture = wire[CowPasture]
  lazy val meatery    = wire[Meatery]
}
