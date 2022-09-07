package kr.dataportal.application.port.user

import kr.dataportal.application.definition.UserDefinition

/**
 * @author Heli
 * Created on 2022. 09. 07
 */
interface CreatePlainAuthUserUseCase {

    fun command(command: Command): Result

    data class Command(
        val email: String,
        val password: String
    )

    data class Result(
        val user: UserDefinition
    )
}
