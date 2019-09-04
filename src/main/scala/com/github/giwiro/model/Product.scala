package com.github.giwiro.model

class Product(var id: Int,
              var status: ProductState,
              var courier: Int,
              var name: String,
              var url: String,
              var quantity: Int,
              var withBox: Int,
              var deliveryDate: Unit,
              var detail: String,
              var image: String)