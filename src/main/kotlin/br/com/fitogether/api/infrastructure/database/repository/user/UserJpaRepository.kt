package br.com.fitogether.api.infrastructure.database.repository.user

import br.com.fitogether.api.infrastructure.database.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun findByUsername(username: String): UserEntity?
}