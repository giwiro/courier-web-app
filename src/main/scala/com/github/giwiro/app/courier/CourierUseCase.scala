package com.github.giwiro.app.courier

import java.sql.Connection

import com.github.giwiro.app.courier.response.GetAllCourierResponse
import com.github.giwiro.model.Courier
import com.github.giwiro.database.DatabaseConnectionSupport
import com.github.giwiro.database.dao.CourierDAO

object CourierUseCase {

  def getAllCouriers = DatabaseConnectionSupport.withDatabaseConnection[GetAllCourierResponse] {
    conn: Connection =>
      var courierDAO: CourierDAO = new CourierDAO(conn)
      var list: List[Courier] = courierDAO.getAll()
      new GetAllCourierResponse(list)
  }
}
