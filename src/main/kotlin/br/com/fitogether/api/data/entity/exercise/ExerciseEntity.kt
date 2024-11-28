package br.com.fitogether.api.data.entity.exercise

import br.com.fitogether.api.core.enums.Exercises
import jakarta.persistence.*

@Entity(name = "exercise")
data class ExerciseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    val name: Exercises? = null,

    @Column(name = "icon")
    val icon: String? = null
)
