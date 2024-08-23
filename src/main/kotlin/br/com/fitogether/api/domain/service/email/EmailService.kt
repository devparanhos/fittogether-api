package br.com.fitogether.api.domain.service.email

import jakarta.mail.MessagingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    @Autowired private val mailSender: JavaMailSender
) {

    @Throws(MessagingException::class)
    fun sendValidationCode(to: String, code: Int) {
        val message = SimpleMailMessage().apply {
            setTo(to)
            setSubject("Código de validação - Fit Together")
            setText(code.toString())
        }

        mailSender.send(message)
    }
}