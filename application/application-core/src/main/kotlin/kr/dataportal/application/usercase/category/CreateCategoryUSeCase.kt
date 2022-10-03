package kr.dataportal.application.usercase.category

import kr.dataportal.application.definition.categoryDefinition
import kr.dataportal.application.enums.category

interface CreateCategoryUseCase {
    fun command(command: Command): Result

    data class Command(
        val title: category,
        val description: String?
    )
 
    data class Result(
        var category: categoryDefinition
    )
}