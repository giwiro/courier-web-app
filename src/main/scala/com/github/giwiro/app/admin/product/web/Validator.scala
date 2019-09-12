
package com.github.giwiro.app.admin.product.web

import org.scalatra.forms.{mapping, label, text, number, required, optional}

case class NewCourierPageValidation(val name: String,
                                    val url: String,
                                    val quantity: Int,
                                    val withBox: Option[String],
                                    val deliveryDate: String,
                                    val detail: Option[String],
                                    val image: String)

object Validator {
  val newCourierPageForm = mapping(
    // "courierId" -> label("CourierId", number())
    "name" -> label("name", text(required)),
    "url" -> label("url", text(required)),
    "quantity" -> label("quantity", number(required)),
    "withBox" -> label("withBox", optional(text())),
    "deliveryDate" -> label("deliveryDate", text(required)),
    "detail" -> label("detail", optional(text())),
    "image" -> label("image", text(required)),
  )(NewCourierPageValidation.apply)
}
