package br.com.fitogether.api.data.entity.gym

import br.com.fitogether.api.data.entity.address.AddressEntity
import br.com.fitogether.api.data.entity.exercise.ExerciseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "gyms")
data class GymEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToOne
    @JoinColumn(name = "address_id")
    val address: AddressEntity? = null,

    @Column(nullable = false)
    val name: String = null.toString(),

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null,

    @OneToMany
    @JoinTable(
        name = "gym_exercises",
        joinColumns = [JoinColumn(name = "exercise_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "gym_id", referencedColumnName = "id")]
    )
    val exercises: MutableSet<ExerciseEntity> = mutableSetOf(),
)
