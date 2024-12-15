package br.com.fitogether.api.domain.model.user

import br.com.fitogether.api.core.enums.RegistrationStep
import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.domain.model.exercise.Exercise
import br.com.fitogether.api.domain.model.goal.Goal
import br.com.fitogether.api.domain.model.preference.Preference

data class User(
    val id: Long?,
    val email: String,
    val name: String,
    val registrationStep: RegistrationStep,
    val photo: String?,
    val username: String,
    val gender: String?,
    val goals: List<Goal>,
    val exercises: List<Exercise>,
    val experience: String?,
    val preferences: Preference?,
    val registrationStatus: UserRegistrationStatus
)