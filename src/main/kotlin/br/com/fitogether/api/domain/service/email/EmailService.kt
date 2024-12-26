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
        try {
            val message = SimpleMailMessage().apply {
                setTo(to)
                subject = "Código de validação - Fit Together"
                text = code.toString()
            }

            mailSender.send(message)
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(MessagingException::class)
    fun sendPasswordResetEmail(to: String, token: String) {
        try {

            val message = SimpleMailMessage().apply {
                setTo(to)
                subject = "Redefinição de senha - Fit Together"
                text = "fittogether://password-reset?token=$token"
            }

            mailSender.send(message)
        } catch (e: Exception) {
            throw e
        }
    }
}