package com.github.giwiro.model

class ProductState(var id: Int, var name: String)

object ProductStates {
  val PENDING = 1
  val RECEIVED = 2
  val DELIVERED = 3
}