package com.github.giwiro.app.courier

import java.sql.Connection

import com.github.giwiro.app.courier.response.GetProductListResponse
import com.github.giwiro.database.DatabaseConnectionSupport
import com.github.giwiro.database.dao.ProductDAO

object CourierUseCase {
  def getProductList(courierId: Int, stateId: Int): GetProductListResponse =
    DatabaseConnectionSupport.withDatabaseConnection[GetProductListResponse] {
      conn: Connection =>
        val productDAO = new ProductDAO(conn)
        val list = productDAO.getAllByCourier(courierId, stateId)
        new GetProductListResponse(list)
    }
}
