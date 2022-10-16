package kr.dataportal.application.usercase.journey

import kr.dataportal.application.definition.JourneyDefinition

interface SearchJourneyUseCase {
    fun command(command: Command): Result

    data class Command(
        val categoryId: Long? = null
    )

    data class Result(
        var searchJourney: JourneyDefinition
    )
}