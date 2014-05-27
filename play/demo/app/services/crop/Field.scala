package services.crop

case class Field() {
  private var harvestCount: Int = 0

  def harvest() {
    harvestCount += 1
  }

  override def toString = s"Field[harvest=$harvestCount]"
}
