package kr.dataportal.application.persistence.repository.journey

/**
 * @author Heli
 * Created on 2022. 09. 26
 */
interface JourneyChallengeRepository {

    fun findByUserIdAndJourneyId(userId: Long, journeyId: Long): JourneyChallenge
}
