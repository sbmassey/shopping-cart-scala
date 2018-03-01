package shoppingcart

import org.scalatest._


class PricingSpec extends FlatSpec {

  import Pricing._

  "an empty cart" should "have zero price" in {
    assert(priceCartInPence(Seq.empty) == 0)
  }

  "a cart with an invalid product" should "throw" in {
    assertThrows[NoSuchElementException](priceCartInPence(Seq("NOT A PRODUCTT")))
    assertThrows[NoSuchElementException](priceCartInPence(Seq("Apple", "NOT A PRODUCTT")))
    assertThrows[NoSuchElementException](priceCartInPence(Seq("NOT A PRODUCTT", "Orange", "Apple")))
  }

  "a cart with a single item" should "cost just that item" in {
    assert(priceCartInPence(Seq("Apple")) == priceProductInPence(Apple))
    assert(priceCartInPence(Seq("Orange")) == priceProductInPence(Orange))
  }

  "a cart with multiple items" should "cost the sum of those items" in {

    val items = Seq(Apple, Orange, Apple)
    assert(priceCartInPence(items.map(_.toString)) == items.map(priceProductInPence).sum)

    val items2 = Seq(Apple, Apple, Orange, Orange, Apple)
    assert(priceCartInPence(items2.map(_.toString)) == items2.map(priceProductInPence).sum)

  }

}
