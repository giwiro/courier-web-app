package com.github.giwiro.server

trait Config {
  val port: Int
  val env: String
  val databaseUrl: String
  val databaseUser: String
  val databasePassword: String
}

object ConfigParser {
  val DEFAULT_VALUES = Map[String, String](
    "PORT" -> "6969",
    "ENV" -> "development",
    "DATABASE_URI" -> "jdbc:sqlite:courier.db",
    "DATABASE_USER" -> "admin",
    "DATABASE_PASSWORD" -> "admin",
  )

  def getConfig: Config = {
    val _port = if (System.getenv("PORT") != null)
      System.getenv("PORT") else DEFAULT_VALUES("PORT")
    val _env = if (System.getenv("ENV") != null)
      System.getenv("ENV") else DEFAULT_VALUES("ENV")
    val _databaseUrl = if (System.getenv("DATABASE_URI") != null)
      System.getenv("DATABASE_URI") else DEFAULT_VALUES("DATABASE_URI")
    val _databaseUser = if (System.getenv("DATABASE_USER") != null)
      System.getenv("DATABASE_USER") else DEFAULT_VALUES("DATABASE_USER")
    val _databasePassword = if (System.getenv("DATABASE_PASSWORD") != null)
      System.getenv("DATABASE_PASSWORD") else DEFAULT_VALUES("DATABASE_PASSWORD")

    object config extends Config {
      val port = _port.toInt
      val env = _env
      val databaseUrl = _databaseUrl
      val databaseUser = _databaseUser
      val databasePassword = _databasePassword
    }
    config
  }
}
