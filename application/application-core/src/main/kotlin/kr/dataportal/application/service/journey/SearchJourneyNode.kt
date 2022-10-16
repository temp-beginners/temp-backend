package kr.dataportal.application.service.journey

import kr.dataportal.application.definition.JourneyNodeDefinition
import kr.dataportal.application.persistence.repository.journey.JourneyNodeRepository
import kr.dataportal.application.usercase.journey.SearchJourneyNodeUseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SearchJourneyNode(
    private val journeyNodeRepository: JourneyNodeRepository
) : SearchJourneyNodeUseCase {

    @Transactional
    override fun command(command: SearchJourneyNodeUseCase.Command): SearchJourneyNodeUseCase.Result {
        val (categoryId, journeyId) = command

        return SearchJourneyNodeUseCase.Result(
            journeyNode = JourneyNodeDefinition(
                journeyNodeList = journeyNodeRepository.findByCategoryIdAndJourneyId(categoryId, journeyId)
            )
        )
    }

}