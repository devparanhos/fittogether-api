package br.com.fitogether.api.domain.model

import br.com.fitogether.api.domain.model.gender.Gender

data class Preferences(
    val id: Long? = null,
    val startAge: Int? = null,
    val endAge: Int? = null,
    val radiusDistance: Int? = null,
    val genders: List<Gender>? = null
)
