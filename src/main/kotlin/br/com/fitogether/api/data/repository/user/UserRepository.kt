package br.com.fitogether.api.data.repository.user

import br.com.fitogether.api.data.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
}