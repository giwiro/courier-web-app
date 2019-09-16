package com.github.giwiro.database

import java.sql.Connection

import com.github.giwiro.server.ConfigParser
import com.mchange.v2.c3p0.ComboPooledDataSource

object SQLiteDatabase extends Database {
  var ds: ComboPooledDataSource = new ComboPooledDataSource
  override var config = ConfigParser.getConfig

  override def configureDb: Unit = {
    Class.forName("org.sqlite.JDBC")
    println(s"SQLite configured with: ${config.databaseUrl}")
    ds.setJdbcUrl(config.databaseUrl)
    // NOTE: Not used in sqlite
    /*ds.setUser(config.databaseUser)
    ds.setPassword(config.databasePassword)*/

    /* Pool tune up */
    ds.setInitialPoolSize(5);
    ds.setMinPoolSize(5);
    ds.setAcquireIncrement(5);
    ds.setMaxPoolSize(20);
    ds.setMaxStatements(100);
  }

  override def getConnection: Connection = {
    ds.getConnection()
  }

  override def closeConnection: Unit = {
    ds.close()
  }
}
