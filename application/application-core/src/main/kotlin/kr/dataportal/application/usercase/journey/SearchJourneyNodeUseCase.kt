package kr.dataportal.application.usercase.journey

import kr.dataportal.application.definition.JourneyNodeDefinition

interface SearchJourneyNodeUseCase {
    fun command(command: Command): Result

    data class Command(
        val categoryId: Long,
        val journeyId: Long,
    )

    data class Result(
        var journeyNode: JourneyNodeDefinition
    )
}