package com.github.giwiro.app.admin.courier.web

import com.github.giwiro.app.admin.courier.CourierUseCase
import com.github.giwiro.app.admin.courier.response.GetAllCourierResponse
import org.scalatra._

class CourierServlet extends ScalatraServlet {
  get("/couriers") {
    val resp: GetAllCourierResponse = CourierUseCase.getAllCouriers
    views.html.admin.courier.all(resp.couriers)
  }

}
