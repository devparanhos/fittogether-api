package br.com.fitogether.api.data.repository.password_reset_token

import br.com.fitogether.api.data.entity.password_reset_token.PasswordResetTokenEntity
import br.com.fitogether.api.data.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PasswordResetTokenRepository : JpaRepository<PasswordResetTokenEntity, Long> {
    fun findByToken(token: String): Optional<PasswordResetTokenEntity>
    fun deleteByUser(user: UserEntity)
}