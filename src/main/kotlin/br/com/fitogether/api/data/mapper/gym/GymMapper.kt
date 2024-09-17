package br.com.fitogether.api.data.mapper.gym

import br.com.fitogether.api.data.entity.gym.GymEntity
import br.com.fitogether.api.domain.model.gym.Gym

fun GymEntity.toModel(): Gym = Gym(
    id = this.id,
    name = this.name,
)