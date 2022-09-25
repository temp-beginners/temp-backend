package kr.dataportal.application.persistence.repository.journey

import org.springframework.stereotype.Repository

/**
 * @author Heli
 * Created on 2022. 09. 26
 */
@Repository
class FakeJourneyChallengeRepository : JourneyChallengeRepository {

    override fun findByUserIdAndJourneyId(userId: Long, journeyId: Long): JourneyChallenge {
        return JourneyChallenge(userId = userId, journeyId = journeyId)
    }
}

data class JourneyChallenge(
    val id: Long = 1,
    val userId: Long,
    val journeyId: Long
)
