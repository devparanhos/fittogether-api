package br.com.fitogether.api.data.mapper.calltoaction

import br.com.fitogether.api.data.entity.calltoaction.CallToActionEntity
import br.com.fitogether.api.domain.model.CallToAction

fun CallToActionEntity.toModel(): CallToAction {
    return CallToAction(
        id = this.id,
        title = this.title,
        description = this.description,
        type = this.type,
        image = this.image
    )
}

fun List<CallToActionEntity>.toModelList(): List<CallToAction> {
    return this.map { it.toModel() }
}
