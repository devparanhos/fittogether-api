package br.com.fitogether.api.data.repository.gym

import br.com.fitogether.api.data.entity.gym.GymEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GymRepository: JpaRepository<GymEntity, Long> {
}