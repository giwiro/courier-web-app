package com.github.giwiro.app.courier.web

import com.github.giwiro.app.courier.CourierUseCase
import com.github.giwiro.model.ProductStates
import org.scalatra.{BadRequest, ScalatraServlet}

class CourierServlet extends ScalatraServlet {
  get("/:courier_id/product/list/:product_state") {
    val courierId: Option[Int] = params.getAs[Int]("courier_id")
    val productState: Option[String] = params.getAs[String]("product_state")
    val validProductState: Boolean = ProductStates.validateStateName(productState.getOrElse(""))
    if (!courierId.isDefined || !productState.isDefined || !validProductState) {
      BadRequest
    } else {
      val productStateId = ProductStates.getStateId(productState.get)
      val resp = CourierUseCase.getProductList(courierId.get, productStateId.get)
      views.html.courier.productList(courierId.get, resp.products, productState.get)
    }
  }

  post("/:courier_id/product/:product_id/receive") {
    val courierId: Option[Int] = params.getAs[Int]("courier_id")
    val productId: Option[Int] = params.getAs[Int]("product_id")
    if (!courierId.isDefined || !productId.isDefined) {
      BadRequest
    } else {
      CourierUseCase.receiveProduct(productId.get)
      redirect(s"/courier/${courierId.get}/product/list/pending")(request, response)
    }
  }

  post("/:courier_id/product/:product_id/delete") {
    val courierId: Option[Int] = params.getAs[Int]("courier_id")
    val productId: Option[Int] = params.getAs[Int]("product_id")
    if (!courierId.isDefined || !productId.isDefined) {
      BadRequest
    } else {
      CourierUseCase.deleteProduct(courierId.get)
      redirect(s"/courier/${courierId.get}/product/list/pending")(request, response)
    }
  }

  post("/:courier_id/product/:product_id/deliver") {
    val courierId: Option[Int] = params.getAs[Int]("courier_id")
    val productId: Option[Int] = params.getAs[Int]("product_id")
    if (!courierId.isDefined || !productId.isDefined) {
      BadRequest
    } else {
      CourierUseCase.deliverProduct(productId.get)
      redirect(s"/courier/${courierId.get}/product/list/received")(request, response)
    }
  }
}
