package br.com.fitogether.api.data.entity.goal

import br.com.fitogether.api.data.entity.BaseEntity
import br.com.fitogether.api.data.entity.user.UserEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity(name = "user_goals")
@SQLDelete(sql = "UPDATE user_goals SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
data class UserGoalEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    val user: UserEntity = UserEntity(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", nullable = false)
    val goal: GoalEntity = GoalEntity(),
) : BaseEntity();
