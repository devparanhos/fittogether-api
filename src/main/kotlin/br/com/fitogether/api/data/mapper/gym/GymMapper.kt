package br.com.fitogether.api.data.mapper.gym

import br.com.fitogether.api.data.entity.gym.GymEntity
import br.com.fitogether.api.data.mapper.address.toModel
import br.com.fitogether.api.domain.dto.response.GymResponse
import br.com.fitogether.api.domain.model.gym.Gym

fun GymEntity.toModel(): Gym {
    return Gym(
        id = this.id,
        name = this.name,
        address = this.address?.toModel()
    )
}

fun Gym.toGymResponse() = GymResponse (
    id = this.id,
    name = this.name,
    address = this.address
)