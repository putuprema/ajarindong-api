package xyz.ajarindong.api.service

import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.springframework.http.*
import org.springframework.stereotype.Service
import xyz.ajarindong.api.constant.AppConstant
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.dto.PaymentMethodDto
import xyz.ajarindong.api.dto.mapper.PaymentMethodMapper
import xyz.ajarindong.api.exception.EntityNotFoundException
import xyz.ajarindong.api.repository.PaymentMethodRepository
import java.io.FileNotFoundException
import java.nio.file.Paths
import java.util.*

@Service
class PaymentMethodServiceImpl(
        private val paymentMethodRepository: PaymentMethodRepository
) : PaymentMethodService {
    override fun getPaymentMethods(): List<PaymentMethodDto> =
            paymentMethodRepository.findAll().map { p -> PaymentMethodMapper.toPaymentMethodDto(p) }

    override fun getIcon(id: String): ResponseEntity<ByteArray> {
        val m = paymentMethodRepository.findById(id).orElseThrow { EntityNotFoundException(ErrCode.NOT_FOUND, "Metode pembayaran tidak ada") }
        val icon = Optional.ofNullable(m.icon).orElseThrow { FileNotFoundException("Payment method icon does not exist") }

        val iconFile = Paths.get(AppConstant.STORAGE_BASE_PATH, AppConstant.STORAGE_PAYMENT_METHOD_FOLDER, m.id, icon).toFile()
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