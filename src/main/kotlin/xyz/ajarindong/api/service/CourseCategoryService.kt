package xyz.ajarindong.api.service

import xyz.ajarindong.api.dto.CourseCategoryDto

interface CourseCategoryService {
    fun getCategories(): List<CourseCategoryDto>
    fun getCategoryDetail(id: String): CourseCategoryDto
}