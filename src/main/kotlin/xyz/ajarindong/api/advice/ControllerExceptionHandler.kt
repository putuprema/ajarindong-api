package xyz.ajarindong.api.advice

import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import xyz.ajarindong.api.constant.ErrCode
import xyz.ajarindong.api.dto.ResponseEnvelope
import xyz.ajarindong.api.exception.AjarinDongException
import java.io.FileNotFoundException

@RestControllerAdvice
class ControllerExceptionHandler : ResponseEntityExceptionHandler() {
    override fun handleExceptionInternal(ex: java.lang.Exception, @Nullable body: Any?, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity.status(status).body(ResponseEnvelope<Any>(status.value().toString(), status.reasonPhrase))
    }

    override fun handleNoHandlerFoundException(ex: NoHandlerFoundException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity.status(status).body(ResponseEnvelope<Any>(status.value().toString(), status.reasonPhrase))
    }

    override fun handleHttpRequestMethodNotSupported(ex: HttpRequestMethodNotSupportedException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity.status(status).body(ResponseEnvelope<Any>(status.value().toString(), status.reasonPhrase))
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val re = ResponseEnvelope<Any>(ErrCode.FIELD_INVALID.code, "Bad Request")
        val objectErrors = ex.bindingResult.allErrors
        if (objectErrors.isNotEmpty()) {
            val err = objectErrors[0]
            if (err.defaultMessage != null) {
                re.statusMessage = err.defaultMessage!!
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re)
    }

    override fun handleBindException(ex: BindException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val re = ResponseEnvelope<Any>(ErrCode.FIELD_INVALID.code, "Bad Request")
        val objectErrors = ex.bindingResult.allErrors
        if (objectErrors.isNotEmpty()) {
            val err = objectErrors[0]
            if (err.defaultMessage != null) {
                re.statusMessage = err.defaultMessage!!
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException::class)
    fun handleFileNotFoundException(ex: FileNotFoundException, handlerMethod: HandlerMethod): ResponseEntity<ResponseEnvelope<Any>> {
        val log = LoggerFactory.getLogger(handlerMethod.method.declaringClass)
        log.warn("Exception caught: {}", ex.message, ex)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseEnvelope(ErrCode.NOT_FOUND.code, ex.message!!))
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AjarinDongException::class)
    fun handleCashitException(ex: AjarinDongException, handlerMethod: HandlerMethod): ResponseEntity<ResponseEnvelope<Any>> {
        val log = LoggerFactory.getLogger(handlerMethod.method.declaringClass)
        log.warn("Exception caught: {}", ex.message, ex)
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseEnvelope(ex.errCode.code, ex.message!!))
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: Exception, handlerMethod: HandlerMethod): ResponseEntity<ResponseEnvelope<Any>> {
        val log = LoggerFactory.getLogger(handlerMethod.method.declaringClass)
        log.warn("Exception caught: {}", ex.message, ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseEnvelope(ErrCode.UNKNOWN.code, ErrCode.UNKNOWN.message))
    }
}