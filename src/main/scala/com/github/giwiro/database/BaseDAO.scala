package com.github.giwiro.database

import java.sql.ResultSet

trait BaseDAO[T] {
  def getAll(): List[T]
}
