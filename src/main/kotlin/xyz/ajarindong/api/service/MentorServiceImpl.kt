package xyz.ajarindong.api.service

import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.slf4j.LoggerFactory
import org.springframework.http.*
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import xyz.ajarindong.api.constant.AppConstant
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.dto.MentorDto
import xyz.ajarindong.api.dto.form.MentorProfileUpdateDto
import xyz.ajarindong.api.dto.form.MentorRegistrationDto
import xyz.ajarindong.api.dto.mapper.MentorMapper
import xyz.ajarindong.api.exception.EntityAlreadyExistException
import xyz.ajarindong.api.exception.EntityNotFoundException
import xyz.ajarindong.api.model.Mentor
import xyz.ajarindong.api.repository.MentorRepository
import java.io.FileNotFoundException
import java.nio.file.Paths
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class MentorServiceImpl(
        private val passwordEncoder: PasswordEncoder,
        private val mentorRepository: MentorRepository
) : MentorService {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun findById(id: String): Mentor =
            mentorRepository
                    .findById(id)
                    .orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Mentor tidak terdaftar") }

    override fun getMentors(): List<MentorDto> {
        val mentors = arrayListOf<MentorDto>()
        mentorRepository.findAll().forEach { m ->
            mentors.add(MentorMapper.toMinimalMentorDto(m))
        }
        return mentors
    }

    override fun getDetails(id: String): MentorDto {
        val m = mentorRepository.findById(id).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Mentor tidak terdaftar") }
        return MentorMapper.toMentorDto(m)
    }

    override fun editProfile(id: String, form: MentorProfileUpdateDto): MentorDto {
        val m = mentorRepository.findById(id).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Mentor tidak terdaftar") }

        m.name = Optional.ofNullable(form.name).orElse(m.name)
        m.bio = Optional.ofNullable(form.bio).orElse(m.bio)
        m.dateOfBirth = if (form.dateOfBirth != null) LocalDate.parse(form.dateOfBirth, DateTimeFormatter.ofPattern(AppConstant.DEFAULT_DATE_FORMAT)) else m.dateOfBirth
        mentorRepository.save(m)

        log.info("Mentor [email={}] profile updated", m.email)
        return MentorMapper.toMentorDto(m)
    }

    override fun register(form: MentorRegistrationDto): MentorDto {
        mentorRepository
                .findByEmail(form.email!!)
                .ifPresent { throw EntityAlreadyExistException(ErrCode.ENTITY_CONFLICT, "Email sudah terdaftar") }

        val m = Mentor(
                form.name!!,
                form.email!!,
                passwordEncoder.encode(form.password!!),
                LocalDate.parse(form.dateOfBirth!!, DateTimeFormatter.ofPattern(AppConstant.DEFAULT_DATE_FORMAT)),
                form.bio!!,
                form.job!!
        )
        mentorRepository.save(m)

        log.info("New mentor [email={}, name={}] registered", m.email, m.name)
        return MentorMapper.toMinimalMentorDto(m)
    }

    @Throws(Exception::class)
    override fun getProfilePicture(mentorId: String): ResponseEntity<ByteArray> {
        val m = mentorRepository.findById(mentorId).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Mentor tidak terdaftar") }
        val profilePicture = Optional.ofNullable(m.profilePicture).orElseThrow { FileNotFoundException("Mentor profile picture does not exist") }

        val pictureFile = Paths.get(AppConstant.STORAGE_BASE_PATH, AppConstant.STORAGE_MENTOR_FOLDER, m.id, profilePicture).toFile()
        val inputStream = FileUtils.openInputStream(pictureFile)
        val byteArray = inputStream.readAllBytes()
        inputStream.close()

        val headers = HttpHeaders()
        headers.setCacheControl(CacheControl.noCache())
        when (FilenameUtils.getExtension(profilePicture)) {
            "png", "PNG" -> headers.contentType = MediaType.IMAGE_PNG
            "jpg", "JPG", "jpeg", "JPEG" -> headers.contentType = MediaType.IMAGE_JPEG
            else -> headers.contentType = MediaType.IMAGE_JPEG
        }

        return ResponseEntity(byteArray, headers, HttpStatus.OK)
    }
}