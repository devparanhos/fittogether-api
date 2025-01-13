package br.com.fitogether.api.data.entity.preference

import br.com.fitogether.api.data.entity.BaseEntity
import br.com.fitogether.api.data.entity.gym.GymEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity(name = "preference_gyms")
@SQLDelete(sql = "UPDATE preference_gyms SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
data class PreferenceGymEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preference_id", nullable = false)
    @JsonBackReference
    val preference: PreferenceEntity = PreferenceEntity(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id", nullable = false)
    val gym: GymEntity = GymEntity(),
) : BaseEntity();

