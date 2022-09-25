package kr.dataportal.application.persistence.entity.account

import kr.dataportal.application.enums.AuthenticationType
import kr.dataportal.application.persistence.config.jpa.BaseEntity
import javax.persistence.*

/**
 * @author Heli
 * Created on 2022. 09. 06
 */
@Table(name = "user_authentication")
@Entity
class AccountAuthentication(
    @ManyToOne
    @JoinColumn(name = "user_account_id")
    val userAccount: UserAccount,

    @Enumerated(EnumType.STRING)
    var authType: AuthenticationType,

    var authText: String
) : BaseEntity() {

    companion object {

        fun authPlain(
            userAccount: UserAccount,
            password: String
        ) = AccountAuthentication(
            userAccount = userAccount,
            authType = AuthenticationType.PLAIN,
            authText = password
        )
    }
}
