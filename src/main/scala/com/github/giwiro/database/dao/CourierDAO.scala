package com.github.giwiro.database.dao

import java.sql.{Connection, ResultSet}

import com.github.giwiro.database.BaseDAO
import com.github.giwiro.model.Courier

class CourierDAO(conn: Connection) extends BaseDAO[Courier] {
  override def getAll(): List[Courier] = {
    val s = conn.createStatement()
    val q = "SELECT * FROM courier;"
    val rs = s.executeQuery(q)
    mapToCourierList(rs)
  }

  private def mapToCourierList(rs: ResultSet): List[Courier] = {
    Iterator
      .continually(rs.next)
      .takeWhile(identity)
      .map { _ => new Courier(rs.getInt(1), rs.getString(2), rs.getString(3)) }
      .toList
  }
}
