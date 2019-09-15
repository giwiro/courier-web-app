package com.github.giwiro.database.dao

import java.sql.{Connection, ResultSet}
import com.github.giwiro.model.Courier

class CourierDAO(conn: Connection) {
  def get(courierId: Int): Option[Courier] = {
    val q =
      """
        |SELECT c.*,
        |       SUM(CASE WHEN p.state_id = 1 then 1 else 0 end) as total_pending,
        |       SUM(CASE WHEN p.state_id = 2 then 1 else 0 end) as total_received
        |FROM courier AS c
        |LEFT JOIN product AS p ON c.id = p.courier_id
        |WHERE c.id = ?
        |GROUP BY c.id;
        |""".stripMargin
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
    val q =
      """
        |SELECT c.*,
        |       SUM(CASE WHEN p.state_id = 1 then 1 else 0 end) as total_pending,
        |       SUM(CASE WHEN p.state_id = 2 then 1 else 0 end) as total_received,
        |       SUM(CASE WHEN p.state_id = 3 then 1 else 0 end) as total_delivered
        |FROM courier AS c
        |LEFT JOIN product AS p ON c.id = p.courier_id
        |GROUP BY c.id;
        |""".stripMargin
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
      image = rs.getString(3),
      totalPending = Some(rs.getInt(4)),
      totalReceived = Some(rs.getInt(4)),
      totalDelivered = Some(rs.getInt(5)),
    )
  }
}
