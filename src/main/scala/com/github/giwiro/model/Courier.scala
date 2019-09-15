package com.github.giwiro.model

class Courier(var id: Int,
              var name: String,
              var image: String,
              var totalPending: Option[Int],
              var totalReceived: Option[Int],
              var totalDelivered: Option[Int])
