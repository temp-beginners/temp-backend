package kr.dataportal.application.persistence.repository.certification

import kr.dataportal.application.persistence.entity.certification.ChallengeCertification
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Heli
 * Created on 2022. 09. 26
 */
interface ChallengeCertificationRepository : JpaRepository<ChallengeCertification, Long> {

    fun findByChallengeIdAndJourneyNodeId(challengeId: Long, journeyNodeId: Long): ChallengeCertification?
}
