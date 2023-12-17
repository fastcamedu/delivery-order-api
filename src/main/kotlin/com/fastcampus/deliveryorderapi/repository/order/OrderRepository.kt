package com.fastcampus.deliveryorderapi.repository.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
interface OrderRepository: JpaRepository<Order, Long> {
    fun findAllByOrderIdIn(orderIds: List<Long>): List<Order>

    fun findAllByStoreId(storeId: Long): List<Order>

    @Query(
        value = """
            SELECT o
            FROM Order as o
            WHERE o.storeId = :storeId
            AND o.createdAt BETWEEN :queryBaseDate AND :plusOneDayQueryBaseDate
        """
    )
    fun findAllBy(
        @Param("storeId") storeId: Long,
        @Param("queryBaseDate") queryBaseDate: OffsetDateTime,
        @Param("plusOneDayQueryBaseDate") plusOneDayQueryBaseDate: OffsetDateTime,
    ): List<Order>
}