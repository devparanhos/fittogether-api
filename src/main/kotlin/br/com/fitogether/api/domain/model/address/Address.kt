package br.com.fitogether.api.domain.model.address

data class Address(
    val id: Long,
    val street: String,
    val neighbourhood: String,
    val number: String?,
    val complement: String?,
    val state: String,
    val country: String,
    val zipcode: String,
    val city: String,
    val lat: String?,
    val lng: String?,
)
