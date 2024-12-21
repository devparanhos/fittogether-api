package br.com.fitogether.api.data.entity.exercise

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "exercise_pool_answer")
@IdClass(ExercisePoolAnswerId::class)
data class ExercisePoolAnswerEntity(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_question_option_id", nullable = false)
    val exerciseQuestionOption: ExerciseQuestionOptionEntity? = null,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_pool_id", nullable = false)
    val exercisePool: ExercisePoolEntity? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null
)