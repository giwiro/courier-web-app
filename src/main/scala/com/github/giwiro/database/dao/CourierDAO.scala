package com.github.giwiro.database.dao

import java.sql.{Connection, ResultSet}
import com.github.giwiro.model.Courier

class CourierDAO(conn: Connection) {
  def get(courierId: Int): Option[Courier] = {
    val q = "SELECT * FROM courier WHERE courier.id = ?;"
    val stmt = conn.prepareStatement(q)
    stmt.setInt(1, courierId)
    val rs = stmt.executeQuery()
    if (rs.next() == false) {
      None
    } else {
      Some(_buildCourierFromRs(rs))
    }
  }

  def getAll(): List[Courier] = {
    val s = conn.createStatement()
    val q = "SELECT * FROM courier;"
    val rs = s.executeQuery(q)
    _mapToCourierList(rs)
  }

  private def _mapToCourierList(rs: ResultSet): List[Courier] = {
    Iterator
      .continually(rs.next)
      .takeWhile(identity)
      .map { _ => _buildCourierFromRs(rs) }
      .toList
  }

  private def _buildCourierFromRs(rs: ResultSet): Courier = {
    new Courier(
      id = rs.getInt(1),
      name = rs.getString(2),
      image = rs.getString(3)
    )
  }
}
