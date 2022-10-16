package kr.dataportal.application.service.journey

import kr.dataportal.application.definition.JourneyDefinition
import kr.dataportal.application.persistence.repository.journey.JourneyRepository
import kr.dataportal.application.usercase.journey.SearchJourneyUseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SearchJourney(
    private val journeyRepository: JourneyRepository
) : SearchJourneyUseCase {

    @Transactional
    override fun command(command: SearchJourneyUseCase.Command): SearchJourneyUseCase.Result {
        val (categoryId) = command

        return SearchJourneyUseCase.Result(
            searchJourney = JourneyDefinition(
                journeyList = when (categoryId) {
                    null -> journeyRepository.findAllBy()
                    else -> journeyRepository.findByCategoryId(categoryId)
                }
            )
        )
    }
}