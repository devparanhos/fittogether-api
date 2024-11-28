package br.com.fitogether.api.infrastructure.database.repository.code

import br.com.fitogether.api.infrastructure.database.entity.code.ValidationCodeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ValidationCodeJpaRepository : JpaRepository<ValidationCodeEntity, Long?> {
    fun findByEmail(email: String) : ValidationCodeEntity?
}