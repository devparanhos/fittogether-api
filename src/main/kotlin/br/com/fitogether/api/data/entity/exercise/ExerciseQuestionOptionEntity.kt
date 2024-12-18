package br.com.fitogether.api.data.entity.exercise

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "exercise_question_options")
data class ExerciseQuestionOptionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_question_id", nullable = false)
    val exerciseQuestion: ExerciseQuestionEntity,

    @Column(columnDefinition = "TEXT", nullable = false)
    val description: String? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = true)
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null
)
