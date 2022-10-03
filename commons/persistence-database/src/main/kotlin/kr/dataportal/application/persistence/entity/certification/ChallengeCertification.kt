package kr.dataportal.application.persistence.entity.certification

import kr.dataportal.application.persistence.config.jpa.BaseEntity
import javax.persistence.Entity
import javax.persistence.Table

/**
 * @author Heli
 * Created on 2022. 09. 26
 */
@Table(name = "challenge_certification")
@Entity
class ChallengeCertification(
    // TODO: Challenge 연관관계 매핑 필요
    val challengeId: Long,

    // TODO: JourneyNode 연관관계 매핑 필요
    val journeyNodeId: Long
) : BaseEntity()
