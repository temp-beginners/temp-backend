package kr.dataportal.application.persistence.repository.journey

import kr.dataportal.application.persistence.entity.category.Category
import kr.dataportal.application.persistence.entity.journey.Journey
import org.springframework.data.jpa.repository.JpaRepository

interface JourneyRepository : JpaRepository<Journey, Long> {

    fun findByCategoryOrNull(category: Category?): List<Journey>
}