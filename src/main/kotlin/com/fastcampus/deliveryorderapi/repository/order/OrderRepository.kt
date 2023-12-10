package com.fastcampus.deliveryorderapi.repository.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<Order, Long> {
    fun findAllByOrderIdIn(orderIds: List<Long>): List<Order>
    fun findAllByStoreId(storeId: Long): List<Order>
}