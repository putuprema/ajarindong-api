package xyz.ajarindong.api.config.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import xyz.ajarindong.api.model.Mentor

class MentorDetails(var mentor: Mentor, private var authorities: MutableCollection<out GrantedAuthority>) : UserDetails {
    private var username: String = mentor.email
    private var password: String = mentor.password

    companion object {
        fun build(mentor: Mentor): MentorDetails {
            return MentorDetails(mentor, arrayListOf())
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

        other as MentorDetails

        if (username != other.username) return false

        return true
    }

    override fun hashCode(): Int {
        return username.hashCode()
    }


}