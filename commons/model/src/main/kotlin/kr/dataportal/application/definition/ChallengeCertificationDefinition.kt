package kr.dataportal.application.definition

/**
 * @author Heli
 * Created on 2022. 09. 26
 */
data class ChallengeCertificationDefinition(
    val id: Long,
    val challengeId: Long,
    val journeyNodeId: Long,
    val attachmentImageUrls: List<String>
)
