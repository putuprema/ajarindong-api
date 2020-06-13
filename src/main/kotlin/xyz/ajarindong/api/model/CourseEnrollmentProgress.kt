package xyz.ajarindong.api.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class CourseEnrollmentProgress(
        @ManyToOne
        var courseEnrollment: CourseEnrollment,
        @ManyToOne
        var courseSubject: CourseSubject
) : AuditableEntity() {
    @Id
    var id: String = UUID.randomUUID().toString()

    @Column(nullable = false)
    var completed: Boolean = false
}