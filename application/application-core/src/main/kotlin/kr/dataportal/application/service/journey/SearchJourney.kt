package kr.dataportal.application.service.journey

import kr.dataportal.application.persistence.repository.journey.JourneyRepository
import kr.dataportal.application.usercase.journey.SearchJourneyUseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SearchJourney (
    private val journeyRepository: JourneyRepository
    ) : SearchJourneyUseCase{

    @Transactional
    override fun command(command: SearchJourneyUseCase.Command): List<SearchJourneyUseCase.Result> {
        val (category) = command
        return journeyRepository.findByCategoryOrNull(category).map { journey -> SearchJourneyUseCase.of(journey) }
    }
}