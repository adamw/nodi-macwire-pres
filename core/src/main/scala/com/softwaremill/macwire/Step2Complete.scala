package com.softwaremill.macwire

/**
 * - change digger/pf order -> lazy vals
 * - introduce wire[]
 * - add a dependency
 * - scoping: def
 */
object Step2Complete extends App with Macwire {
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

  lazy val field = wire[Field]
  def potatoFarm = wire[PotatoFarm]
  lazy val digger = wire[Digger]

  lazy val cowPasture = wire[CowPasture]
  lazy val meatery = wire[Meatery]

  lazy val restaurant = wire[Restaurant]

  restaurant.orderSteakWithPotatoes()
}
