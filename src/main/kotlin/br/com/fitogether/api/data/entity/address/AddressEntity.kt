package br.com.fitogether.api.data.entity.address

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "addresses")
data class AddressEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val street: String = null.toString(),

    @Column(nullable = false)
    val neighbourhood: String = null.toString(),

    val number: String? = null,

    val complement: String? = null,

    @Column(nullable = false, length = 2)
    val state: String = null.toString(),

    @Column(nullable = false, length = 100)
    val country: String = "Brazil",

    @Column(nullable = false, length = 25)
    val zipcode: String = null.toString(),

    @Column(nullable = false, length = 25)
    val city: String = null.toString(),

    val lat: String? = null,

    val lng: String? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null
)


