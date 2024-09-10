package br.com.fitogether.api.data.entity.goal

import br.com.fitogether.api.data.entity.user.UserEntity
import jakarta.persistence.*

@Entity(name = "goal")
data class GoalEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name="name", nullable = false)
    var name: String = "",

    @Column(name="icon")
    var icon: String? = null,

    @ManyToMany
    @JoinTable(
        name = "user_goals",
        joinColumns = [JoinColumn(name = "goal_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
    )
    var users: Set<UserEntity> = setOf()
)
