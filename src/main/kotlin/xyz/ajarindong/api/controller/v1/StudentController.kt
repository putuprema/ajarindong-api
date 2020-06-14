package xyz.ajarindong.api.controller.v1

import io.swagger.v3.oas.annotations.Operation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import xyz.ajarindong.api.dto.CourseEnrollmentDto
import xyz.ajarindong.api.dto.ResponseEnvelope
import xyz.ajarindong.api.dto.StudentDto
import xyz.ajarindong.api.dto.form.StudentProfileUpdateDto
import xyz.ajarindong.api.dto.form.StudentRegistrationDto
import xyz.ajarindong.api.service.CourseEnrollmentService
import xyz.ajarindong.api.service.StudentService
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/v1/student")
@Transactional(rollbackOn = [Exception::class])
class StudentController(
        private val studentService: StudentService,
        private val courseEnrollmentService: CourseEnrollmentService
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Operation(summary = "Get all students")
    @GetMapping
    fun getStudents(): ResponseEnvelope<List<StudentDto>> =
            ResponseEnvelope<List<StudentDto>>()
                    .data(studentService.getStudents())

    @Operation(summary = "Get student profile")
    @GetMapping("/{id}")
    fun getStudentProfile(@PathVariable id: String): ResponseEnvelope<StudentDto> =
            ResponseEnvelope<StudentDto>()
                    .data(studentService.getProfile(id))

    @Operation(summary = "Edit student profile")
    @PutMapping("/{id}")
    fun editStudentProfile(@PathVariable id: String, @Valid @RequestBody form: StudentProfileUpdateDto): ResponseEnvelope<StudentDto> =
            ResponseEnvelope<StudentDto>()
                    .data(studentService.editProfile(id, form))

    @Operation(summary = "User registration")
    @PostMapping("/register")
    fun registerStudent(@Valid @RequestBody form: StudentRegistrationDto): ResponseEntity<ResponseEnvelope<StudentDto>> =
            ResponseEntity.status(HttpStatus.CREATED).body(
                    ResponseEnvelope<StudentDto>()
                            .data(studentService.register(form)))

    @Operation(summary = "Get courses enrolled by student")
    @GetMapping("/{id}/enrolled-course")
    fun getEnrolledCourses(@PathVariable id: String): ResponseEnvelope<List<CourseEnrollmentDto>> =
            ResponseEnvelope<List<CourseEnrollmentDto>>()
                    .data(courseEnrollmentService.getEnrolledCourse())

    @Operation(summary = "Get courses enrollment detail")
    @GetMapping("/{id}/enrolled-course/{enrollmentId}")
    fun getEnrolledCourseDetail(@PathVariable id: String, @PathVariable enrollmentId: String): ResponseEnvelope<CourseEnrollmentDto> =
            ResponseEnvelope<CourseEnrollmentDto>()
                    .data(courseEnrollmentService.getEnrolledCourseDetail(enrollmentId))
}