package com.fastcampus.deliveryorderapi.controller.dto

import com.fastcampus.deliveryorderapi.domain.order.OrderStatus
import com.fastcampus.deliveryorderapi.repository.order.Order
import java.math.BigDecimal

data class DeliveryOrderDTO(
    val orderId: Long,
    val orderShortenId: String,
    val orderStatus: OrderStatus,
    val storeId: Long,
    val customerId: Long,
    val totalAmount: BigDecimal,
    var orderItems: List<OrderItemDTO>,
) {
    companion object {
        fun of(order: Order): DeliveryOrderDTO {
            return DeliveryOrderDTO(
                orderId = order.orderId,
                orderShortenId = "",
                orderStatus = order.orderStatus,
                storeId = order.storeId,
                customerId = order.customerId,
                totalAmount = order.totalAmount,
                orderItems = emptyList(),
            )
        }
    }
}