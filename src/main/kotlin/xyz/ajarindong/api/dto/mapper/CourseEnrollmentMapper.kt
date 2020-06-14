package xyz.ajarindong.api.dto.mapper

import xyz.ajarindong.api.dto.CourseEnrollmentDto
import xyz.ajarindong.api.model.CourseEnrollment

object CourseEnrollmentMapper {
    fun toCourseEnrollmentDto(c: CourseEnrollment): CourseEnrollmentDto {
        val dto = CourseEnrollmentDto()
        dto.course = CourseMapper.toCourseDto(c.course)
        dto.course!!.mentors = null
        dto.mentor = MentorMapper.toMinimalMentorDto(c.mentor)
        dto.payment = CourseEnrollmentPaymentMapper.toCourseEnrollmentPaymentDto(c.payment)
        dto.progress = c.progress?.map { c -> CourseEnrollmentProgressMapper.toCourseEnrollmentProgressDto(c) }
        dto.id = c.id
        dto.completionPercent = c.completionPercent
        return dto
    }

    fun toMinimalCourseEnrollmentDto(c: CourseEnrollment): CourseEnrollmentDto {
        val dto = CourseEnrollmentDto()
        dto.course = CourseMapper.toMinimalCourseDto(c.course)
        dto.payment = CourseEnrollmentPaymentMapper.toCourseEnrollmentPaymentDto(c.payment)
        dto.id = c.id
        dto.completionPercent = c.completionPercent
        return dto
    }
}