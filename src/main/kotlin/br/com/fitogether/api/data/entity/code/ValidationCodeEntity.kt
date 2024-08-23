package br.com.fitogether.api.data.entity.code

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "validation_code")
data class ValidationCodeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "email", unique = true, nullable = false)
    var email: String = "",

    @Column(name="code", nullable = false)
    var code: Int = 0,

    @Column(name="duration", nullable = false)
    var duration: Int = 90,

    @Column(name="created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),
)
