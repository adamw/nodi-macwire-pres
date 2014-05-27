package services

import services.livestock.Meatery
import services.crop.PotatoFarm

case class Restaurant(potatoFarm: PotatoFarm, meatery: Meatery) {
  def orderSteakWithPotatoes() = {
    s"Welcome to $this. Here's your order."
  }
}
