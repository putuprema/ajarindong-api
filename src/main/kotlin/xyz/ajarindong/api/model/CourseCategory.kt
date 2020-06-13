package xyz.ajarindong.api.model

import java.util.*
import javax.persistence.*

@Entity
data class CourseCategory(
        @Column(nullable = false)
        var name: String,
        @Column(nullable = false)
        var color: String,
        var icon: String
) : AuditableEntity() {
    @Id
    var id: String = UUID.randomUUID().toString()

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL])
    var courses: List<Course>? = null
}