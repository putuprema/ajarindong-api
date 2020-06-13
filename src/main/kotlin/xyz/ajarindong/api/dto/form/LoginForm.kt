package xyz.ajarindong.api.dto.form

import javax.validation.constraints.NotEmpty

class LoginForm(
        @field:NotEmpty(message = "Email tidak boleh kosong")
        var email: String? = null,
        @field:NotEmpty(message = "Password tidak boleh kosong")
        var password: String? = null
)