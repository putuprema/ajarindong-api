package xyz.ajarindong.api.service

import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.dto.CourseDto
import xyz.ajarindong.api.dto.form.CourseQueryForm
import xyz.ajarindong.api.dto.mapper.CourseMapper
import xyz.ajarindong.api.exception.EntityNotFoundException
import xyz.ajarindong.api.repository.CourseCategoryRepository
import xyz.ajarindong.api.repository.course.CourseRepository

@Service
class CourseServiceImpl(
        private val courseCategoryRepository: CourseCategoryRepository,
        private val courseRepository: CourseRepository
) : CourseService {
    override fun getCourses(form: CourseQueryForm): List<CourseDto> {
        return when {
            StringUtils.isNotEmpty(form.categoryId) -> {
                val category = courseCategoryRepository.findById(form.categoryId!!).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Kategori tidak ada") }
                courseRepository.findAllByCategory(category).map { c -> CourseMapper.toMinimalCourseDto(c) }
            }
            StringUtils.isNotEmpty(form.name) -> {
                var courseName = form.name!!
                        .replace("_", "\\\\_")
                        .replace("%", "\\\\%")
                courseName = "%${courseName}%"
                courseRepository.findAllByNameLike(courseName).map { c -> CourseMapper.toMinimalCourseDto(c) }
            }
            else -> {
                courseRepository.findAll().map { c -> CourseMapper.toMinimalCourseDto(c) }
            }
        }
    }

    override fun getCourseDetail(id: String): CourseDto {
        val c = courseRepository.findById(id).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Kursus tidak ada") }
        return CourseMapper.toCourseDto(c)
    }
}