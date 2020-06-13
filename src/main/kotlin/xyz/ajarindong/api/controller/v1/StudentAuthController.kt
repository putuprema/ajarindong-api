package xyz.ajarindong.api.controller.v1

import io.swagger.v3.oas.annotations.Operation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.ajarindong.api.config.security.StudentDetails
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.dto.AuthDto
import xyz.ajarindong.api.dto.ResponseEnvelope
import xyz.ajarindong.api.dto.StudentDto
import xyz.ajarindong.api.dto.form.LoginForm
import xyz.ajarindong.api.dto.mapper.StudentMapper
import xyz.ajarindong.api.exception.AjarinDongException
import xyz.ajarindong.api.utils.JwtUtils
import javax.validation.Valid

@RestController
@RequestMapping("/v1/student/auth")
class StudentAuthController(
        @Qualifier("studentAuthManager")
        private val studentAuthManager: AuthenticationManager,
        private val jwtUtils: JwtUtils
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Operation(summary = "Login")
    @PostMapping("/login")
    fun login(@Valid @RequestBody form: LoginForm): ResponseEnvelope<AuthDto<StudentDto>> {
        val auth: Authentication = try {
            studentAuthManager.authenticate(UsernamePasswordAuthenticationToken(form.email, form.password))
        } catch (e: AuthenticationException) {
            log.info("Student [{}] failed to login: {}", form.email, e.message)
            throw AjarinDongException(ErrCode.BAD_CREDENTIALS, ErrCode.BAD_CREDENTIALS.message)
        }

        SecurityContextHolder.getContext().authentication = auth

        val studentDetails: StudentDetails = auth.principal as StudentDetails
        val student = studentDetails.student

        val jwt = jwtUtils.generateStudentJwtToken(student)

        log.info("Student [{}, {}] has logged in", student.email, student.name)
        return ResponseEnvelope<AuthDto<StudentDto>>(ErrCode.SUCCESS.code, ErrCode.SUCCESS.message)
                .data(AuthDto(jwt, StudentMapper.toStudentDto(student)))
    }
}