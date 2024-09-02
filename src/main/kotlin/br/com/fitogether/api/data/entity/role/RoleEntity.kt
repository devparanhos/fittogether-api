package br.com.fitogether.api.data.entity.role

import br.com.fitogether.api.core.enums.Roles
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity(name = "role")
data class RoleEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "description", nullable = false)
    @Enumerated(EnumType.STRING)
    var description: Roles? = null,

    ) : GrantedAuthority {
    override fun getAuthority(): String {
        return this.description?.value.toString()
    }
}
