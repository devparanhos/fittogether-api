package br.com.fitogether.api.data.entity.exercise

import br.com.fitogether.api.core.enums.QuestionType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "exercise_questions")
data class ExerciseQuestionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    val exercise: ExerciseEntity? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: QuestionType = QuestionType.SINGLE,

    @Column(columnDefinition = "TEXT", nullable = true)
    val description: String? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = true)
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null,

    @OneToMany(mappedBy = "exerciseQuestion", cascade = [CascadeType.ALL], orphanRemoval = true)
    val options: MutableList<ExerciseQuestionOptionEntity?> = mutableListOf()
)
