package com.github.giwiro.database

import com.github.giwiro.database.SQLiteDatabase.getConnection

import java.sql.Connection

object DatabaseConnectionSupport {
  def withDatabaseConnection[T](cb: Connection => T) = {
    val connection = getConnection

    try {
      cb(connection)
    }
    finally {
      connection.close()
    }
  }
}