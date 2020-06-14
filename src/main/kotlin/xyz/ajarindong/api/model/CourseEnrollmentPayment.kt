package xyz.ajarindong.api.model

import org.apache.commons.lang3.RandomStringUtils
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import javax.persistence.*

@Entity
data class CourseEnrollmentPayment(
        @ManyToOne
        var paymentMethod: PaymentMethod
) {
    enum class Status { PAID, UNPAID }

    @Id
    var id: String = UUID.randomUUID().toString()

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: Status = Status.PAID

    @Column(nullable = false)
    var paymentNumber: String = RandomStringUtils.randomNumeric(10)

    @Column(nullable = false)
    var validUntil: Instant = Instant.now().plus(1, ChronoUnit.DAYS)
}