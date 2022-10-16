package kr.dataportal.application.persistence.repository.journey

import kr.dataportal.application.definition.JourneyResponse
import kr.dataportal.application.persistence.entity.journey.Journey
import org.springframework.data.jpa.repository.JpaRepository

interface JourneyRepository : JpaRepository<Journey, Long> {

    fun findAllBy(): List<JourneyResponse>

    fun findByCategoryId(categoryId: Long): List<JourneyResponse>
}