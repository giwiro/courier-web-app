package com.github.giwiro.app.admin.product

import java.sql.Connection

import com.github.giwiro.app.admin.product.request.PostNewProduct
import com.github.giwiro.app.admin.product.response.{AddProductResponse, GetAllProductStatesResponse}
import com.github.giwiro.database.DatabaseConnectionSupport
import com.github.giwiro.database.dao.{ProductDAO, ProductStateDAO}
import com.github.giwiro.model.{Product, ProductStates}

object ProductUseCase {
  def getAllProductStates =
    DatabaseConnectionSupport.withDatabaseConnection[GetAllProductStatesResponse] {
      conn: Connection =>
        val productStateDAO = new ProductStateDAO(conn)
        val list = productStateDAO.getAll()
        new GetAllProductStatesResponse(list)
    }

  def addProduct(request: PostNewProduct) =
    DatabaseConnectionSupport.withDatabaseConnection[AddProductResponse] {
      conn: Connection =>
        val productDAO = new ProductDAO(conn)
        val product = new Product(
          id = None,
          statusId = ProductStates.PENDING,
          status = None,
          courierId = request.courierId,
          name = request.name,
          url = request.url,
          quantity = request.quantity,
          withBox = request.withBox,
          deliveryDate = request.deliveryDate,
          detail = request.detail,
          image = request.image)
        println("!!!! GONNA INSERT")
        val inserted = productDAO.insert(product)
        new AddProductResponse(inserted)
    }
}
