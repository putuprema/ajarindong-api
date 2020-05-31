package xyz.ajarindong.api.exception

import xyz.ajarindong.api.constant.ErrCode

class EntityNotFoundException(
        errCode: ErrCode,
        message: String
) : AjarinDongException(errCode, message) {
}