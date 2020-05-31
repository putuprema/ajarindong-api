package xyz.ajarindong.api.controller.v1

import io.swagger.v3.oas.annotations.Operation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import xyz.ajarindong.api.dto.ResponseEnvelope
import xyz.ajarindong.api.exception.AjarinDongException
import xyz.ajarindong.api.service.StudentService
import xyz.ajarindong.studentservice.dto.StudentDto
import xyz.ajarindong.studentservice.dto.StudentProfileUpdateDto
import xyz.ajarindong.studentservice.dto.StudentRegistrationDto
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/v1/student")
@Transactional(rollbackOn = [Exception::class])
class StudentController(
        private val studentService: StudentService
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Operation(summary = "Get all students")
    @GetMapping
    fun getStudents(): ResponseEnvelope<List<StudentDto>> =
            ResponseEnvelope<List<StudentDto>>()
                    .data(studentService.getStudents())

    @Operation(summary = "Get student profile")
    @GetMapping("/{id}")
    @Throws(AjarinDongException::class)
    fun getStudentProfile(@PathVariable id: String): ResponseEnvelope<StudentDto> =
            ResponseEnvelope<StudentDto>()
                    .data(studentService.getProfile(id))

    @Operation(summary = "Edit student profile")
    @PutMapping("/{id}")
    @Throws(AjarinDongException::class)
    fun editStudentProfile(@PathVariable id: String, @Valid @RequestBody form: StudentProfileUpdateDto): ResponseEnvelope<StudentDto> =
            ResponseEnvelope<StudentDto>()
                    .data(studentService.editProfile(id, form))

    @Operation(summary = "User registration")
    @PostMapping("/register")
    @Throws(AjarinDongException::class)
    fun registerStudent(@Valid @RequestBody form: StudentRegistrationDto): ResponseEntity<ResponseEnvelope<StudentDto>> =
            ResponseEntity.status(HttpStatus.CREATED).body(
                    ResponseEnvelope<StudentDto>()
                            .data(studentService.register(form))
            )
}