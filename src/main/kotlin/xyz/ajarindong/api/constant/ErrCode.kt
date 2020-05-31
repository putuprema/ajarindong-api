package xyz.ajarindong.api.constant

enum class ErrCode(val code: String, val message: String) {
    SUCCESS("0000", "Sukses"),
    UNKNOWN("9999", "Terjadi kesalahan, silahkan coba beberapa saat lagi"),
    TOKEN_INVALID("3000", "Token tidak valid"),
    TOKEN_EXPIRED("3001", "Token kadaluarsa"),
    BAD_CREDENTIALS("3333", "Silahkan periksa informasi login anda"),
    FORBIDDEN("5555", "Akses tidak diizinkan"),
    NOT_FOUND("4000", "Item tidak ditemukan"),
    ENTITY_CONFLICT("4001", "Entitas sudah terdaftar"),
    FIELD_INVALID("4002", "Field tidak valid")
}