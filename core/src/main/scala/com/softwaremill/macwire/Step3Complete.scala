package com.softwaremill.macwire

/**
 * - 3 modules
 * - dependency through abstract member
 * - dependency through extending traits
 */
object Step3Complete extends App with Macwire {
  case class Field()
  case class Digger()
  case class PotatoFarm(field: Field, digger: Digger) {
    println("New potato farm! Rejoice!")
  }

  case class CowPasture(potatoFarm: PotatoFarm)
  case class Meatery(cowPasture: CowPasture)

  case class Restaurant(potatoFarm: PotatoFarm, meatery: Meatery) {
    def orderSteakWithPotatoes() = {
      println(s"Welcome to $this. Here's your order.")
    }
  }

  trait CropModule {
    lazy val field = wire[Field]
    def potatoFarm = wire[PotatoFarm]
    lazy val digger = wire[Digger]
  }

  trait LivestockModule {
    lazy val cowPasture = wire[CowPasture]
    lazy val meatery = wire[Meatery]

    def potatoFarm: PotatoFarm
  }

  trait RestaurantModule extends CropModule with LivestockModule {
    lazy val restaurant = wire[Restaurant]
  }

  val app = new CropModule with LivestockModule with RestaurantModule
  app.restaurant.orderSteakWithPotatoes()
}
