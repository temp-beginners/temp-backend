package kr.dataportal.application.service.category

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import kr.dataportal.application.definition.CategoryResponse
import kr.dataportal.application.persistence.entity.category.Category
import kr.dataportal.application.persistence.repository.category.CategoryRepository
import kr.dataportal.application.usercase.category.CreateCategoryUseCase
import kr.dataportal.application.usercase.category.SearchCategoryUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ExtendWith(MockKExtension::class)
internal class SearchCategoryTest {

    @MockK
    private lateinit var categoryRepository: CategoryRepository

    private lateinit var sutSearch: SearchCategoryUseCase

    private lateinit var sutCreate: CreateCategoryUseCase

    @BeforeEach
    fun init() {
        val categorySlot = slot<Category>() //캡쳐 준비

        every {
            categoryRepository.save(capture(categorySlot))
        } answers {
            categorySlot.captured.apply {
                id = 1L

            }
        }
        every {
            categoryRepository.findAllBy()
        } answers {
            listOf(
                CategoryResponse(
                    id = 1L,
                    title = "등산",
                )
            )
        }
        sutCreate = CreateCategory(
            categoryRepository = categoryRepository
        )
        sutSearch = SearchCategory(
            categoryRepository = categoryRepository
        )
    }

    @Test
    fun `category를 조회 할 수 있다`() {
        // given
        val command = CreateCategoryUseCase.Command(
            title = "등산",
        )
        sutCreate.command(command = command)
        
        // when
        var (result) = sutSearch.command()

        // then
        expectThat(result) {
            get { categoryList } isEqualTo listOf(
                CategoryResponse(
                    id = 1L,
                    title = "등산"
                )
            )
        }
    }

}