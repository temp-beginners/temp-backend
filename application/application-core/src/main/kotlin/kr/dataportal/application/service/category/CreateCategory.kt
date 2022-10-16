package kr.dataportal.application.service.category

import kr.dataportal.application.definition.CategoryDefinition
import kr.dataportal.application.definition.CategoryResponse
import kr.dataportal.application.persistence.config.jpa.requiredId
import kr.dataportal.application.persistence.entity.category.Category
import kr.dataportal.application.persistence.repository.category.CategoryRepository
import kr.dataportal.application.usercase.category.CreateCategoryUseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CreateCategory(
    private val categoryRepository: CategoryRepository
) : CreateCategoryUseCase {

    @Transactional
    override fun command(command: CreateCategoryUseCase.Command): CreateCategoryUseCase.Result {
        val (title, description) = command

        val category = Category.create(title = title, description = description)
            .let(categoryRepository::save)

        return CreateCategoryUseCase.Result(
            createCategory = CategoryDefinition(
                categoryList = listOf(
                    CategoryResponse(
                        id = category.requiredId,
                        title = category.title,
                        description = category.description,
                    )
                ),
            )
        )
    }
}