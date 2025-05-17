package br.com.fitogether.api.data.entity.suggestion

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "suggestions")
class SuggestionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "title", nullable = false)
    val title: String? = null,

    @Column(name = "description", nullable = false)
    val description: String? = null,

    @Column(name = "type", nullable = false)
    val type: String? = null,

    @Column(name = "image", nullable = false, columnDefinition = "TEXT")
    val image: String? = null,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null
)