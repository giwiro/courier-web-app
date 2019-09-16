package com.github.giwiro.app.admin.product.web

import com.github.giwiro.app.admin.courier.CourierUseCase
import com.github.giwiro.app.admin.courier.request.GetCourierRequest
import com.github.giwiro.app.admin.product.ProductUseCase
import com.github.giwiro.app.admin.product.request.PostNewProduct
import org.scalatra.forms.FormSupport
import org.scalatra.i18n.I18nSupport
import org.scalatra.{BadRequest, NotFound, ScalatraServlet}

class ProductServlet extends ScalatraServlet with FormSupport with I18nSupport {
  get("/new/courier/:courier_id") {
    val courierId: Option[Int] = params.getAs[Int]("courier_id")
    if (!courierId.isDefined) {
      BadRequest()
    } else {
      val request = new GetCourierRequest(courierId.get)
      val getCourierResponse = CourierUseCase.getCourier(request)
      if (!getCourierResponse.courier.isDefined) {
        NotFound()
      } else {
        views.html.admin.product.add(getCourierResponse.courier.orNull)
      }
    }
  }

  post("/new/courier/:courier_id") {
    validate(Validator.newCourierPageForm)(
      errors => {
        errors.foreach { e => println(e) }
        BadRequest()
      },
      form => {
        val courierId: Option[Int] = params.getAs[Int]("courier_id")
        if (!courierId.isDefined) {
          BadRequest()
        } else {
          val req = new PostNewProduct(
            courierId.get,
            form.name,
            form.url,
            form.quantity,
            form.withBox match {
              case Some(withBox) => if (withBox == "on") 1 else 0
              case None => 0
            },
            form.deliveryDate,
            form.detail,
            form.image)
          val resp = ProductUseCase.addProduct(req)
          redirect(s"/courier/${resp.product.courierId}/product/list/pending")(request, response)
        }
      }
    )
  }

}
