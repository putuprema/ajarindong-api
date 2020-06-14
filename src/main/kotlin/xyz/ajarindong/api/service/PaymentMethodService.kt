package xyz.ajarindong.api.service

import xyz.ajarindong.api.dto.PaymentMethodDto

interface PaymentMethodService {
    fun getPaymentMethods(): List<PaymentMethodDto>
}