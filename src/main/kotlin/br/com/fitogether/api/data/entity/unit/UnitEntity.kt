package br.com.fitogether.api.data.entity.unit

import br.com.fitogether.api.core.enums.UnitType
import br.com.fitogether.api.data.entity.address.AddressEntity
import br.com.fitogether.api.data.entity.company.CompanyEntity
import br.com.fitogether.api.data.entity.exercise.ExerciseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "units")
data class UnitEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    val company: CompanyEntity = CompanyEntity(),

    @OneToOne
    @JoinColumn(name = "address_id")
    val address: AddressEntity? = null,

    @Column(nullable = false)
    val name: String = "",

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val type: UnitType = UnitType.GYM,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "unit_exercises",
        joinColumns = [JoinColumn(name = "unit_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "exercise_id", referencedColumnName = "id")]
    )
    val exercises: MutableSet<ExerciseEntity> = mutableSetOf(),
)
