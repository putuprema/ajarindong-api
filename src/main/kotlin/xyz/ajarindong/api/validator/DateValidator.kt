package xyz.ajarindong.api.validator

import xyz.ajarindong.api.constant.AppConstant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class DateValidator : ConstraintValidator<Date, String> {
    var format: String = AppConstant.DEFAULT_DATE_FORMAT

    override fun initialize(constraintAnnotation: Date) {
        format = constraintAnnotation.format
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true

        val formatter = DateTimeFormatter.ofPattern(format)
        return try {
            LocalDate.parse(value, formatter)
            true
        } catch (e: Exception) {
            false
        }
    }
}