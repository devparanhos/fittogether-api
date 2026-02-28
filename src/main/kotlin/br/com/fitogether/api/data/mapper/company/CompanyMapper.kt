package br.com.fitogether.api.data.mapper.company

import br.com.fitogether.api.data.entity.company.CompanyEntity
import br.com.fitogether.api.domain.model.company.Company

fun CompanyEntity.toModel(): Company = Company(
    id = this.id,
    name = this.name,
)
