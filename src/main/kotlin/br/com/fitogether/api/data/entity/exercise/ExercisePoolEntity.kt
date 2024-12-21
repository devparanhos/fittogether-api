package br.com.fitogether.api.data.entity.exercise

import br.com.fitogether.api.data.entity.user.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "exercise_pool")
@Table(name = "exercise_pool")
data class ExercisePoolEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    val exercise: ExerciseEntity? = null,

    var startTime: LocalDateTime? = null,
    var endTime: LocalDateTime? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null,

    @OneToMany(mappedBy = "exercisePool", cascade = [CascadeType.ALL], orphanRemoval = true)
    val answers: List<ExercisePoolAnswerEntity?> = mutableListOf()
)