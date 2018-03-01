package shoppingcart


object Pricing {

  def priceCartInPence(cart: Seq[String]): Int = {
    val products = cart map Product.byName
    priceProductsInPence(products)
  }

  def priceProductsInPence(cart: Seq[Product]): Int = {
    val pricesByProduct = Product.products.map { product =>
      val count = cart.count(_ == product)
      val freeCount = {
        val threshold = specialOfferThreshold(product)
        if (threshold == 0) 0
        else count / (threshold + 1)
      }
      (count - freeCount) * priceProductInPence(product)
    }
    pricesByProduct.sum
  }

  def priceProductInPence(item: Product): Int = item match {
    case Orange => 25
    case Apple => 60
  }

  // Number of items after which the next item is free
  def specialOfferThreshold(item: Product): Int = item match {
    case Orange => 2
    case Apple => 1
  }
}


