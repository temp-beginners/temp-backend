package kr.dataportal.application.service.user

import kr.dataportal.application.definition.UserDefinition
import kr.dataportal.application.persistence.config.jpa.requiredId
import kr.dataportal.application.persistence.entity.account.AccountAuthentication
import kr.dataportal.application.persistence.entity.account.UserAccount
import kr.dataportal.application.persistence.repository.account.AccountAuthenticationRepository
import kr.dataportal.application.persistence.repository.account.UserAccountRepository
import kr.dataportal.application.port.user.CreatePlainAuthUserUseCase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Heli
 * Created on 2022. 09. 07
 */
@Component
class CreatePlainAuthUser(
    private val userAccountRepository: UserAccountRepository,
    private val accountAuthenticationRepository: AccountAuthenticationRepository
) : CreatePlainAuthUserUseCase {

    @Transactional
    override fun command(command: CreatePlainAuthUserUseCase.Command): CreatePlainAuthUserUseCase.Result {
        val (email, password) = command
        val userAccount = UserAccount.create(email = email)
            .let(userAccountRepository::save)

        val authentication = AccountAuthentication.authPlain(
            userAccount = userAccount,
            password = password
        ).let(accountAuthenticationRepository::save)

        return CreatePlainAuthUserUseCase.Result(
            user = UserDefinition(
                id = userAccount.requiredId,
                email = userAccount.email,
                authType = authentication.authType
            )
        )
    }
}
