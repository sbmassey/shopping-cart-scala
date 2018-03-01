package shoppingcart

sealed trait Product
case object Apple extends Product
case object Orange extends Product

object Product {

  val products = List(Apple, Orange)

  val byName: Map[String,Product] = products.map(p => (p.toString, p)).toMap

}

