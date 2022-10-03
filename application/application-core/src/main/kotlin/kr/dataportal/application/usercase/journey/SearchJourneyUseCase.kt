package kr.dataportal.application.usercase.journey

import kr.dataportal.application.definition.JourneyDefinition
import kr.dataportal.application.persistence.entity.category.Category
import kr.dataportal.application.persistence.entity.journey.Journey

interface SearchJourneyUseCase {
    fun command(command: Command): List<Result>

    data class Command(
        val category: Category?,
    )

    data class Result(
        var journey: JourneyDefinition
    )

    companion object {
        fun of(journey: Journey): Result {
            return Result(
                JourneyDefinition(
                    id = journey.id!!,
                    title = journey.title,
                    description = journey.description
                )
            )
        }
    }
}