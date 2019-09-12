package com.github.giwiro.utils

import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat

object DateUtil {
  def parseDate(s: String): java.sql.Date = {
    val df = new SimpleDateFormat("dd/mm/yyyy")
    new Date(df.parse(s).getTime())
  }
}
