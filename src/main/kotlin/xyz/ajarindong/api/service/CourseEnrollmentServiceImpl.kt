package xyz.ajarindong.api.service

import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.dto.CourseEnrollmentDto
import xyz.ajarindong.api.dto.form.CourseEnrollmentForm
import xyz.ajarindong.api.dto.mapper.CourseEnrollmentMapper
import xyz.ajarindong.api.exception.EntityNotFoundException
import xyz.ajarindong.api.exception.ForbiddenOperationException
import xyz.ajarindong.api.model.CourseEnrollment
import xyz.ajarindong.api.model.CourseEnrollmentPayment
import xyz.ajarindong.api.model.CourseEnrollmentProgress
import xyz.ajarindong.api.model.Student
import xyz.ajarindong.api.repository.MentorRepository
import xyz.ajarindong.api.repository.PaymentMethodRepository
import xyz.ajarindong.api.repository.course.CourseRepository
import xyz.ajarindong.api.repository.courseenrollment.CourseEnrollmentRepository
import java.util.*

@Service
class CourseEnrollmentServiceImpl(
        private val courseEnrollmentRepository: CourseEnrollmentRepository,
        private val courseRepository: CourseRepository,
        private val paymentMethodRepository: PaymentMethodRepository,
        private val mentorRepository: MentorRepository
) : CourseEnrollmentService {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun enrollCourse(form: CourseEnrollmentForm): CourseEnrollmentDto {
        val student: Student = SecurityContextHolder.getContext().authentication.principal as Student
        val paymentMethod = paymentMethodRepository.findById(form.paymentMethodId!!).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Metode pembayaran tidak ditemukan") }

        val course = courseRepository.findById(form.courseId!!).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Kursus tidak ditemukan") }
        val courseMentors = Optional.ofNullable(course.mentors).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Tidak ada mentor yang tersedia untuk kursus ini") }
        val courseSubjects = Optional.ofNullable(course.courseSubjects).orElseThrow { ForbiddenOperationException(ErrCode.FORBIDDEN, "Kursus ini tidak bisa diikuti") }

        val mentor = mentorRepository.findById(form.mentorId!!).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Mentor tidak ditemukan") }
        if (!courseMentors.contains(mentor)) throw EntityNotFoundException(ErrCode.NOT_FOUND, "Mentor tidak terdaftar di kursus yang kamu pilih")

        val payment = CourseEnrollmentPayment(paymentMethod)
        val enrollment = CourseEnrollment(student, mentor, course, payment)
        val enrollmentProgress = courseSubjects.map { s -> CourseEnrollmentProgress(enrollment, s) }
        enrollment.progress = enrollmentProgress

        courseEnrollmentRepository.save(enrollment)
        log.info("Student [{}, {}] enrolled to course [{}, {}]", student.id, student.name, course.id, course.name)
        return CourseEnrollmentMapper.toCourseEnrollmentDto(enrollment)
    }

    override fun getEnrolledCourse(): List<CourseEnrollmentDto> {
        val student: Student = SecurityContextHolder.getContext().authentication.principal as Student
        return courseEnrollmentRepository.getAllByStudent(student).map { e -> CourseEnrollmentMapper.toMinimalCourseEnrollmentDto(e) }
    }

    override fun getEnrolledCourseDetail(enrollmentId: String): CourseEnrollmentDto {
        val enrollment = courseEnrollmentRepository.findById(enrollmentId).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Enrollment tidak ditemukan") }
        return CourseEnrollmentMapper.toCourseEnrollmentDto(enrollment)
    }
}