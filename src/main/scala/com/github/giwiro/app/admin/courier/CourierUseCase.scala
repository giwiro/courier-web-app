package com.github.giwiro.app.admin.courier

import java.sql.Connection

import com.github.giwiro.app.admin.courier.request.GetCourierRequest
import com.github.giwiro.app.admin.courier.response.{GetAllCourierResponse, GetCourierResponse}
import com.github.giwiro.database.DatabaseConnectionSupport
import com.github.giwiro.database.dao.CourierDAO

object CourierUseCase {
  def getCourier(request: GetCourierRequest) =
    DatabaseConnectionSupport.withDatabaseConnection[GetCourierResponse] {
      conn: Connection =>
        var courierDAO = new CourierDAO(conn)
        val courier = courierDAO.get(request.courierId)
        new GetCourierResponse(courier)
    }

  def getAllCouriers = DatabaseConnectionSupport.withDatabaseConnection[GetAllCourierResponse] {
    conn: Connection =>
      val courierDAO = new CourierDAO(conn)
      val list = courierDAO.getAll()
      new GetAllCourierResponse(list)
  }
}
