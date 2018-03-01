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

  "carts with just apples" should "have every other apple free" in {
    assert(priceCartInPence(Seq.fill(2)("Apple")) == priceProductInPence(Apple))
    assert(priceCartInPence(Seq.fill(3)("Apple")) == priceProductInPence(Apple) * 2)
    assert(priceCartInPence(Seq.fill(4)("Apple")) == priceProductInPence(Apple) * 2)
    assert(priceCartInPence(Seq.fill(5)("Apple")) == priceProductInPence(Apple) * 3)
  }

  "carts with just oranges" should "have every third oramge free" in {
    assert(priceCartInPence(Seq.fill(3)("Orange")) == priceProductInPence(Orange) * 2)
    assert(priceCartInPence(Seq.fill(4)("Orange")) == priceProductInPence(Orange) * 3)
    assert(priceCartInPence(Seq.fill(5)("Orange")) == priceProductInPence(Orange) * 4)
    assert(priceCartInPence(Seq.fill(6)("Orange")) == priceProductInPence(Orange) * 4)
    assert(priceCartInPence(Seq.fill(7)("Orange")) == priceProductInPence(Orange) * 5)
  }

  "carts with both apples and oranges" should "cost the sum of two single-product carts" in {
    val cart = Seq(Apple, Apple, Orange, Orange, Apple, Orange, Orange, Apple, Orange, Apple).map(_.toString)
    assert(priceCartInPence(cart) == priceCartInPence(cart.filter(_ == Apple.toString)) + priceCartInPence(cart.filter(_ == Orange.toString)))
  }

}
