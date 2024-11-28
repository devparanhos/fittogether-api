package br.com.fitogether.api.data.mapper.gender

import br.com.fitogether.api.data.entity.gender.GenderEntity
import br.com.fitogether.api.domain.model.gender.Gender

fun GenderEntity.toModel() : Gender {
    return Gender(
        id = this.id,
        name = this.name?.value,
        icon = this.icon
    )
}