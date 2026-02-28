package br.com.fitogether.api.data.entity.preference

import br.com.fitogether.api.data.entity.BaseEntity
import br.com.fitogether.api.data.entity.unit.UnitEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity(name = "preference_units")
@SQLDelete(sql = "UPDATE preference_units SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
data class PreferenceUnitEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preference_id", nullable = false)
    @JsonBackReference
    val preference: PreferenceEntity = PreferenceEntity(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    val unit: UnitEntity = UnitEntity(),
) : BaseEntity()
