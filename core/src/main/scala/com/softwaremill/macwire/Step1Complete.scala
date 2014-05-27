package com.softwaremill.macwire

/**
 * - object graph
 * - case class -> toString()
 * - manual DI
 */
object Step1Complete extends App with Macwire {
  case class Field()
  case class Digger()
  case class PotatoFarm(field: Field, digger: Digger)

  case class CowPasture()
  case class Meatery(cowPasture: CowPasture)

  case class Restaurant(potatoFarm: PotatoFarm, meatery: Meatery) {
    def orderSteakWithPotatoes() = {
      println(s"Welcome to $this. Here's your order.")
    }
  }

  val field = new Field()
  val digger = new Digger()
  val potatoFarm = new PotatoFarm(field, digger)

  val cowPasture = new CowPasture()
  val meatery = new Meatery(cowPasture)

  val restaurant = new Restaurant(potatoFarm, meatery)

  restaurant.orderSteakWithPotatoes()
}
