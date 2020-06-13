package xyz.ajarindong.api.dto

class AuthDto<T>(
        var accessToken: String,
        var profile: T
) {
}