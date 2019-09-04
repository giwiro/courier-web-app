package com.github.giwiro.app.admin.courier

import java.sql.Connection

import com.github.giwiro.app.admin.courier.response.GetAllCourierResponse
import com.github.giwiro.model.Courier
import com.github.giwiro.database.DatabaseConnectionSupport
import com.github.giwiro.database.dao.CourierDAO

object CourierUseCase {

  def getAllCouriers = DatabaseConnectionSupport.withDatabaseConnection[GetAllCourierResponse] {
    conn: Connection =>
      var courierDAO = new CourierDAO(conn)
      var list = courierDAO.getAll()
      new GetAllCourierResponse(list)
  }
}
