package shoppingcart


object Pricing {

  def priceCartInPence(cart: Seq[String]): Int = {
    val products = cart map Product.byName
    priceProductsInPence(products)
  }

  def priceProductsInPence(cart: Seq[Product]): Int = {
    val prices = cart map priceProductInPence
    prices.sum
  }

  def priceProductInPence(item: Product): Int = item match {
    case Orange => 25
    case Apple => 60
  }

}


