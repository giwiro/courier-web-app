package com.github.giwiro.app.courier.web

import org.scalatra.{BadRequest, ScalatraServlet}

class CourierServlet extends ScalatraServlet {
  get("/:courier_id/product/list/:product_state") {
    val courierId: Option[Int] = params.getAs[Int]("courier_id")
    val productState: Option[String] = params.getAs[String]("product_state")
    if (courierId == None || productState == None) {
      BadRequest()
    } else {
      views.html.courier.productList(courierId.getOrElse(0), productState.getOrElse(""))
    }
  }
}
