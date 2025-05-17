package br.com.fitogether.api.data.mapper.suggestion

import br.com.fitogether.api.data.entity.suggestion.SuggestionEntity
import br.com.fitogether.api.domain.model.Suggestion

fun SuggestionEntity.toModel(): Suggestion {
    return Suggestion(
        id = this.id,
        title = this.title,
        description = this.description,
        type = this.type,
        image = this.image
    )
}

fun List<SuggestionEntity>.toModelList(): List<Suggestion> {
    return this.map { it.toModel() }
}