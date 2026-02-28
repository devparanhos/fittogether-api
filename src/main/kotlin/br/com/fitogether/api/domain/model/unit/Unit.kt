package br.com.fitogether.api.domain.model.unit

import br.com.fitogether.api.core.enums.UnitType
import br.com.fitogether.api.domain.model.address.Address
import br.com.fitogether.api.domain.model.company.Company
import br.com.fitogether.api.domain.model.exercise.Exercise

data class Unit(
    val id: Long? = null,
    val company: Company? = null,
    val name: String = "",
    val address: Address? = null,
    val type: UnitType = UnitType.GYM,
    val exercises: List<Exercise> = emptyList(),
)
