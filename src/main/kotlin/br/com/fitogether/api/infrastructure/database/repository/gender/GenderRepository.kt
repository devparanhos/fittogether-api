package br.com.fitogether.api.infrastructure.database.repository.gender

import br.com.fitogether.api.infrastructure.database.entity.gender.GenderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GenderRepository : JpaRepository<GenderEntity, Long>