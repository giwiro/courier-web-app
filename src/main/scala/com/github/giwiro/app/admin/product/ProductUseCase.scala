package com.github.giwiro.app.admin.product

import java.sql.Connection

import com.github.giwiro.app.admin.product.request.{PostEditProduct, PostNewProduct}
import com.github.giwiro.app.admin.product.response.{AddProductResponse, EditProductResponse, GetAllProductStatesResponse, GetProductResponse}
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

  def editProduct(request: PostEditProduct) =
    DatabaseConnectionSupport.withDatabaseConnection[EditProductResponse] {
      conn: Connection =>
        val productDAO = new ProductDAO(conn)
        val product = new Product(
          id = Some(request.productId),
          stateId = ProductStates.PENDING,
          state = None,
          courierId = request.courierId,
          name = request.name,
          url = request.url,
          quantity = request.quantity,
          withBox = request.withBox,
          deliveryDate = request.deliveryDate,
          detail = request.detail,
          image = request.image,
          owner = Some(request.owner))
        val inserted = productDAO.edit(product)
        new EditProductResponse(inserted)
    }

  def getProduct(productId: Int): GetProductResponse =
    DatabaseConnectionSupport.withDatabaseConnection[GetProductResponse] {
      conn: Connection =>
        val productDAO = new ProductDAO(conn)
        val product = productDAO.getBytId(productId)
        new GetProductResponse(product)
    }


  def addProduct(request: PostNewProduct) =
    DatabaseConnectionSupport.withDatabaseConnection[AddProductResponse] {
      conn: Connection =>
        val productDAO = new ProductDAO(conn)
        val product = new Product(
          id = None,
          stateId = ProductStates.PENDING,
          state = None,
          courierId = request.courierId,
          name = request.name,
          url = request.url,
          quantity = request.quantity,
          withBox = request.withBox,
          deliveryDate = request.deliveryDate,
          detail = request.detail,
          image = request.image,
          owner = Some(request.owner))
        val inserted = productDAO.insert(product)
        new AddProductResponse(inserted)
    }
}
