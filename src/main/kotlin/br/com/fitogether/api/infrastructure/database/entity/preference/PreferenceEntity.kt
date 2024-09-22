package br.com.fitogether.api.infrastructure.database.entity.preference

import br.com.fitogether.api.infrastructure.database.entity.gender.GenderEntity
import jakarta.persistence.*

@Entity(name="preferences")
data class PreferenceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(name="start_age", nullable = false)
    val startAge: Int? = null,

    @Column(name="end_age", nullable = false)
    val endAge: Int? = null,

    @Column(name="radius_distance", nullable = false)
    val radiusDistance: Int? = null,

    @ManyToMany
    @JoinTable(
        name="genders_preferences",
        joinColumns = [JoinColumn(name = "preference_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "gender_id", referencedColumnName = "id")])
    val genders: Set<GenderEntity> = setOf(),
)
