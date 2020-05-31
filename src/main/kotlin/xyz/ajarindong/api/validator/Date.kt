package xyz.ajarindong.api.validator

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [DateValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Date(
        val message: String = "ignored message",
        val format: String = "dd/MM/yyyy",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)