package xyz.ajarindong.api.model

import java.util.*
import javax.persistence.*

@Entity
data class CourseEnrollment(
        @ManyToOne
        var student: Student,
        @ManyToOne
        var mentor: Mentor,
        @ManyToOne
        var course: Course,
        @OneToOne(cascade = [CascadeType.ALL])
        var payment: CourseEnrollmentPayment
) : AuditableEntity() {
    @Id
    var id: String = UUID.randomUUID().toString()

    @Column(nullable = false)
    var completionPercent: Int = 0

    @OneToMany(mappedBy = "courseEnrollment", cascade = [CascadeType.ALL])
    var progress: List<CourseEnrollmentProgress>? = null
}