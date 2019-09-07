package com.github.giwiro.app.admin.product

import java.sql.Connection

import com.github.giwiro.app.admin.product.response.GetAllProductStatesResponse
import com.github.giwiro.database.DatabaseConnectionSupport
import com.github.giwiro.database.dao.{ProductStateDAO}

object ProductUseCase {
  def getAllProductStates = DatabaseConnectionSupport.withDatabaseConnection[GetAllProductStatesResponse] {
    conn: Connection =>
      val productStateDAO = new ProductStateDAO(conn)
      val list = productStateDAO.getAll()
      new GetAllProductStatesResponse(list)
  }
}
