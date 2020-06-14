package xyz.ajarindong.api.service

import org.springframework.http.ResponseEntity
import xyz.ajarindong.api.dto.CourseCategoryDto

interface CourseCategoryService {
    fun getCategories(): List<CourseCategoryDto>
    fun getCategoryDetail(id: String): CourseCategoryDto
    fun getIcon(id: String): ResponseEntity<ByteArray>
}