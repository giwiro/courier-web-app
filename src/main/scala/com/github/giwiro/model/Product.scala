package com.github.giwiro.model

class Product(var id: Option[Int],
              var stateId: Int,
              var state: Option[ProductState],
              var courierId: Int,
              var name: String,
              var url: String,
              var quantity: Int,
              var withBox: Int,
              var deliveryDate: String,
              var detail: Option[String],
              var image: String)