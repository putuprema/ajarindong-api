package xyz.ajarindong.studentservice.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.hibernate.validator.constraints.Length
import xyz.ajarindong.api.validator.Date

class MentorProfileUpdateDto(
        @field:Length(min = 4, message = "Nama harus minimal 4 karakter")
        var name: String? = null,
        @field:Schema(description = "Tanggal lahir (format: DD/MM/YYYY)", example = "16/06/2000")
        @field:Date(message = "Tanggal lahir tidak valid")
        var dateOfBirth: String? = null,
        @field:Length(max = 1000, message = "Panjang bio maksimal 1000 karakter")
        var bio: String? = null
)