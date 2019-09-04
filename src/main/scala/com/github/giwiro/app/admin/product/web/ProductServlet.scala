package com.github.giwiro.app.admin.product.web

import com.github.giwiro.app.admin.product.ProductUseCase
import com.github.giwiro.app.admin.product.response.GetAllProductStatesResponse
import org.scalatra.ScalatraServlet

class ProductServlet extends ScalatraServlet {
  get("/new-product") {
    val resp: GetAllProductStatesResponse = ProductUseCase.getAllProductStates
    views.html.admin.product.add(resp.states)
  }
}
