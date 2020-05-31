package xyz.ajarindong.api.controller.v1

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import xyz.ajarindong.api.dto.MentorDto
import xyz.ajarindong.api.dto.ResponseEnvelope
import xyz.ajarindong.api.dto.form.MentorProfileUpdateDto
import xyz.ajarindong.api.dto.form.MentorRegistrationDto
import xyz.ajarindong.api.service.MentorService
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/v1/mentor")
@Transactional(rollbackOn = [Exception::class])
class MentorController(
        private val mentorService: MentorService
) {
    @Operation(summary = "Get all mentors")
    @GetMapping
    fun getMentors(): ResponseEnvelope<List<MentorDto>> =
            ResponseEnvelope<List<MentorDto>>()
                    .data(mentorService.getMentors())

    @Operation(summary = "Get mentor profile")
    @GetMapping("/{id}")
    fun getMentorProfile(@PathVariable id: String): ResponseEnvelope<MentorDto> =
            ResponseEnvelope<MentorDto>()
                    .data(mentorService.getDetails(id))

    @Operation(summary = "Update mentor profile")
    @PutMapping("/{id}")
    fun editMentorProfile(@PathVariable id: String, @Valid @RequestBody form: MentorProfileUpdateDto): ResponseEnvelope<MentorDto> =
            ResponseEnvelope<MentorDto>()
                    .data(mentorService.editProfile(id, form))

    @Operation(summary = "Mentor registration")
    @PostMapping("/register")
    fun registerMentor(@Valid @RequestBody form: MentorRegistrationDto): ResponseEntity<ResponseEnvelope<MentorDto>> =
            ResponseEntity.status(HttpStatus.CREATED).body(
                    ResponseEnvelope<MentorDto>()
                            .data(mentorService.register(form))
            )
}