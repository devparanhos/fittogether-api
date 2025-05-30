package br.com.fitogether.api.data.entity.user

import br.com.fitogether.api.core.enums.RegistrationStep
import br.com.fitogether.api.core.enums.UserRegistrationStatus
import br.com.fitogether.api.data.entity.code.ValidationCodeEntity
import br.com.fitogether.api.data.entity.exercise.ExerciseEntity
import br.com.fitogether.api.data.entity.exercise.UserExerciseEntity
import br.com.fitogether.api.data.entity.experience.ExperienceEntity
import br.com.fitogether.api.data.entity.gender.GenderEntity
import br.com.fitogether.api.data.entity.goal.GoalEntity
import br.com.fitogether.api.data.entity.goal.UserGoalEntity
import br.com.fitogether.api.data.entity.preference.PreferenceEntity
import com.fasterxml.jackson.annotation.JsonManagedReference

import jakarta.persistence.*
import org.hibernate.annotations.Formula
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import java.util.Date

@Entity(name = "user")
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

    @Column(name = "access_token")
    val accessToken: String? = null,

    @ManyToOne
    @JoinColumn(name = "gender_id")
    val gender: GenderEntity? = null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var userGoals: MutableList<UserGoalEntity> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var userExercises: MutableList<UserExerciseEntity> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "experience_id")
    val experience: ExperienceEntity? = null,

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL])
    var preferences: PreferenceEntity? = null,

    @OneToOne(mappedBy = "user")
    var validationCode: ValidationCodeEntity? = null,

    @Column(name = "photo", nullable = false)
    val photo: String = "",

    @Column(nullable = false)
    val level: Int? = 0,

    @Formula(
        """
        (SELECT l.description 
         FROM level l 
         WHERE l.min <= level AND l.max >= level 
         LIMIT 1)
        """
    )
    val levelDescription: String? = null

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