package br.com.fitogether.api.data.entity.gender

import br.com.fitogether.api.core.enums.Genders
import jakarta.persistence.*

@Entity(name = "genders")
data class GenderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name="name", nullable = false)
    @Enumerated(EnumType.STRING)
    var name: Genders? = null,

    @Column(name="icon")
    var icon: String? = null,
)

