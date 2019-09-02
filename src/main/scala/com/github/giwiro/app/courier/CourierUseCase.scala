package com.github.giwiro.app.courier

import java.sql.Connection

import com.github.giwiro.app.courier.response.GetAllCourierResponse
import com.github.giwiro.model.Courier
import com.github.giwiro.database.DatabaseConnectionSupport

object CourierUseCase {

  def getAllCouriers = DatabaseConnectionSupport.withDatabaseConnection[GetAllCourierResponse] {
    conn: Connection =>
      var s = conn.createStatement()
      var q = "SELECT * FROM courier;"
      var rs = s.executeQuery(q)
      var list: List[Courier] = Iterator
        .continually(rs.next)
        .takeWhile(identity)
        .map { _ => new Courier(rs.getInt(1), rs.getString(2), rs.getString(3)) }
        .toList
      new GetAllCourierResponse(list)
  }
}
