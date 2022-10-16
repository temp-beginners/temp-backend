package kr.dataportal.application.usercase.category

import kr.dataportal.application.definition.CategoryDefinition

interface CreateCategoryUseCase {
    fun command(command: Command): Result

    data class Command(
        val title: String,
        val description: String = ""
    )

    data class Result(
        var createCategory: CategoryDefinition
    )
}