package br.com.fitogether.api.data.repository.company

import br.com.fitogether.api.data.entity.company.CompanyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository : JpaRepository<CompanyEntity, Long>
