package xyz.ajarindong.api.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import xyz.ajarindong.api.model.*
import xyz.ajarindong.api.repository.CourseCategoryRepository
import xyz.ajarindong.api.repository.CourseRepository
import xyz.ajarindong.api.repository.MentorRepository
import xyz.ajarindong.api.repository.PaymentMethodRepository
import java.time.LocalDate
import java.time.Month

@Service
class DataInitializerService(
        private val paymentMethodRepository: PaymentMethodRepository,
        private val courseRepository: CourseRepository,
        private val mentorRepository: MentorRepository,
        private val courseCategoryRepository: CourseCategoryRepository,
        private val passwordEncoder: PasswordEncoder
) {
    //    @PostConstruct
    fun init() {
        // Payment Method
        val paymentMethods = arrayListOf(
                PaymentMethod("BCA Virtual Account", "Bayar dengan BCA Virtual Account", "icon.png"),
                PaymentMethod("Mandiri Virtual Account", "Bayar dengan Mandiri Virtual Account", "icon.png"),
                PaymentMethod("BRI Virtual Account", "Bayar dengan BRI Virtual Account", "icon.png"),
                PaymentMethod("BNI Virtual Account", "Bayar dengan BNI Virtual Account", "icon.png")
        )
        paymentMethodRepository.saveAll(paymentMethods)

        // Mentors
        val putu = Mentor("Putu Prema",
                "iputupremaananda@gmail.com",
                passwordEncoder.encode("123456"),
                LocalDate.of(2000, Month.JUNE, 16), "Lorem ipsum dolor sit amet.")
        putu.rating = 5.0
        val vincent = Mentor("Vincent",
                "vincent@gmail.com",
                passwordEncoder.encode("123456"),
                LocalDate.of(2000, Month.JUNE, 16), "Lorem ipsum dolor sit amet.")
        vincent.rating = 4.5
        val tony = Mentor("Tony",
                "tony@gmail.com",
                passwordEncoder.encode("123456"),
                LocalDate.of(2000, Month.JUNE, 16), "Lorem ipsum dolor sit amet.")
        tony.rating = 4.7
        mentorRepository.saveAll(arrayListOf(putu, vincent, tony))

        // Course category
        val web = CourseCategory("Web Development", "#6B85C2", "icon.svg")
        val ai = CourseCategory("Artificial Intelligence", "#6BC291", "icon.svg")

        // Courses for web
        val a = Course(web,
                "Full Stack Web Development",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                500000,
                "https://syndlab.com/files/view/5db9b150252346nbDR1gKP3OYNuwBhXsHJerdToc5I0SMLfk7qlv951730.jpeg")
        a.courseSubjects = arrayListOf(
                CourseSubject(a, 1, "Basic HTML", "Lorem ipsum", "#5DB85D"),
                CourseSubject(a, 2, "Basic Javascript", "Lorem ipsum", "#5D60B8"),
                CourseSubject(a, 3, "Basic Database", "Lorem ipsum", "#A65DB8"),
                CourseSubject(a, 4, "Basic React", "Lorem ipsum", "#B8635D")
        )
        a.benefits = arrayListOf(
                "Pengembangan website full stack",
                "Memahami isu-isu yang sering terjadi dalam pengembangan website",
                "Dapat menggunakan framework modern seperti React & Express.js"
        )
        a.mentors = arrayListOf(putu, vincent)

        val b = Course(web,
                "Master Angular JS",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                750000,
                "https://syndlab.com/files/view/5db9b150252346nbDR1gKP3OYNuwBhXsHJerdToc5I0SMLfk7qlv951730.jpeg")
        b.courseSubjects = arrayListOf(
                CourseSubject(b, 1, "Basic Typescript", "Lorem ipsum", "#5DB85D"),
                CourseSubject(b, 2, "Basic Angular", "Lorem ipsum", "#5D60B8"),
                CourseSubject(b, 3, "Koneksi ke API", "Lorem ipsum", "#A65DB8")
        )
        b.benefits = arrayListOf(
                "Pengembangan single page application dengan Angular",
                "Bisa melakukan unit testing untuk aplikasi yang dibuat"
        )
        b.mentors = arrayListOf(putu, vincent)

        // Courses for AI
        val c = Course(ai,
                "Object Recognition dengan Deep Learning",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                2500000,
                "https://syndlab.com/files/view/5db9b150252346nbDR1gKP3OYNuwBhXsHJerdToc5I0SMLfk7qlv951730.jpeg")
        c.courseSubjects = arrayListOf(
                CourseSubject(c, 1, "Basic Python", "Lorem ipsum", "#5DB85D"),
                CourseSubject(c, 2, "Machine Learning Concepts", "Lorem ipsum", "#5D60B8"),
                CourseSubject(c, 3, "Machine Learning Algorithms", "Lorem ipsum", "#A65DB8"),
                CourseSubject(c, 4, "Deep Learning", "Lorem ipsum", "#B8635D")
        )
        c.benefits = arrayListOf(
                "Pengembangan artificial intelligence model untuk object recognition",
                "Bisa melakukan object recognition dengan deep learning",
                "Membuat aplikasi mobile/web berbasis deep learning"
        )
        c.mentors = arrayListOf(putu, tony)

        web.courses = arrayListOf(a, b)
        ai.courses = arrayListOf(c)
        courseCategoryRepository.saveAll(arrayListOf(web, ai))
    }
}