package br.com.fitogether.api.data.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PreUpdate
import org.hibernate.annotations.Where
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null

    fun softDelete() {
        this.deletedAt = LocalDateTime.now()
    }

    fun isDeleted(): Boolean = deletedAt != null

    @PreUpdate
    fun setUpdatedAt() {
        this.updatedAt = LocalDateTime.now()
    }
}