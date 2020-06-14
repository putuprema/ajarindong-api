package xyz.ajarindong.api.controller.v1

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import xyz.ajarindong.api.dto.CourseCategoryDto
import xyz.ajarindong.api.dto.CourseDto
import xyz.ajarindong.api.dto.ResponseEnvelope
import xyz.ajarindong.api.dto.form.CourseQueryForm
import xyz.ajarindong.api.service.CourseCategoryService
import xyz.ajarindong.api.service.CourseService

@RestController
@RequestMapping("/v1/course")
class CourseController(
        private val courseCategoryService: CourseCategoryService,
        private val courseService: CourseService
) {
    @Operation(summary = "Get course category")
    @GetMapping("/category")
    fun getCourseCategories(): ResponseEnvelope<List<CourseCategoryDto>> =
            ResponseEnvelope<List<CourseCategoryDto>>()
                    .data(courseCategoryService.getCategories())

    @Operation(summary = "Get course category detail")
    @GetMapping("/category/{id}")
    fun getCourseCategoryDetail(@PathVariable id: String): ResponseEnvelope<CourseCategoryDto> =
            ResponseEnvelope<CourseCategoryDto>()
                    .data(courseCategoryService.getCategoryDetail(id))

    @Operation(summary = "Get courses")
    @GetMapping
    fun getCourses(@ModelAttribute form: CourseQueryForm): ResponseEnvelope<List<CourseDto>> =
            ResponseEnvelope<List<CourseDto>>()
                    .data(courseService.getCourses(form))

    @Operation(summary = "Get course detail")
    @GetMapping("/{id}")
    fun getCourseDetail(@PathVariable id: String): ResponseEnvelope<CourseDto> =
            ResponseEnvelope<CourseDto>()
                    .data(courseService.getCourseDetail(id))
}