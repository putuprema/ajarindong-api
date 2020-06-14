package xyz.ajarindong.api.controller.v1

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.ajarindong.api.dto.PaymentMethodDto
import xyz.ajarindong.api.dto.ResponseEnvelope
import xyz.ajarindong.api.service.PaymentMethodService

@RestController
@RequestMapping("/v1/payment-method")
class PaymentMethodController(
        private val paymentMethodService: PaymentMethodService
) {
    @Operation(summary = "Get supported payment methods")
    @GetMapping
    fun getPaymentMethods(): ResponseEnvelope<List<PaymentMethodDto>> =
            ResponseEnvelope<List<PaymentMethodDto>>().data(paymentMethodService.getPaymentMethods())
}