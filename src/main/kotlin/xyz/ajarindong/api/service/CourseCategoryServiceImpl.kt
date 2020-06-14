package xyz.ajarindong.api.service

import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.springframework.http.*
import org.springframework.stereotype.Service
import xyz.ajarindong.api.constant.AppConstant
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.dto.CourseCategoryDto
import xyz.ajarindong.api.dto.mapper.CourseCategoryMapper
import xyz.ajarindong.api.exception.EntityNotFoundException
import xyz.ajarindong.api.repository.CourseCategoryRepository
import java.io.FileNotFoundException
import java.nio.file.Paths
import java.util.*

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

    override fun getIcon(id: String): ResponseEntity<ByteArray> {
        val m = courseCategoryRepository.findById(id).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Kategori tidak ada") }
        val icon = Optional.ofNullable(m.icon).orElseThrow { FileNotFoundException("Category icon does not exist") }

        val iconFile = Paths.get(AppConstant.STORAGE_BASE_PATH, AppConstant.STORAGE_COURSE_CATEGORY_FOLDER, m.id, icon).toFile()
        val inputStream = FileUtils.openInputStream(iconFile)
        val byteArray = inputStream.readAllBytes()
        inputStream.close()

        val headers = HttpHeaders()
        headers.setCacheControl(CacheControl.noCache())
        when (FilenameUtils.getExtension(icon)) {
            "png", "PNG" -> headers.contentType = MediaType.IMAGE_PNG
            "jpg", "JPG", "jpeg", "JPEG" -> headers.contentType = MediaType.IMAGE_JPEG
            "svg", "SVG" -> headers.contentType = MediaType.APPLICATION_OCTET_STREAM
            else -> headers.contentType = MediaType.IMAGE_JPEG
        }

        return ResponseEntity(byteArray, headers, HttpStatus.OK)
    }
}