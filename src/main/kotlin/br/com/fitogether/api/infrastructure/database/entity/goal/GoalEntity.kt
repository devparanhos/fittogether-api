package br.com.fitogether.api.infrastructure.database.entity.goal

import br.com.fitogether.api.core.enums.Goals
import jakarta.persistence.*

@Entity(name = "goals")
data class GoalEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name="name")
    var name: Goals? = null,

    @Column(name="icon")
    var icon: String? = null,
)
