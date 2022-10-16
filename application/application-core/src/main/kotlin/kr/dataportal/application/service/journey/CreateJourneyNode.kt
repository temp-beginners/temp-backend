package kr.dataportal.application.service.journey

import kr.dataportal.application.definition.JourneyNodeDefinition
import kr.dataportal.application.definition.JourneyNodeResponse
import kr.dataportal.application.persistence.config.jpa.requiredId
import kr.dataportal.application.persistence.entity.journey.JourneyNode
import kr.dataportal.application.persistence.repository.journey.JourneyNodeRepository
import kr.dataportal.application.usercase.journey.CreateJourneyNodeUseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CreateJourneyNode(
    private val journeyNodeRepository: JourneyNodeRepository,
) : CreateJourneyNodeUseCase {

    @Transactional
    override fun command(command: CreateJourneyNodeUseCase.Command): CreateJourneyNodeUseCase.Result {
        val (category, journey, title, level, nodeNum, description) = command

        val journeyNode = JourneyNode.create(
            category = category,
            journey = journey,
            title = title,
            level = level,
            nodeNum = nodeNum,
            description = description
        ).let(journeyNodeRepository::save)

        return CreateJourneyNodeUseCase.Result(
            createJourneyNode = JourneyNodeDefinition(
                journeyNodeList = listOf(
                    JourneyNodeResponse(
                        id = journeyNode.requiredId,
                        title = journeyNode.title,
                        description = journeyNode.description,
                        level = journeyNode.level,
                        nodeNum = journeyNode.nodeNum,
                        journeyId = journeyNode.journey.requiredId,
                        categoryId = journeyNode.category.requiredId,
                    )
                ),
            )
        )
    }
}