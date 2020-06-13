package xyz.ajarindong.api.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class CourseSubject(
        @ManyToOne
        var course: Course,
        @Column(nullable = false)
        var number: Int,
        @Column(nullable = false)
        var title: String,
        @Column(nullable = false, length = 1000)
        var description: String,
        @Column(nullable = false)
        var color: String
) : AuditableEntity() {
    @Id
    var id: String = UUID.randomUUID().toString()
}