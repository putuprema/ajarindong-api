package xyz.ajarindong.api.model

import java.util.*
import javax.persistence.*

@Entity
data class Course(
        @ManyToOne
        var category: CourseCategory,
        @Column(nullable = false)
        var name: String,
        @Column(nullable = false, length = 1000)
        var description: String,
        @Column(nullable = false)
        var price: Long,
        @Column(nullable = false)
        var backgroundImgUrl: String
) : AuditableEntity() {
    @Id
    var id: String = UUID.randomUUID().toString()

    @ElementCollection
    var benefits: List<String>? = null

    @ManyToMany
    @JoinTable(
            joinColumns = [JoinColumn(name = "course_id")],
            inverseJoinColumns = [JoinColumn(name = "mentor_id")]
    )
    var mentors: List<Mentor>? = null

    @OneToMany(mappedBy = "course", cascade = [CascadeType.ALL])
    var courseSubjects: List<CourseSubject>? = null

    @OneToMany(mappedBy = "course")
    var enrollments: List<CourseEnrollment>? = null
}