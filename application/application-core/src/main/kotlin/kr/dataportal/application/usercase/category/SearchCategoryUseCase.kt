package kr.dataportal.application.usercase.category

import kr.dataportal.application.definition.CategoryDefinition

interface SearchCategoryUseCase {
    fun command(): Result

    data class Result(
        var searchCategory: CategoryDefinition
    )

}