package br.com.fitogether.api.data.mapper.unit

import br.com.fitogether.api.data.entity.unit.UnitEntity
import br.com.fitogether.api.data.mapper.address.toModel
import br.com.fitogether.api.data.mapper.company.toModel
import br.com.fitogether.api.data.mapper.exercise.toModel
import br.com.fitogether.api.domain.dto.response.UnitResponse
import br.com.fitogether.api.domain.model.unit.Unit

fun UnitEntity.toModel(): Unit = Unit(
    id = this.id,
    company = this.company.toModel(),
    name = this.name,
    address = this.address?.toModel(),
    type = this.type,
    exercises = this.exercises.map { it.toModel() },
)

fun Unit.toUnitResponse(): UnitResponse = UnitResponse(
    id = this.id,
    companyName = this.company?.name,
    name = this.name,
    address = this.address,
    type = this.type,
    exercises = this.exercises,
)
