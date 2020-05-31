package xyz.ajarindong.api.dto.form

import io.swagger.v3.oas.annotations.media.Schema
import org.hibernate.validator.constraints.Length
import xyz.ajarindong.api.validator.Date
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class StudentRegistrationDto(
        @field:Schema(example = "iputupremaananda@gmail.com")
        @field:NotEmpty(message = "Email tidak boleh kosong")
        @field:Email(message = "Email tidak valid")
        var email: String? = null,
        @field:NotEmpty(message = "Nama tidak boleh kosong")
        @field:Length(min = 4, message = "Nama harus minimal 4 karakter")
        var name: String? = null,
        @field:NotEmpty(message = "Password tidak boleh kosong")
        @field:Length(min = 8, message = "Password harus minimal 8 karakter")
        var password: String? = null,
        @field:Schema(description = "Tanggal lahir (format: DD/MM/YYYY)", example = "16/06/2000")
        @field:NotEmpty(message = "Tanggal lahir tidak boleh kosong")
        @field:Date(message = "Tanggal lahir tidak valid")
        var dateOfBirth: String? = null
)