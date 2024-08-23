package br.com.fitogether.api.data.repository.code

import br.com.fitogether.api.data.entity.code.ValidationCodeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ValidationCodeRepository : JpaRepository<ValidationCodeEntity, Long?> {
    fun findByEmail(email: String) : ValidationCodeEntity?
}