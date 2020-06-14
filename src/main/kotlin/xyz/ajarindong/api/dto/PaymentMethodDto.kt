package xyz.ajarindong.api.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class PaymentMethodDto {
    var name: String? = null
    var description: String? = null
    var id: String? = null
}