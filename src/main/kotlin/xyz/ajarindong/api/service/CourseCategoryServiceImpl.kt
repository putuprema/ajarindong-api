package xyz.ajarindong.api.service

import org.springframework.stereotype.Service
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.dto.CourseCategoryDto
import xyz.ajarindong.api.dto.mapper.CourseCategoryMapper
import xyz.ajarindong.api.exception.EntityNotFoundException
import xyz.ajarindong.api.repository.CourseCategoryRepository

@Service
class CourseCategoryServiceImpl(
        private val courseCategoryRepository: CourseCategoryRepository
) : CourseCategoryService {
    override fun getCategories(): List<CourseCategoryDto> {
        return courseCategoryRepository.findAll().map { c -> CourseCategoryMapper.toMinimalCourseCategoryDto(c) }
    }

    override fun getCategoryDetail(id: String): CourseCategoryDto {
        val c = courseCategoryRepository.findById(id).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Kategori tidak ada") }
        return CourseCategoryMapper.toCourseCategoryDto(c)
    }
}