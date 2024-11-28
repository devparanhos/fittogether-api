package br.com.fitogether.api.infrastructure.database.mapper.gender

import br.com.fitogether.api.infrastructure.database.entity.gender.GenderEntity
import br.com.fitogether.api.domain.model.gender.Gender

fun GenderEntity.toModel() : Gender {
    return Gender(
        id = this.id,
        name = this.name?.value,
        icon = this.icon
    )
}