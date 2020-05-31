package xyz.ajarindong.api.exception

import xyz.ajarindong.api.constant.ErrCode

open class AjarinDongException(
        val errCode: ErrCode,
        message: String
) : RuntimeException(message)