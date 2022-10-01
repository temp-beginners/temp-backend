package kr.dataportal.application.service.journey

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import kr.dataportal.application.enums.category
import kr.dataportal.application.persistence.entity.category.Category
import kr.dataportal.application.persistence.entity.journey.Journey
import kr.dataportal.application.persistence.repository.journey.JourneyRepository
import kr.dataportal.application.usercase.journey.CreateJourneyUseCase
import kr.dataportal.application.usercase.journey.SearchJourneyUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ExtendWith(MockKExtension::class)
internal class SearchJourneyTest {

    @MockK
    private lateinit var journeyRepository: JourneyRepository
    /*@MockK
    private lateinit var categoryRepository: CategoryRepository
*/
    private lateinit var sutSearch: SearchJourneyUseCase

    private lateinit var sutCreate: CreateJourneyUseCase

    @BeforeEach
    fun init() {
        /*val categorySlot = slot<Category>() //캡쳐 준비

        every {
            categoryRepository.save(capture(categorySlot))
        } answers {
            categorySlot.captured.apply {
                id = 1L

            }
        }*/
        val journeySlot = slot<Journey>() //캡쳐 준비

        every {
            journeyRepository.save(capture(journeySlot))
        } answers {
            journeySlot.captured.apply {
                id = 1L

            }
        }
        /*every {
            categoryRepository.findAllBy()
        } returns null*/
        /*every {
            journeyRepository.findByTitle(categoryTitle = category.라이딩)
        } returns null*/

        sutCreate = CreateJourney(
            journeyRepository = journeyRepository,
            //categoryRepository = categoryRepository
        )
        sutSearch = SearchJourney(
            journeyRepository = journeyRepository
        )
    }
    @Test
    fun `journey를 생성할 수 있다`() {
        // given
        val command = CreateJourneyUseCase.Command(
            category = Category.create(category.라이딩, null),
            title = "이달의 추천",
            description = null
        )
        // when
        val (result) = sutCreate.command(command = command)

        // then
        expectThat(result) {
            get { id } isEqualTo 1L
            get { title } isEqualTo "이달의 추천"
            get { description } isEqualTo null
        }
    }
}