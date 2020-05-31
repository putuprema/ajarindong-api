package xyz.ajarindong.api.model

import java.time.LocalDate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Student(
        @Column(nullable = false)
        var email: String,
        @Column(nullable = false)
        var password: String,
        @Column(nullable = false)
        var name: String,
        @Column(nullable = false)
        var dateOfBirth: LocalDate
) : AuditableEntity() {
    @Id
    var id: String = UUID.randomUUID().toString()
    var level: Int = 0
    var experience: Long = 0
}