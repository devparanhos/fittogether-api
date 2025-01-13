package br.com.fitogether.api.data.entity.preference

import br.com.fitogether.api.data.entity.BaseEntity
import br.com.fitogether.api.data.entity.gender.GenderEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity(name = "preference_genders")
@SQLDelete(sql = "UPDATE preference_genders SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
data class PreferenceGenderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preference_id", nullable = false)
    @JsonBackReference
    val preference: PreferenceEntity = PreferenceEntity(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id", nullable = false)
    val gender: GenderEntity = GenderEntity(),
) : BaseEntity();

