package kr.dataportal.application.usercase.journey

import kr.dataportal.application.definition.JourneyNodeDefinition
import kr.dataportal.application.persistence.entity.category.Category
import kr.dataportal.application.persistence.entity.journey.Journey

interface CreateJourneyNodeUseCase {
    fun command(command: Command): Result

    data class Command(
        val category: Category,
        val journey: Journey,
        val title: String,
        val level: Long,
        val nodeNum: Long,
        val description: String? = "",
    )

    data class Result(
        var createJourneyNode: JourneyNodeDefinition
    )
}