package br.com.fitogether.api.data.entity.user

import br.com.fitogether.api.core.enums.RegistrationStep
import br.com.fitogether.api.core.enums.UserRegistrationStatus

import jakarta.persistence.*

import java.util.Date

@Entity(name = "user")
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "email", nullable = false, unique = true)
    var email: String = "",

    @Column(name = "username", nullable = false, unique = true)
    @get:JvmName("getUsernameInternal")
    var username: String = "",

    @Column(name = "name", nullable = false)
    var name: String = "",

    @Column(name = "cellphone", nullable = false)
    var cellphone: String = "",

    @Column(name = "birth_date", nullable = false)
    var birthDate: Date = Date(),

    @Column(name = "password", nullable = false)
    @get:JvmName("getPasswordInternal")
    var password: String = "",

    @Column(name = "registration_status", nullable = false)
    @Enumerated(EnumType.STRING)
    var registrationStatus: UserRegistrationStatus = UserRegistrationStatus.IN_REGISTRATION,

    @Column(name = "registration_step", nullable = false)
    @Enumerated(EnumType.STRING)
    var registrationStep: RegistrationStep = RegistrationStep.GENDER,

    @Column(name="access_token")
    var accessToken: String? = null,
)