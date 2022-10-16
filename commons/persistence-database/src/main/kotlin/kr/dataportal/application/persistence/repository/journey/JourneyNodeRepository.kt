package kr.dataportal.application.persistence.repository.journey

import kr.dataportal.application.definition.JourneyNodeResponse
import kr.dataportal.application.persistence.entity.journey.JourneyNode
import org.springframework.data.jpa.repository.JpaRepository

interface JourneyNodeRepository : JpaRepository<JourneyNode, Long> {

    fun findByCategoryIdAndJourneyId(categoryId: Long, journeyId: Long): List<JourneyNodeResponse>
}