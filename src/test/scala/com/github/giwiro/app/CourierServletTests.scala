package com.github.giwiro.app

import com.github.giwiro.app.admin.courier.web.CourierServlet
import org.scalatra.test.scalatest._

class CourierServletTests extends ScalatraFunSuite {

  addServlet(classOf[CourierServlet], "/*")

  test("GET / on CourierServlet should return status 200") {
    get("/") {
      status should equal (200)
    }
  }

}
