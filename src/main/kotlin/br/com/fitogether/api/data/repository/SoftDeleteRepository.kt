package br.com.fitogether.api.data.repository

import br.com.fitogether.api.data.entity.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository

@NoRepositoryBean
interface SoftDeleteRepository<T, ID> : JpaRepository<T, ID> {
    fun softDeleteById(id: ID)
}


class SoftDeleteRepositoryImpl<T, ID>(
    entityInformation: JpaEntityInformation<T, ID>,
    private val entityManager: EntityManager
) : SimpleJpaRepository<T, ID>(entityInformation, entityManager), SoftDeleteRepository<T, ID> {

    override fun softDeleteById(id: ID) {
        val entity = entityManager.find(domainClass, id)
        if (entity is BaseEntity) {
            entity.softDelete()
            entityManager.persist(entity)
        }
    }
}