package xyz.ajarindong.api.config.security

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Service
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.dto.ResponseEnvelope
import xyz.ajarindong.api.exception.JwtTokenException
import xyz.ajarindong.api.service.JsonMapperService
import java.io.Writer
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class ApiAuthenticationEntryPoint(
        private val mapper: JsonMapperService
) : AuthenticationEntryPoint {
    override fun commence(req: HttpServletRequest, res: HttpServletResponse, e: AuthenticationException) {
        res.contentType = MediaType.APPLICATION_JSON_VALUE
        res.status = HttpStatus.UNAUTHORIZED.value()

        val writer: Writer = res.writer

        var errCode: ErrCode = ErrCode.BAD_CREDENTIALS
        val errMessage: String = e.message!!
        if (e is JwtTokenException) {
            val ex: JwtTokenException = e
            errCode = ex.errCode
        }

        val re = ResponseEnvelope<Any>(errCode.code, errMessage)
        mapper.write(writer, re)
    }
}