package br.com.fitogether.api.data.mapper.experience

import br.com.fitogether.api.data.entity.experience.ExperienceEntity
import br.com.fitogether.api.domain.model.experience.Experience

fun ExperienceEntity.toModel(): Experience {
    return Experience(
        id = this.id,
        name = this.name?.value,
        description = this.description
    )
}