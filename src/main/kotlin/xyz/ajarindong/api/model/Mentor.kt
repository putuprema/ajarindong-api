package xyz.ajarindong.api.model

import java.time.LocalDate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
data class Mentor(
        @Column(nullable = false)
        var name: String,
        @Column(nullable = false)
        var email: String,
        @Column(nullable = false)
        var password: String,
        @Column(nullable = false)
        var dateOfBirth: LocalDate,
        @Column(nullable = false, length = 1000)
        var bio: String,
        var job: String
) : AuditableEntity() {
    @Id
    var id: String = UUID.randomUUID().toString()
    var profilePicture: String? = null

    @Column(nullable = false)
    var rating: Double = 0.0

    @ManyToMany(mappedBy = "mentors")
    var courses: List<Course>? = null
}