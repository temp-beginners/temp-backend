package kr.dataportal.application.service.journey

import kr.dataportal.application.definition.JourneyDefinition
import kr.dataportal.application.persistence.entity.journey.Journey
import kr.dataportal.application.persistence.repository.journey.JourneyRepository
import kr.dataportal.application.usercase.journey.CreateJourneyUseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CreateJourney(
    private val journeyRepository: JourneyRepository,
    //private val categoryRepository : CategoryRepository
) : CreateJourneyUseCase {

    @Transactional
    override fun command(command: CreateJourneyUseCase.Command): CreateJourneyUseCase.Result {
        val (category, title, description) = command

        /*val categoryObj = Category.create(title = category.title, description = null)
            .let(categoryRepository::save)*/

        val journeyObj = Journey.create(category = category, title = title, description = description)
            .let(journeyRepository::save)

        return CreateJourneyUseCase.Result(
            journey = JourneyDefinition(
                id = journeyObj.id!!,
                title = journeyObj.title,
                description = journeyObj.description
            )
        )
    }
}