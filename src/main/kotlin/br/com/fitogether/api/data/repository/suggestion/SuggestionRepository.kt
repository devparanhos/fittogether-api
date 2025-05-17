
package br.com.fitogether.api.data.repository.suggestion

import br.com.fitogether.api.data.entity.suggestion.SuggestionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SuggestionRepository : JpaRepository<SuggestionEntity, Long> {
    fun findByType(type: String): List<SuggestionEntity>
    fun findByTypeAndDeletedAtIsNull(type: String): List<SuggestionEntity>
}