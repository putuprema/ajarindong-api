package xyz.ajarindong.api.config.security

import io.jsonwebtoken.*
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.exception.JwtTokenException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class StudentJwtAuthenticationFilter(
        private val studentDetailsService: StudentDetailsService,
        private val apiAuthenticationEntryPoint: ApiAuthenticationEntryPoint,
        @Value("\${jwt.secret}")
        private val jwtSecret: String
) : OncePerRequestFilter() {
    override fun doFilterInternal(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, filterChain: FilterChain) {
        // 1. Get authentication header
        val header = httpServletRequest.getHeader("Authorization")
        if (header == null || !header.startsWith("Bearer ")) {
            // If there is no Authentication header, maybe client just wants to access public resource,
            // so we let them do so without throwing exception.
            filterChain.doFilter(httpServletRequest, httpServletResponse)
            return
        }

        try {
            // 2. Extract JWT from Authentication header
            val jwt = header.replaceFirst("Bearer ", "")
            try {
                // 3. Parse JWT claims
                val parsedJwt: Jws<Claims> = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt)

                // 4. Try to get user by its username and user type
                val username = parsedJwt.body.subject
                val studentDetails: StudentDetails = studentDetailsService.loadByEmail(username) as StudentDetails

                // 5. Set current auth'd user to 'auth'
                // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
                // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                val auth = UsernamePasswordAuthenticationToken(studentDetails.student, null, studentDetails.authorities)
                SecurityContextHolder.getContext().authentication = auth
            } catch (e: JwtException) {
                when (e) {
                    is ExpiredJwtException -> {
                        throw JwtTokenException(ErrCode.TOKEN_EXPIRED, ErrCode.TOKEN_EXPIRED.message)
                    }
                    is SignatureException -> {
                        throw JwtTokenException(ErrCode.TOKEN_INVALID, ErrCode.TOKEN_INVALID.message)
                    }
                    else -> {
                        throw BadCredentialsException("Token invalid")
                    }
                }
            }
        } catch (e: AuthenticationException) {
            SecurityContextHolder.clearContext()
            apiAuthenticationEntryPoint.commence(httpServletRequest, httpServletResponse, e)
            return
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse)
    }
}