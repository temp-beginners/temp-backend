package kr.dataportal.application.service.journey

import kr.dataportal.application.definition.JourneyDefinition
import kr.dataportal.application.definition.JourneyResponse
import kr.dataportal.application.persistence.config.jpa.requiredId
import kr.dataportal.application.persistence.entity.journey.Journey
import kr.dataportal.application.persistence.repository.journey.JourneyRepository
import kr.dataportal.application.usercase.journey.CreateJourneyUseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CreateJourney(
    private val journeyRepository: JourneyRepository,
) : CreateJourneyUseCase {

    @Transactional
    override fun command(command: CreateJourneyUseCase.Command): CreateJourneyUseCase.Result {
        val (category, title, description) = command

        val journey = Journey.create(category = category, title = title, description = description)
            .let(journeyRepository::save)

        return CreateJourneyUseCase.Result(
            createJourney = JourneyDefinition(
                journeyList = listOf(
                    JourneyResponse(
                        id = journey.requiredId,
                        title = journey.title,
                        description = journey.description,
                        categoryId = journey.category.requiredId,
                    )
                ),
            )
        )
    }
}