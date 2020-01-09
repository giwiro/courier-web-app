package com.github.giwiro.app.courier

import java.sql.Connection

import com.github.giwiro.app.courier.response.{GetProductListResponse}
import com.github.giwiro.database.DatabaseConnectionSupport
import com.github.giwiro.database.dao.{CourierDAO, ProductDAO}
import com.github.giwiro.model.{Product, ProductStates}

object CourierUseCase {
  def deleteDeliveredProducts(courierId: Int): Unit =
    DatabaseConnectionSupport.withDatabaseConnection[Unit] {
      conn: Connection =>
        val courierDAO = new CourierDAO(conn)
        courierDAO.archiveDelivered(courierId)
    }

  def getProductList(courierId: Int, stateId: Int): GetProductListResponse =
    DatabaseConnectionSupport.withDatabaseConnection[GetProductListResponse] {
      conn: Connection =>
        val productDAO = new ProductDAO(conn)
        val courierDAO = new CourierDAO(conn)
        val list = productDAO.getAllByCourier(courierId, stateId)
        val courier = courierDAO.get(courierId)
        new GetProductListResponse(list, courier.orNull)
    }

  def receiveProduct(productId: Int): Unit =
    DatabaseConnectionSupport.withDatabaseConnection[Unit] {
      conn: Connection =>
        val productDAO = new ProductDAO(conn)
        val product: Option[Product] = productDAO.getBytId(productId)
        if (product.isDefined && product.get.id.isDefined && product.get.stateId == ProductStates.PENDING) {
          val productDAO = new ProductDAO(conn)
          productDAO.changeState(productId, ProductStates.RECEIVED)
        }
    }

  def deleteProduct(productId: Int): Unit =
    DatabaseConnectionSupport.withDatabaseConnection[Unit] {
      conn: Connection =>
        val productDAO = new ProductDAO(conn)
        productDAO.deleteProduct(productId)
    }

  def checkProduct(productId: Int): Unit =
    DatabaseConnectionSupport.withDatabaseConnection[Unit] {
      conn: Connection =>
        val productDAO = new ProductDAO(conn)
        val product: Option[Product] = productDAO.getBytId(productId)
        if (product.isDefined && product.get.id.isDefined && product.get.stateId == ProductStates.RECEIVED) {
          val productDAO = new ProductDAO(conn)
          productDAO.changeState(productId, ProductStates.CHECKED)
        }
    }

  def deliverProduct(productId: Int): Unit =
    DatabaseConnectionSupport.withDatabaseConnection[Unit] {
      conn: Connection =>
        val productDAO = new ProductDAO(conn)
        val product: Option[Product] = productDAO.getBytId(productId)
        if (product.isDefined && product.get.id.isDefined && product.get.stateId == ProductStates.CHECKED) {
          val productDAO = new ProductDAO(conn)
          productDAO.changeState(productId, ProductStates.DELIVERED)
        }
    }
}
