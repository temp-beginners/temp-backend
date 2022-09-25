package kr.dataportal.application.persistence.entity.certification

import kr.dataportal.application.enums.Status
import kr.dataportal.application.persistence.config.jpa.BaseEntity
import javax.persistence.*

/**
 * @author Heli
 * Created on 2022. 09. 26
 */
@Table(name = "challenge_certification_additional_info")
@Entity
class ChallengeCertificationAdditionalInfo(

    @ManyToOne
    @JoinColumn(name = "challenge_certification_id")
    val challengeCertification: ChallengeCertification,

    val attachmentImageUrl: String
) : BaseEntity() {

    @Enumerated(EnumType.STRING)
    var status: Status = Status.AVAILABLE
}
