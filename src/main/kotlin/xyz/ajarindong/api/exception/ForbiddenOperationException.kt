package xyz.ajarindong.api.exception

import xyz.ajarindong.api.constant.ErrCode

class ForbiddenOperationException(
        errCode: ErrCode,
        message: String
) : AjarinDongException(errCode, message) {
}