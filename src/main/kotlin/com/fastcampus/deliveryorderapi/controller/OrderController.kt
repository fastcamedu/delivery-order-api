package com.fastcampus.deliveryorderapi.controller

import com.fastcampus.deliveryorderapi.controller.dto.DeliveryOrderDTO
import com.fastcampus.deliveryorderapi.controller.dto.OrderQueryResponse
import com.fastcampus.deliveryorderapi.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@CrossOrigin("*")
@RestController
class OrderController(
    private val orderService: OrderService
){
    @GetMapping("/apis/orders")
    fun list(@RequestParam storeId: Long): ResponseEntity<OrderQueryResponse> {
        val orders = this.orderService.findAllByStoreId(storeId)
        return ResponseEntity.ok(OrderQueryResponse(
            storeId = storeId,
            orders = orders,
        ))
    }

    @GetMapping("/apis/orders/{orderId}")
    fun detail(@PathVariable("orderId") orderId: Long): ResponseEntity<DeliveryOrderDTO> {
        val deliveryOrderDTO = this.orderService.findById(orderId)
        return ResponseEntity.ok(deliveryOrderDTO)
    }
}