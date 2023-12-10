package com.fastcampus.deliveryorderapi.controller.dto

import com.fastcampus.deliveryorderapi.domain.orderitem.OrderItemMenu
import java.math.BigDecimal

data class OrderItemDTO(
    val orderId: Long,
    val orderItemId: Long,
    val menuId: Long,
    val menuName: String,
    val menuPrice: BigDecimal,
    val menuQuantity: Int,
) {
    companion object {
        fun of(it: OrderItemMenu): OrderItemDTO {
            return OrderItemDTO(
                orderId = it.orderId,
                orderItemId = it.orderId,
                menuId = it.menuId,
                menuName = it.menuName,
                menuPrice = it.menuPrice,
                menuQuantity = it.menuQuantity,
            )
        }
    }
}