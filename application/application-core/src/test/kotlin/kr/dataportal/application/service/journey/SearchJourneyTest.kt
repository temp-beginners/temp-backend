package kr.dataportal.application.service.journey

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import kr.dataportal.application.definition.JourneyNodeResponse
import kr.dataportal.application.definition.JourneyResponse
import kr.dataportal.application.persistence.config.jpa.requiredId
import kr.dataportal.application.persistence.entity.category.Category
import kr.dataportal.application.persistence.entity.journey.Journey
import kr.dataportal.application.persistence.entity.journey.JourneyNode
import kr.dataportal.application.persistence.repository.journey.JourneyNodeRepository
import kr.dataportal.application.persistence.repository.journey.JourneyRepository
import kr.dataportal.application.usercase.journey.CreateJourneyNodeUseCase
import kr.dataportal.application.usercase.journey.CreateJourneyUseCase
import kr.dataportal.application.usercase.journey.SearchJourneyNodeUseCase
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

    @MockK
    private lateinit var journeyNodeRepository: JourneyNodeRepository

    private lateinit var sutSearch: SearchJourneyUseCase

    private lateinit var sutCreate: CreateJourneyUseCase

    private lateinit var sutSearchNode: SearchJourneyNodeUseCase

    private lateinit var sutCreateNode: CreateJourneyNodeUseCase


    @BeforeEach
    fun init() {
        val journeySlot = slot<Journey>()

        every {
            journeyRepository.save(capture(journeySlot))
        } answers {
            journeySlot.captured.apply {
                id = 1L
                category.id = 1L

            }
        }
        val journeyNodeSlot = slot<JourneyNode>()

        every {
            journeyNodeRepository.save(capture(journeyNodeSlot))
        } answers {
            journeyNodeSlot.captured.apply {
                id = 1L
                category.id = 1L
                journey.id = 1L

            }
        }


        every {
            journeyRepository.findByCategoryId(1L)
        } answers {
            listOf(
                JourneyResponse(
                    id = 1L,
                    title = "이달의 추천",
                    categoryId = 1L
                )
            )
        }
        every {
            journeyNodeRepository.findByCategoryIdAndJourneyId(1L, 1L)
        } answers {
            listOf(
                JourneyNodeResponse(
                    id = 1L,
                    title = "이달의 추천",
                    categoryId = 1L,
                    journeyId = 1L,
                    nodeNum = 1L,
                    level = 1L,

                    )
            )
        }
        sutCreate = CreateJourney(
            journeyRepository = journeyRepository,
        )
        sutSearch = SearchJourney(
            journeyRepository = journeyRepository
        )
        sutCreateNode = CreateJourneyNode(
            journeyNodeRepository = journeyNodeRepository
        )
        sutSearchNode = SearchJourneyNode(
            journeyNodeRepository = journeyNodeRepository
        )
    }

    @Test
    fun `여정를 조회할 수 있다`() {
        // given
        val category = Category.create(title = "등산")
        val command = CreateJourneyUseCase.Command(category, "이달의 추천")
        sutCreate.command(command = command)

        // when
        var (result) = sutSearch.command(SearchJourneyUseCase.Command((category.requiredId)))  //

        // then
        expectThat(result) {
            get { journeyList } isEqualTo listOf(
                JourneyResponse(
                    id = 1L,
                    title = "이달의 추천",
                    categoryId = 1L,
                )
            )
        }
    }

    @Test
    fun `여정의 노드를 조회할 수 있다`() {
        // given
        val category = Category.create(title = "등산")
        var journey = Journey.create(category, "이달의 추천")
        val command = CreateJourneyNodeUseCase.Command(category, journey, "산1", 1L, 1L)
        sutCreateNode.command(command = command)

        // when
        var (result) = sutSearchNode.command(SearchJourneyNodeUseCase.Command(category.requiredId, journey.requiredId))

        // then
        expectThat(result) {
            get { journeyNodeList } isEqualTo listOf(
                JourneyNodeResponse(
                    id = 1L,
                    title = "이달의 추천",
                    categoryId = 1L,
                    journeyId = 1L,
                    nodeNum = 1L,
                    level = 1L,
                )
            )
        }
    }
}