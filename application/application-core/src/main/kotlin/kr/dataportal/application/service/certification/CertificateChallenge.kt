package kr.dataportal.application.service.certification

import kr.dataportal.application.definition.ChallengeCertificationDefinition
import kr.dataportal.application.persistence.config.jpa.requiredId
import kr.dataportal.application.persistence.entity.certification.ChallengeCertification
import kr.dataportal.application.persistence.entity.certification.ChallengeCertificationAdditionalInfo
import kr.dataportal.application.persistence.repository.certification.ChallengeCertificationAdditionalInfoRepository
import kr.dataportal.application.persistence.repository.certification.ChallengeCertificationRepository
import kr.dataportal.application.persistence.repository.journey.JourneyChallengeRepository
import kr.dataportal.application.usercase.account.exception.AlreadyCertificateNode
import kr.dataportal.application.usercase.certification.CertificateChallengeUseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Heli
 * Created on 2022. 09. 26
 */
@Component
class CertificateChallenge(
    private val certificationRepository: ChallengeCertificationRepository,
    private val additionalInfoRepository: ChallengeCertificationAdditionalInfoRepository,
    private val journeyChallengeRepository: JourneyChallengeRepository
) : CertificateChallengeUseCase {

    companion object {
        private const val TEMPORARY_USER_ID = 1L
        private const val TEMPORARY_JOURNEY_ID = 1L
    }

    @Transactional
    override fun command(command: CertificateChallengeUseCase.Command): CertificateChallengeUseCase.Result {
        val (journeyNodeId, attachmentImageUrl) = command

        val journeyChallenge = journeyChallengeRepository.findByUserIdAndJourneyId(
            userId = TEMPORARY_USER_ID,
            journeyId = TEMPORARY_JOURNEY_ID
        )

        val challengeCertification = when (certificationRepository.findByChallengeIdAndJourneyNodeId(
            challengeId = journeyChallenge.id,
            journeyNodeId = journeyNodeId
        )) {
            null -> ChallengeCertification(challengeId = journeyChallenge.id, journeyNodeId = journeyNodeId)
                .let(certificationRepository::save)
            else -> throw AlreadyCertificateNode(challengeId = journeyChallenge.id, journeyNodeId = journeyNodeId)
        }

        val challengeCertificationAdditionalInfo = ChallengeCertificationAdditionalInfo(
            challengeCertification = challengeCertification,
            attachmentImageUrl = attachmentImageUrl
        ).let(additionalInfoRepository::save)

        return CertificateChallengeUseCase.Result(
            certificationChallenge = ChallengeCertificationDefinition(
                id = challengeCertification.requiredId,
                challengeId = challengeCertification.challengeId,
                journeyNodeId = challengeCertification.journeyNodeId,
                attachmentImageUrls = listOf(challengeCertificationAdditionalInfo.attachmentImageUrl)
            )
        )
    }
}
