package xyz.ajarindong.api.service

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.io.IOException
import java.io.OutputStream
import java.io.Writer

@Service
class JsonMapperService {
    private val mapper = ObjectMapper()

    init {
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
    }

    @Throws(IOException::class)
    fun write(out: OutputStream?, value: Any?) {
        mapper.writeValue(out, value)
    }

    @Throws(IOException::class)
    fun write(writer: Writer?, value: Any?) {
        mapper.writeValue(writer, value)
    }
}