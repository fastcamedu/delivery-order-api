package com.fastcampus.deliveryorderapi.service

import com.fastcampus.deliveryorderapi.controller.dto.DeliveryOrderDTO
import com.fastcampus.deliveryorderapi.controller.dto.OrderItemDTO
import com.fastcampus.deliveryorderapi.exception.NotFoundException
import com.fastcampus.deliveryorderapi.repository.order.OrderRepository
import com.fastcampus.deliveryorderapi.repository.orderitem.OrderItemRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
) {
    fun findAllByStoreId(storeId: Long): List<DeliveryOrderDTO> {
        val orders = this.orderRepository.findAllByStoreId(storeId)

        val orderIds = orders.map { it.orderId }
        val orderItemMenus = orderItemRepository.findAllByOrderIdIn(orderIds)

        val orderItemDTOs = orderItemMenus.map { OrderItemDTO.of(it) }
        val orderItemMap = orderItemDTOs.groupBy { it.orderId }
        val deliveryOrderDTOList = orders.map {
            DeliveryOrderDTO.of(it)
        }
        deliveryOrderDTOList
            .filter { orderItemMap.containsKey(it.orderId) }
            .forEach { it.orderItems = orderItemMap[it.orderId] ?: emptyList() }

        return deliveryOrderDTOList
    }

    fun findById(orderId: Long): DeliveryOrderDTO {
        val orderOptional = this.orderRepository.findById(orderId)
        if (orderOptional.isEmpty) {
            throw NotFoundException("주문 정보를 찾을 수 없습니다. $orderId")
        }
        val order = orderOptional.get()
        val orderItemMenus = orderItemRepository.findByOrderId(orderId)
        val orderItemDTOS = orderItemMenus.map { OrderItemDTO.of(it) }

        return DeliveryOrderDTO(
            orderId = order.orderId,
            orderShortenId = order.orderShortenId,
            orderStatus = order.orderStatus,
            storeId = order.storeId,
            customerId = order.customerId,
            totalAmount = order.totalAmount,
            orderItems = orderItemDTOS,
        )
    }
}