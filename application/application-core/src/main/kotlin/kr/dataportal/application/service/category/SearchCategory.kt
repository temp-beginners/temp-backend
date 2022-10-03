package kr.dataportal.application.service.category

import kr.dataportal.application.persistence.repository.category.CategoryRepository
import kr.dataportal.application.usercase.category.SearchCategoryUseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SearchCategory(
    private val categoryRepository: CategoryRepository
) : SearchCategoryUseCase {

    @Transactional(readOnly = true)
    override fun command(): List<SearchCategoryUseCase.Result> {
        return categoryRepository.findAllBy()!!.map { category -> SearchCategoryUseCase.of(category) }
    }

}