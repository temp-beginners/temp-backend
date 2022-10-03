package kr.dataportal.application.usercase.category

import kr.dataportal.application.definition.categoryDefinition
import kr.dataportal.application.persistence.entity.category.Category

interface SearchCategoryUseCase {
    //fun command(): List<Result>
    fun command(): List<Result>

    data class Result(
        var category: categoryDefinition

    )
    companion object {
        fun of(category: Category): Result {
            return Result(
                categoryDefinition(
                    id = category.id!!,
                    title = category.title,
                    description = category.description
                )
            )
        }
    }
}