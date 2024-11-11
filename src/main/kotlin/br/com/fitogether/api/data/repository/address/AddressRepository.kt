package br.com.fitogether.api.data.repository.address

import br.com.fitogether.api.data.entity.address.AddressEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository: JpaRepository<AddressEntity, Long> {
}