package com.github.giwiro.server

trait ServerLauncher[T] {
  def configureServer: T
}
