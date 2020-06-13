package xyz.ajarindong.api.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import xyz.ajarindong.api.constant.AppConstant
import xyz.ajarindong.api.dto.mapper.StudentMapper
import xyz.ajarindong.api.model.Student
import java.util.*

@Component
class JwtUtils(
        @Value("\${jwt.secret}")
        private val jwtSecret: String
) {
    fun generateStudentJwtToken(student: Student): String {
        val claims: Map<String, Any> = mapOf(
                Pair("student", StudentMapper.toMinimalStudentDto(student))
        )

        return Jwts.builder()
                .setIssuedAt(Date())
                .setIssuer(AppConstant.JWT_ISSUER)
                .setSubject(student.email)
                .setId(student.id)
                .setExpiration(Date(Date().time + AppConstant.JWT_EXPIRATION_MS))
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }
}