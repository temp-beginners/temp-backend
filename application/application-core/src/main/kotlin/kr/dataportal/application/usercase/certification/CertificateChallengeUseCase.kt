package kr.dataportal.application.usercase.certification

import kr.dataportal.application.definition.ChallengeCertificationDefinition

/**
 * @author Heli
 * Created on 2022. 09. 26
 */
interface CertificateChallengeUseCase {

    fun command(command: Command): Result

    data class Command(
        val journeyNodeId: Long,
        val attachmentImageUrl: String
    )

    data class Result(
        val certificationChallenge: ChallengeCertificationDefinition
    )
}
