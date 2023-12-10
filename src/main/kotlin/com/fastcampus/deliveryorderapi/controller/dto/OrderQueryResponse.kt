package com.fastcampus.deliveryorderapi.controller.dto

data class OrderQueryResponse(
    val storeId: Long,
    val orders: List<DeliveryOrderDTO>
)