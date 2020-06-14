package xyz.ajarindong.api.constant

object AppConstant {
    const val JWT_ISSUER = "ajarindong.xyz"
    const val JWT_EXPIRATION_MS: Long = 86400000

    val IMAGE_EXTS = arrayOf("jpg", "png", "bmp", "jpeg")
    const val LOCAL_STORAGE_TEMP_FOLDER = "/tmp"

    const val DEFAULT_DATE_FORMAT = "dd/MM/yyyy"

    const val STORAGE_BASE_PATH = "D:/AjarinDong_storage/data"
    const val STORAGE_STUDENT_FOLDER = "/student"
    const val STORAGE_MENTOR_FOLDER = "/mentor"
    const val STORAGE_COURSE_CATEGORY_FOLDER = "/course-category"
    const val STORAGE_PAYMENT_METHOD_FOLDER = "/payment-method"
}