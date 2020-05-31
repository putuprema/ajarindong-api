package xyz.ajarindong.api.exception

import xyz.ajarindong.api.constant.ErrCode

class EntityAlreadyExistException(
        errCode: ErrCode,
        message: String
) : AjarinDongException(errCode, message) {
}