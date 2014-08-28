import com.softwaremill.macwire.Macwire
import play.api.GlobalSettings

object Global extends GlobalSettings with Macwire {
  val instanceLookup = wiredInModule(Application)

  override def getControllerInstance[A](controllerClass: Class[A]) =
    instanceLookup.lookupSingleOrThrow(controllerClass)
}
