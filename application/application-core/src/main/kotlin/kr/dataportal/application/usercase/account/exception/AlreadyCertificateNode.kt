package kr.dataportal.application.usercase.account.exception

/**
 * @author Heli
 * Created on 2022. 09. 26
 */
class AlreadyCertificateNode(
    challengeId: Long,
    journeyNodeId: Long
) : RuntimeException("이미 인증한 노드입니다. [challengeId=$challengeId, journeyNodeId=$journeyNodeId]")
