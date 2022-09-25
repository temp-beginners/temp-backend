package kr.dataportal.application.service.user

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import kr.dataportal.application.enums.AuthenticationType
import kr.dataportal.application.persistence.entity.account.AccountAuthentication
import kr.dataportal.application.persistence.entity.account.UserAccount
import kr.dataportal.application.persistence.repository.account.AccountAuthenticationRepository
import kr.dataportal.application.persistence.repository.account.UserAccountRepository
import kr.dataportal.application.port.user.CreatePlainAuthUserUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import strikt.api.expectThat
import strikt.assertions.isEqualTo

/**
 * @Author Heli
 */
@ExtendWith(MockKExtension::class)
internal class CreatePlainAuthUserTest {

    @MockK
    private lateinit var userAccountRepository: UserAccountRepository

    @MockK
    private lateinit var accountAuthenticationRepository: AccountAuthenticationRepository

    private lateinit var sut: CreatePlainAuthUser

    @BeforeEach
    fun init() {
        val userAccountSlot = slot<UserAccount>()
        every {
            userAccountRepository.save(capture(userAccountSlot))
        } answers {
            userAccountSlot.captured.apply {
                id = 1L
            }
        }

        val accountAuthenticationSlot = slot<AccountAuthentication>()
        every {
            accountAuthenticationRepository.save(capture(accountAuthenticationSlot))
        } answers {
            accountAuthenticationSlot.captured.apply {
                id = 1L
            }
        }

        sut = CreatePlainAuthUser(
            userAccountRepository = userAccountRepository,
            accountAuthenticationRepository = accountAuthenticationRepository
        )
    }

    @Test
    fun `email 과 password 로 PlainAuthUser 를 생성할 수 있다`() {
        // given
        val command = CreatePlainAuthUserUseCase.Command(
            email = "heli@example.com",
            password = "password"
        )
        // when
        val (result) = sut.command(command = command)

        // then
        expectThat(result) {
            get { id } isEqualTo 1L
            get { email } isEqualTo "heli@example.com"
            get { authType } isEqualTo AuthenticationType.PLAIN
        }
    }
}
