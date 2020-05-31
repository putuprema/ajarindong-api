package xyz.ajarindong.api.service

import org.slf4j.LoggerFactory
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import xyz.ajarindong.api.constant.AppConstant
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.controller.v1.StudentController
import xyz.ajarindong.api.dto.mapper.StudentMapper
import xyz.ajarindong.api.exception.EntityAlreadyExistException
import xyz.ajarindong.api.exception.EntityNotFoundException
import xyz.ajarindong.api.model.Student
import xyz.ajarindong.api.repository.StudentRepository
import xyz.ajarindong.studentservice.dto.StudentDto
import xyz.ajarindong.studentservice.dto.StudentProfileUpdateDto
import xyz.ajarindong.studentservice.dto.StudentRegistrationDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class StudentServiceImpl(
        private val passwordEncoder: PasswordEncoder,
        private val studentRepository: StudentRepository
) : StudentService {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun getStudents(): List<StudentDto> {
        val students = arrayListOf<StudentDto>()
        studentRepository.findAll().forEach { s ->
            students.add(StudentMapper.toMinimalStudentDto(s)
                    .add(linkTo<StudentController> { getStudentProfile(s.id) }.withSelfRel())
            )
        }
        return students
    }

    override fun getProfile(id: String): StudentDto {
        val s = studentRepository.findById(id).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Pengguna tidak terdaftar") }
        return StudentMapper.toStudentDto(s)
    }

    override fun editProfile(id: String, form: StudentProfileUpdateDto): StudentDto {
        val s = studentRepository.findById(id).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Pengguna tidak terdaftar") }

        s.name = Optional.ofNullable(form.name).orElse(s.name)
        s.dateOfBirth = if (form.dateOfBirth != null) LocalDate.parse(form.dateOfBirth, DateTimeFormatter.ofPattern(AppConstant.DEFAULT_DATE_FORMAT)) else s.dateOfBirth
        studentRepository.save(s)

        log.info("Account [email={}] profile updated", s.email)
        return StudentMapper.toStudentDto(s)
    }

    override fun register(form: StudentRegistrationDto): StudentDto {
        studentRepository
                .findByEmail(form.email!!)
                .ifPresent { throw EntityAlreadyExistException(ErrCode.ENTITY_CONFLICT, "Email sudah terdaftar") }

        val stud = Student(
                form.email!!,
                passwordEncoder.encode(form.password!!),
                form.name!!,
                LocalDate.parse(form.dateOfBirth!!, DateTimeFormatter.ofPattern(AppConstant.DEFAULT_DATE_FORMAT))
        )
        studentRepository.save(stud)

        log.info("New student [email={}, name={}] registered", stud.email, stud.name)
        return StudentMapper.toMinimalStudentDto(stud)
    }
}