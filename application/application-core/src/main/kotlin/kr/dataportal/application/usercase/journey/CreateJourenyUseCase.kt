package kr.dataportal.application.usercase.journey

import kr.dataportal.application.definition.JourneyDefinition
import kr.dataportal.application.persistence.entity.category.Category

interface CreateJourneyUseCase {
    fun command(command: Command): Result

    data class Command(
        val category: Category,
        var title: String,
        val description: String?
    )

    data class Result(
        var journey: JourneyDefinition
    )
}