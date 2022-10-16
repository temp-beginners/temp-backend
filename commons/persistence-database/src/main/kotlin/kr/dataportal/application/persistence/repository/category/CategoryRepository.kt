package kr.dataportal.application.persistence.repository.category

import kr.dataportal.application.definition.CategoryResponse
import kr.dataportal.application.persistence.entity.category.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
    fun findAllBy(): List<CategoryResponse>
}
