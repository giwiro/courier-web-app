package com.github.giwiro.model

class ProductState(var id: Int, var name: String)

object ProductStates {
  val PENDING = 1
  val RECEIVED = 2
  val DELIVERED = 3

  def getStateId(name: String): Option[Int] = name match {
    case "pending" => Some(PENDING)
    case "received" => Some(RECEIVED)
    case "delivered" => Some(DELIVERED)
    case _ => None
  }

  def validateStateName(name: String): Boolean = name match {
    case "pending" => true
    case "received" => true
    case "delivered" => true
    case _ => false
  }
}