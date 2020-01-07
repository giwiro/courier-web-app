package com.github.giwiro.app.courier.web

import com.github.giwiro.app.courier.CourierUseCase
import com.github.giwiro.model.ProductStates
import org.scalatra.forms.FormSupport
import org.scalatra.i18n.I18nSupport
import org.scalatra.{BadRequest, ScalatraServlet}

class CourierServlet extends ScalatraServlet with FormSupport with I18nSupport {
  post("/:courier_id/product/list/archive") {
    val courierId: Option[Int] = params.getAs[Int]("courier_id")
    if (courierId.isEmpty) {
      BadRequest
    } else {
      CourierUseCase.deleteDeliveredProducts(courierId.get)
      redirect(s"/courier/${courierId.get}/product/list/delivered")(request, response)
    }
  }

  get("/:courier_id/product/list/:product_state") {
    val courierId: Option[Int] = params.getAs[Int]("courier_id")
    val productState: Option[String] = params.getAs[String]("product_state")
    val validProductState: Boolean = ProductStates.validateStateName(productState.getOrElse(""))
    if (courierId.isEmpty || productState.isEmpty || !validProductState) {
      BadRequest
    } else {
      val productStateId = ProductStates.getStateId(productState.get)
      val resp = CourierUseCase.getProductList(courierId.get, productStateId.get)
      views.html.courier.productList(courierId.get, resp.courier, resp.products, productState.get)
    }
  }

  post("/:courier_id/product/:product_id/receive") {
    validate(Validator.changeStateForm)(
      errors => {
        errors.foreach { e => println(e) }
        BadRequest()
      },
      form => {
        val courierId: Option[Int] = params.getAs[Int]("courier_id")
        val productId: Option[Int] = params.getAs[Int]("product_id")
        if (courierId.isEmpty || productId.isEmpty) {
          BadRequest
        } else {
          CourierUseCase.receiveProduct(productId.get)
          redirect(s"/courier/${courierId.get}/product/list/pending#${form.scrollToProductId.get}")(request, response)
        }
      }
    )
  }

  post("/:courier_id/product/:product_id/delete") {
    val courierId: Option[Int] = params.getAs[Int]("courier_id")
    val productId: Option[Int] = params.getAs[Int]("product_id")
    if (courierId.isEmpty || productId.isEmpty) {
      BadRequest
    } else {
      CourierUseCase.deleteProduct(courierId.get)
      redirect(s"/courier/${courierId.get}/product/list/pending")(request, response)
    }
  }

  post("/:courier_id/product/:product_id/deliver") {
    validate(Validator.changeStateForm)(
      errors => {
        errors.foreach { e => println(e) }
        BadRequest()
      },
      form => {
        val courierId: Option[Int] = params.getAs[Int]("courier_id")
        val productId: Option[Int] = params.getAs[Int]("product_id")
        if (courierId.isEmpty || productId.isEmpty) {
          BadRequest
        } else {
          CourierUseCase.deliverProduct(productId.get)
          redirect(s"/courier/${courierId.get}/product/list/received#${form.scrollToProductId.get}")(request, response)
        }
      }
    )
  }
}
