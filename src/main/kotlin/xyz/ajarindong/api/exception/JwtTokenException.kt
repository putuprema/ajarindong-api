package xyz.ajarindong.api.exception

import org.springframework.security.core.AuthenticationException
import xyz.ajarindong.api.constant.ErrCode

class JwtTokenException(
        val errCode: ErrCode,
        message: String
) : AuthenticationException(message) {
}