package xyz.ajarindong.api.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import xyz.ajarindong.api.constant.ErrCode

@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseEnvelope<T>(var statusCode: String? = ErrCode.SUCCESS.code, var statusMessage: String? = ErrCode.SUCCESS.message) : RepresentationModel<ResponseEnvelope<T>>() {
    var data: T? = null

    fun data(payload: T) = apply { this.data = payload }
}