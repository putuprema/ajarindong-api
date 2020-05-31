package xyz.ajarindong.api.controller.v1

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.dto.ResponseEnvelope
import xyz.ajarindong.studentservice.dto.StudentDto

@RestController
@RequestMapping("/v1/student")
class StudentAuthController {
    @Operation(summary = "Login")
    @PostMapping("/auth/login")
    fun login(): ResponseEntity<ResponseEnvelope<StudentDto>> {
        return ResponseEntity.ok(ResponseEnvelope(ErrCode.SUCCESS.code, ErrCode.SUCCESS.message))
    }
}