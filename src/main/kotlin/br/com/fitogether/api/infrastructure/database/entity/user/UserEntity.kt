package br.com.fitogether.api.infrastructure.database.entity.user

import br.com.fitogether.api.core.enums.RegistrationStep
import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.infrastructure.database.entity.exercise.ExerciseEntity
import br.com.fitogether.api.infrastructure.database.entity.experience.ExperienceEntity
import br.com.fitogether.api.infrastructure.database.entity.gender.GenderEntity
import br.com.fitogether.api.infrastructure.database.entity.goal.GoalEntity
import br.com.fitogether.api.infrastructure.database.entity.gym.GymEntity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import java.util.Date

@Entity(name = "users")
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "email", nullable = false, unique = true)
    val email: String = "",

    @Column(name = "username", nullable = false, unique = true)
    @get:JvmName("getUsernameInternal")
    val username: String = "",

    @Column(name = "name", nullable = false)
    val name: String = "",

    @Column(name = "cellphone", nullable = false)
    val cellphone: String = "",

    @Column(name = "birth_date", nullable = false)
    val birthDate: Date = Date(),

    @Column(name = "password", nullable = false)
    @get:JvmName("getPasswordInternal")
    val password: String = "",

    @Column(name = "registration_status", nullable = false)
    @Enumerated(EnumType.STRING)
    val registrationStatus: UserRegistrationStatus = UserRegistrationStatus.IN_REGISTRATION,

    @Column(name = "registration_step", nullable = false)
    @Enumerated(EnumType.STRING)
    val registrationStep: RegistrationStep = RegistrationStep.GENDER,

    @Column(name="access_token")
    val accessToken: String? = null,

    @ManyToOne
    @JoinColumn(name = "gender_id")
    val gender: GenderEntity? = null,

    @ManyToMany
    @JoinTable(
        name = "user_goals",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "goal_id", referencedColumnName = "id")]
    )
    val goals: MutableSet<GoalEntity> = mutableSetOf(),

    @ManyToMany
    @JoinTable(
        name = "user_exercises",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "exercise_id", referencedColumnName = "id")]
    )
    val exercises: MutableSet<ExerciseEntity> = mutableSetOf(),

    @ManyToOne
    @JoinColumn(name = "experience_id")
    val experience: ExperienceEntity? = null,

    @ManyToOne
    @JoinColumn(name = "preference_id")
    val preferences: br.com.fitogether.api.infrastructure.database.entity.preference.PreferenceEntity? = null,

    @ManyToMany
    @JoinTable(
        name = "user_gyms",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "gym_id", referencedColumnName = "id")]
    )
    val gyms: Set<GymEntity> = setOf()
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }
}