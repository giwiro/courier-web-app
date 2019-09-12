package com.github.giwiro.app.admin.product.request

class PostNewProduct(val courierId: Int,
                     val name: String,
                     val url: String,
                     val quantity: Int,
                     val withBox: Int,
                     val deliveryDate: String,
                     val detail: Option[String],
                     val image: String)
