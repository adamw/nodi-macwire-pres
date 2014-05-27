package com.softwaremill.macwire

/**
 * - potato farm -> trait, two impls
 * - two crop modules, interface-module
 * - extends ... dep -> now better, no concrete impl
 * - two apps
 */
object Step4Complete extends App with Macwire {
  case class Field()
  case class Digger()

  trait PotatoFarm
  case class TraditionalPotatoFarm(field: Field, digger: Digger) extends PotatoFarm {
    println("New potato farm! Rejoice!")
  }

  case class ThreeDPrintingPotatoFarm() extends PotatoFarm

  case class CowPasture(potatoFarm: PotatoFarm)
  case class Meatery(cowPasture: CowPasture)

  case class Restaurant(potatoFarm: PotatoFarm, meatery: Meatery) {
    def orderSteakWithPotatoes() = {
      println(s"Welcome to $this. Here's your order.")
    }
  }

  // ---

  trait CropModule {
    def potatoFarm: PotatoFarm
  }

  trait TraditionalCropModule extends CropModule {
    lazy val field = wire[Field]
    def potatoFarm = wire[TraditionalPotatoFarm]
    lazy val digger = wire[Digger]
  }

  trait ModernCropModule extends CropModule {
    def potatoFarm = wire[ThreeDPrintingPotatoFarm]
  }

  // ---

  trait LivestockModule {
    lazy val cowPasture = wire[CowPasture]
    lazy val meatery = wire[Meatery]

    def potatoFarm: PotatoFarm
  }

  trait RestaurantModule extends CropModule with LivestockModule {
    lazy val restaurant = wire[Restaurant]
  }

  val app1 = new TraditionalCropModule with LivestockModule with RestaurantModule
  app1.restaurant.orderSteakWithPotatoes()

  val app2 = new ModernCropModule with LivestockModule with RestaurantModule
  app2.restaurant.orderSteakWithPotatoes()
}
