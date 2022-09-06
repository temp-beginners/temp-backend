package kr.dataportal.application.persistence.entity

import kr.dataportal.application.persistence.config.jpa.BaseEntity
import javax.persistence.*

/**
 * @author Heli
 * Created on 2022. 09. 06
 */
@Table(name = "user_authentication")
@Entity
class UserAuthentication(
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Enumerated(EnumType.STRING)
    var authType: AuthType,

    var authText: String
) : BaseEntity() {

    companion object {

        fun authPlain(
            user: User,
            password: String
        ) = UserAuthentication(
            user = user,
            authType = AuthType.PLAIN,
            authText = password
        )
    }

    enum class AuthType {
        PLAIN, GOOGLE, KAKAO
    }
}
