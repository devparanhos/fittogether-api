package br.com.fitogether.api.data.repository.user

import br.com.fitogether.api.data.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun findByUsername(username: String?): UserEntity?
    fun findByAccessToken(accessToken: String): UserEntity?
}