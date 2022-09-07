package kr.dataportal.application.service.user

import kr.dataportal.application.definition.UserDefinition
import kr.dataportal.application.persistence.config.jpa.requiredId
import kr.dataportal.application.persistence.entity.AccountAuthentication
import kr.dataportal.application.persistence.entity.UserAccount
import kr.dataportal.application.persistence.repository.AccountAuthenticationRepository
import kr.dataportal.application.persistence.repository.UserAccountRepository
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
        val userAccount = UserAccount.create(email = email).let {
            userAccountRepository.save(it)
        }

        val authentication = AccountAuthentication.authPlain(
            userAccount = userAccount,
            password = password
        ).let {
            accountAuthenticationRepository.save(it)
        }

        return CreatePlainAuthUserUseCase.Result(
            user = UserDefinition(
                id = userAccount.requiredId,
                email = userAccount.email,
                authType = authentication.authType
            )
        )
    }
}
