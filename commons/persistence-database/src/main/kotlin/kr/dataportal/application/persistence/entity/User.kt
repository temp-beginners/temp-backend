package kr.dataportal.application.persistence.entity

import kr.dataportal.application.persistence.config.jpa.BaseEntity
import javax.persistence.Entity
import javax.persistence.Table

/**
 * @author Heli
 * Created on 2022. 09. 06
 */
@Table(name = "user")
@Entity
class User private constructor(
    var email: String
) : BaseEntity() {

    companion object {

        fun create(
            email: String
        ) = User(
            email = email
        )
    }
}
