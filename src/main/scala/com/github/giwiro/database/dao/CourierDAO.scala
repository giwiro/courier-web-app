package com.github.giwiro.database.dao

import java.sql.{Connection, ResultSet}
import com.github.giwiro.model.Courier

class CourierDAO(conn: Connection) {
  def getAll(): List[Courier] = {
    val s = conn.createStatement()
    val q = "SELECT * FROM courier;"
    val rs = s.executeQuery(q)
    mapToCourierList(rs)
  }

  private def mapToCourierList(rs: ResultSet): List[Courier] = {
    Iterator
      .continually(rs.next)
      .takeWhile(identity)
      .map { _ =>
        new Courier(
          id = rs.getInt(1),
          name = rs.getString(2),
          image = rs.getString(3))
      }
      .toList
  }
}
