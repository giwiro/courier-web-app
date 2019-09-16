package com.github.giwiro.app.courier

import java.sql.Connection

import com.github.giwiro.app.courier.response.GetProductListResponse
import com.github.giwiro.database.DatabaseConnectionSupport
import com.github.giwiro.database.dao.ProductDAO
import com.github.giwiro.model.{Product, ProductStates}

object CourierUseCase {
  def getProductList(courierId: Int, stateId: Int): GetProductListResponse =
    DatabaseConnectionSupport.withDatabaseConnection[GetProductListResponse] {
      conn: Connection =>
        val productDAO = new ProductDAO(conn)
        val list = productDAO.getAllByCourier(courierId, stateId)
        new GetProductListResponse(list)
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

  def deliverProduct(productId: Int): Unit =
    DatabaseConnectionSupport.withDatabaseConnection[Unit] {
      conn: Connection =>
        val productDAO = new ProductDAO(conn)
        val product: Option[Product] = productDAO.getBytId(productId)
        if (product.isDefined && product.get.id.isDefined && product.get.stateId == ProductStates.RECEIVED) {
          val productDAO = new ProductDAO(conn)
          productDAO.changeState(productId, ProductStates.DELIVERED)
        }
    }
}
