package com.github.giwiro.database.dao

import java.sql.{Connection, ResultSet}

import com.github.giwiro.model.ProductState

class ProductStateDAO(conn: Connection) {
  def getAll(): List[ProductState] = {
    val s = conn.createStatement()
    val q = "SELECT * FROM product_state;"
    val rs = s.executeQuery(q)
    mapToProductState(rs)
  }

  private def mapToProductState(rs: ResultSet): List[ProductState] = {
    Iterator
      .continually(rs.next)
      .takeWhile(identity)
      .map(_ => new ProductState(id = rs.getInt(1), name = rs.getString(2)))
      .toList
  }

}
