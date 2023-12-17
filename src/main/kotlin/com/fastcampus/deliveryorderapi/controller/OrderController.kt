package com.fastcampus.deliveryorderapi.controller

import com.fastcampus.deliveryorderapi.controller.dto.DeliveryOrderDTO
import com.fastcampus.deliveryorderapi.controller.dto.OrderQueryResponse
import com.fastcampus.deliveryorderapi.service.OrderService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.OffsetDateTime

@CrossOrigin("*")
@RestController
class OrderController(
    private val orderService: OrderService
) {
    companion object {
        private val logger = KotlinLogging.logger {  }
    }

    @GetMapping("/apis/orders")
    fun list(
        @RequestParam storeId: Long,
        @RequestParam queryBaseDate: String,
    ): ResponseEntity<OrderQueryResponse> {
        logger.info { ">>> 상점에서 들어온 주문 정보 조회: $storeId, $queryBaseDate" }

        // Parse the string to LocalDate (without time and offset)
        val changedQueryBaseDate = OffsetDateTime.parse(queryBaseDate + "T00:00:00Z")

        val orders = this.orderService.findAll(storeId, changedQueryBaseDate)
        return ResponseEntity.ok(OrderQueryResponse(
            storeId = storeId,
            orders = orders,
        ))
    }

    @GetMapping("/apis/order-ids")
    fun listByOrderIds(
        @RequestParam storeId: Long,
        @RequestParam("orderIds") orderIds: List<Long>
    ): ResponseEntity<OrderQueryResponse> {
        logger.info { ">>> 상점에서 들어온 주문 정보 조회: $storeId, $orderIds" }
        val deliveryOrderDTO = this.orderService.findByIds(orderIds)
        return ResponseEntity.ok(OrderQueryResponse(
            storeId = storeId,
            orders = deliveryOrderDTO,
        ))
    }

    @GetMapping("/apis/orders/{orderId}")
    fun detail(@PathVariable("orderId") orderId: Long): ResponseEntity<DeliveryOrderDTO> {
        logger.info { ">>> 상점에서 들어온 주문 상세 정보 조회: $orderId" }
        val deliveryOrderDTO = this.orderService.findById(orderId)
        return ResponseEntity.ok(deliveryOrderDTO)
    }
}