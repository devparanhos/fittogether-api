package br.com.fitogether.api.data.repository.calltoaction

import br.com.fitogether.api.data.entity.calltoaction.CallToActionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CallToActionRepository : JpaRepository<CallToActionEntity, Long> {
    fun findByType(type: String): List<CallToActionEntity>
    fun findByTypeAndDeletedAtIsNull(type: String): List<CallToActionEntity>
}
