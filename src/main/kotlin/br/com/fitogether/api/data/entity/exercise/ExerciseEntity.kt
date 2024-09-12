package br.com.fitogether.api.data.entity.exercise

import jakarta.persistence.*

@Entity(name = "exercise")
data class ExerciseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String = "",

    @Column(name = "icon")
    val icon: String? = null
)
