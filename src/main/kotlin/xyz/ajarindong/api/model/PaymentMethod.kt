package xyz.ajarindong.api.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class PaymentMethod(
        @Column(nullable = false)
        var name: String,
        @Column(nullable = false)
        var description: String,
        @Column(nullable = false)
        var icon: String
) : AuditableEntity() {
    @Id
    var id: String = UUID.randomUUID().toString()
}