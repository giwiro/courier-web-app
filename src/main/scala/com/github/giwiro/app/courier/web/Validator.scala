
package com.github.giwiro.app.courier.web

import org.scalatra.forms.{mapping, label, text, number, required, optional}

case class ChangeStateValidation(val scrollToProductId: Option[String])

object Validator {
  val changeStateForm = mapping(
    "scrollToProductId" -> label("scrollToProductId", optional(text())),
  )(ChangeStateValidation.apply)
}
