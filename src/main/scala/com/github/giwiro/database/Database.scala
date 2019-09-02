package com.github.giwiro.database

import java.sql.Connection

import com.github.giwiro.server.Config

trait Database {
  var config: Config

  def configureDb: Unit

  def getConnection: Connection

  def closeConnection: Unit
}
