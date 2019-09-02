package com.github.giwiro.app.courier.web

import com.github.giwiro.app.courier.CourierUseCase
import com.github.giwiro.app.courier.response.GetAllCourierResponse
import org.scalatra._

class CourierServlet extends ScalatraServlet {
  get("/couriers") {
    val resp: GetAllCourierResponse = CourierUseCase.getAllCouriers
    views.html.courier.all(resp.couriers)
  }

}
