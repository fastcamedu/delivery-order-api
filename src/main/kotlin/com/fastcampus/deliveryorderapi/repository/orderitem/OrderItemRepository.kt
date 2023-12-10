package com.fastcampus.deliveryorderapi.repository.orderitem

import com.fastcampus.deliveryorderapi.domain.orderitem.OrderItemMenu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OrderItemRepository: JpaRepository<OrderItem, Long> {
    @Query(
        value = """
            SELECT new com.fastcampus.deliveryorderapi.domain.orderitem.OrderItemMenu(oi.orderId, oi.orderItemId, oi.menuId, m.menuName, oi.menuPrice, oi.menuQuantity, m.menuMainImageUrl)
            FROM OrderItem as oi
            JOIN Menu as m ON oi.menuId = m.menuId
            WHERE oi.orderId IN (:orderIds)
        """
    )
    fun findAllByOrderIdIn(@Param("orderIds") orderIds: List<Long>): List<OrderItemMenu>

    @Query(
        value = """
            SELECT new com.fastcampus.deliveryorderapi.domain.orderitem.OrderItemMenu(oi.orderId, oi.orderItemId, oi.menuId, m.menuName, oi.menuPrice, oi.menuQuantity, m.menuMainImageUrl)
            FROM OrderItem as oi
            JOIN Menu as m ON oi.menuId = m.menuId
            WHERE oi.orderId = :orderId
        """
    )
    fun findByOrderId(@Param("orderId") orderId: Long): List<OrderItemMenu>
}