package xyz.ajarindong.api.config.security

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Service
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.dto.ResponseEnvelope
import xyz.ajarindong.api.service.JsonMapperService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class ApiAccessDeniedHandler(
        private val mapper: JsonMapperService
) : AccessDeniedHandler {
    override fun handle(req: HttpServletRequest, res: HttpServletResponse, ex: AccessDeniedException) {
        val re = ResponseEnvelope<Any>(ErrCode.FORBIDDEN.code, ErrCode.FORBIDDEN.message)

        res.status = HttpStatus.FORBIDDEN.value()
        res.contentType = MediaType.APPLICATION_JSON_VALUE

        mapper.write(res.writer, re)
    }
}