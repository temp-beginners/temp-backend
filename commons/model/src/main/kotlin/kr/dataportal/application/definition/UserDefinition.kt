package kr.dataportal.application.definition

import kr.dataportal.application.enums.AuthenticationType

/**
 * @author Heli
 * Created on 2022. 09. 07
 */
data class UserDefinition(
    val id: Long,
    val email: String,
    val authType: AuthenticationType
)
