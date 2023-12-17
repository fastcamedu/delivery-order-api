package com.fastcampus.deliveryorderapi.controller.dto

enum class RequestStoreOrderStatus(val description: String) {
    ALL("전체"),
    READY("주문 대기"),
    COOKING("조리 중"),
    DELIVERING("배달 중"),
    CANCELLED("주문 취소"),
    COMPLETED("배달 완료")
}