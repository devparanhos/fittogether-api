package br.com.fitogether.api.infrastructure.database.entity.experience

import br.com.fitogether.api.core.enums.Experiences
import jakarta.persistence.*

@Entity(name="experiences")
data class ExperienceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name="name", nullable = false)
    @Enumerated(EnumType.STRING)
    val name: Experiences? = null,

    @Column(name="description")
    val description: String? = null,
)