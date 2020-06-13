package xyz.ajarindong.api.config.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import xyz.ajarindong.api.model.Student

class StudentDetails(var student: Student, private var authorities: MutableCollection<out GrantedAuthority>) : UserDetails {
    private var username: String = student.email
    private var password: String = student.password

    companion object {
        fun build(student: Student): StudentDetails {
            return StudentDetails(student, arrayListOf())
        }
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = this.authorities

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = this.username

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = this.password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StudentDetails

        if (username != other.username) return false

        return true
    }

    override fun hashCode(): Int {
        return username.hashCode()
    }


}