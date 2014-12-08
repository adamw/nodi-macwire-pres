package com.softwaremill.macwire

object QualifiersComplete extends App with Macwire {
  /*

  A proposition on how classifiers can be implemented in MacWire. Based on @milessabin's https://gist.github.com/milessabin/89c9b47a91017973a35f
  */

  // Infrastructure: would come in the library
  // note that no changes in the macro are necessary. The qualifiers are purely type-based
  type Tag[U] = { type Tag = U }
  type @@[T, U] = T with Tag[U]
  type Tagged[T, U] = T with Tag[U]
  implicit class Tagger[T](t: T) {
    def taggedWith[U]: T @@ U = t.asInstanceOf[T @@ U]
  }

  // Example usage:
  class Berry()
  trait Black
  trait Blue

  class Basket(blueberry: Berry @@ Blue, blackberry: Berry @@ Black) {
    def consume(b: Berry) {}
    consume(blueberry) // can be used as "just" berry
    consume(blackberry)
  }
  // or
  class Basket2(blueberry: Berry Tagged Blue, blackberry: Berry Tagged Black)

  lazy val blueberry = wire[Berry].taggedWith[Blue]
  lazy val blackberry = wire[Berry].taggedWith[Black]
  lazy val basket = wire[Basket]
  lazy val basket2 = wire[Basket2]

  // Yes/no/would prefer annotations?
  // One downside is that when declaring dependencies, you would need to use a type from Macwire (@@)
}
