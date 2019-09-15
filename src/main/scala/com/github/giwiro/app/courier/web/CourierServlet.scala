package com.github.giwiro.app.courier.web

import com.github.giwiro.app.courier.CourierUseCase
import com.github.giwiro.model.ProductStates
import org.scalatra.{BadRequest, ScalatraServlet}

class CourierServlet extends ScalatraServlet {
  get("/:courier_id/product/list/:product_state") {
    val courierId: Option[Int] = params.getAs[Int]("courier_id")
    val productState: Option[String] = params.getAs[String]("product_state")
    val validProductState: Boolean = ProductStates.validateStateName(productState.getOrElse(""))
    if (courierId == None || productState == None || !validProductState) {
      BadRequest()
    } else {
      val productStateId = ProductStates.getStateId(productState.getOrElse(""))
      println("productStateId: " + productStateId)
      val resp = CourierUseCase.getProductList(courierId.getOrElse(0), productStateId.getOrElse(0))
      views.html.courier.productList(courierId.getOrElse(0), resp.products, productState.getOrElse(""))
    }
  }
}
