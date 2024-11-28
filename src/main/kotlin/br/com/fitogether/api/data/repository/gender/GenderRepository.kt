package br.com.fitogether.api.data.repository.gender

import br.com.fitogether.api.data.entity.gender.GenderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GenderRepository : JpaRepository<GenderEntity, Long>