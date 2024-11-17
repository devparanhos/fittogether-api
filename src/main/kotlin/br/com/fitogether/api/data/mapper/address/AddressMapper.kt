package br.com.fitogether.api.data.mapper.address

import br.com.fitogether.api.data.entity.address.AddressEntity
import br.com.fitogether.api.domain.model.address.Address

fun AddressEntity.toModel() = this.id?.let {
    Address(
        id = it,
        street = this.street,
        neighbourhood = this.neighbourhood,
        number = this.number,
        complement = this.complement,
        state = this.state,
        country = this.country,
        zipcode = this.zipcode,
        city = this.city,
        lat = this.lat,
        lng = this.lng
    )
}