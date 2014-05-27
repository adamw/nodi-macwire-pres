package com.softwaremill.macwire

import com.softwaremill.macwire.aop.{NoOpInterceptor, ProxyingInterceptor, Interceptor}

/**
 * - add an abstract interceptor
 * - implement using a proxying interceptor
 * - for testing: no-op interceptor, override potato farm
 */
object Step5Complete extends App with Macwire {
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
    def potatoFarm: PotatoFarm = wire[ThreeDPrintingPotatoFarm]
  }

  // ---

  trait LivestockModule {
    lazy val cowPasture = wire[CowPasture]
    lazy val meatery = wire[Meatery]

    def potatoFarm: PotatoFarm
  }

  trait RestaurantModule extends CropModule with LivestockModule {
    lazy val restaurant = transactional(wire[Restaurant])

    def transactional: Interceptor
  }

  val app2 = new ModernCropModule with LivestockModule with RestaurantModule {
    lazy val transactional = ProxyingInterceptor { ctx =>
      println("About to invoke restaurant")
      val result = ctx.proceed()
      println("Restaurant invoked!")
      result
    }
  }

  app2.restaurant.orderSteakWithPotatoes()

  // ---

  val appTesting = new ModernCropModule with LivestockModule with RestaurantModule {
    lazy val transactional = NoOpInterceptor
    override val potatoFarm = new PotatoFarm { } // mock
  }

  appTesting.restaurant.orderSteakWithPotatoes()
}
