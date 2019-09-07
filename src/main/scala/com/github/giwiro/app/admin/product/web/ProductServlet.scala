package com.github.giwiro.app.admin.product.web

import com.github.giwiro.app.admin.courier.CourierUseCase
import com.github.giwiro.app.admin.courier.request.GetCourierRequest
import org.scalatra.{BadRequest, NotFound, ScalatraServlet}

class ProductServlet extends ScalatraServlet {
  get("/new/courier/:courier_id") {
    val courierId: Option[Int] = params.getAs[Int]("courier_id")
    if (courierId == None) {
      BadRequest()
    }else {
      val request = new GetCourierRequest(courierId.getOrElse(0))
      val getCourierResponse = CourierUseCase.getCourier(request)
      if (getCourierResponse.courier == None) {
        NotFound()
      }else {
        views.html.admin.product.add(getCourierResponse.courier.orNull)
      }
    }
  }
}
