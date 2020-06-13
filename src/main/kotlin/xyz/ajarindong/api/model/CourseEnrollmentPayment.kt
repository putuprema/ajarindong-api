package xyz.ajarindong.api.model

import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
data class CourseEnrollmentPayment(
        @ManyToOne
        var paymentMethod: PaymentMethod,
        @Column(nullable = false)
        var paymentNumber: String,
        @Column(nullable = false)
        var validUntil: Instant
) {
    enum class Status { PAID, UNPAID }

    @Id
    var id: String = UUID.randomUUID().toString()

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: Status = Status.PAID
}