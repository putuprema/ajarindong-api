package xyz.ajarindong.api.service

import org.springframework.stereotype.Service
import xyz.ajarindong.api.dto.PaymentMethodDto
import xyz.ajarindong.api.dto.mapper.PaymentMethodMapper
import xyz.ajarindong.api.repository.PaymentMethodRepository

@Service
class PaymentMethodServiceImpl(
        private val paymentMethodRepository: PaymentMethodRepository
) : PaymentMethodService {
    override fun getPaymentMethods(): List<PaymentMethodDto> =
            paymentMethodRepository.findAll().map { p -> PaymentMethodMapper.toPaymentMethodDto(p) }
}