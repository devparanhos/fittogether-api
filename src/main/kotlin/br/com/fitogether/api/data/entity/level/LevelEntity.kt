package br.com.fitogether.api.data.entity.level

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "level")
data class LevelEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val min: Int,

    @Column(nullable = false, unique = true)
    val max: Int,

    @Column(nullable = false)
    val description: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null
)