package br.com.fitogether.api.data.entity.preference

import br.com.fitogether.api.data.entity.gender.GenderEntity
import br.com.fitogether.api.data.entity.goal.UserGoalEntity
import br.com.fitogether.api.data.entity.unit.UnitEntity
import br.com.fitogether.api.data.entity.user.UserEntity
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "preferences")
data class PreferenceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "start_age", nullable = false)
    var startAge: Int = 0,

    @Column(name = "end_age", nullable = false)
    var endAge: Int = 0,

    @Column(name = "radius_distance", nullable = false)
    var radiusDistance: Int = 0,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null,

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    val user: UserEntity? = null,

    @OneToMany(mappedBy = "preference", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var preferenceGenders: MutableList<PreferenceGenderEntity> = mutableListOf(),

    @OneToMany(mappedBy = "preference", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var preferenceUnits: MutableList<PreferenceUnitEntity> = mutableListOf(),

    @OneToMany(mappedBy = "preference", cascade = [CascadeType.ALL], orphanRemoval = true)
    val schedules: MutableList<PreferenceScheduleEntity> = mutableListOf()
)