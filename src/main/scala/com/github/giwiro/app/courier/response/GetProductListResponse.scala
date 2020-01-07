package com.github.giwiro.app.courier.response

import com.github.giwiro.model.{Courier, Product}

class GetProductListResponse(val products: List[Product], val courier: Courier)
