package kr.dataportal.application.service.category

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import kr.dataportal.application.enums.category
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
        /*every {
            categoryRepository.findAllBy()
        } returns null*/
        every {
            categoryRepository.findByTitle(categoryTitle = category.라이딩)
        } returns null

        sutCreate = CreateCategory(
            categoryRepository = categoryRepository
        )
        sutSearch = SearchCategory(
            categoryRepository = categoryRepository
        )
    }
    @Test
    fun `category를 생성할 수 있다`() {
        // given
        val command = CreateCategoryUseCase.Command(
            title = category.등산,
            description = null
        )
        // when
        val (result) = sutCreate.command(command = command)

        // then
        expectThat(result) {
            get { id } isEqualTo 1L
            get { title } isEqualTo category.등산
            get { description } isEqualTo null
        }
    }
    @Test
    fun `category를 조회할 수 있다`() {
        // given
        val categoryObj = Category(category.라이딩,"11")
        categoryRepository.save(categoryObj)

        // when
        every {
            categoryRepository.findByTitle(categoryTitle = category.라이딩)
        } returns mockk()

        //var results = categoryRepository.findByTitle(categoryTitle = category.라이딩)
        //assertThat(results).hasSize(1)
        //println("$results")

        /*val categorySlot = slot<Category>()
        val categoryObj = Category(category.라이딩,"11")
        categoryRepository.save(categoryObj)


        //when
        verify {
            val (result) = sutSearch.command()
        }*/

       // then
       /*expectThat(results) {
           println( get { category })
           //get { results. } isEqualTo category.등산
          // get { categoryDescription } isEqualTo ""
       }*/
       /* val categoryList = listOf(
            Category(category.등산, null),
            Category(category.라이딩, null),

        )

        every { categoryRepository.findAllBy() } returns categoryList

        verify(exactly = 1) { sutSearch.command() }*/



    }
}