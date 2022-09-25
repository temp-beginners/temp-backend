package kr.dataportal.application.service.certification

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kr.dataportal.application.persistence.entity.certification.ChallengeCertification
import kr.dataportal.application.persistence.entity.certification.ChallengeCertificationAdditionalInfo
import kr.dataportal.application.persistence.repository.certification.ChallengeCertificationAdditionalInfoRepository
import kr.dataportal.application.persistence.repository.certification.ChallengeCertificationRepository
import kr.dataportal.application.persistence.repository.journey.FakeJourneyChallengeRepository
import kr.dataportal.application.persistence.repository.journey.JourneyChallengeRepository
import kr.dataportal.application.usercase.account.exception.AlreadyCertificateNode
import kr.dataportal.application.usercase.certification.CertificateChallengeUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import strikt.assertions.withFirst

/**
 * @author Heli
 * Created on 2022. 09. 26
 */
@ExtendWith(MockKExtension::class)
internal class CertificateChallengeTest {

    @MockK
    private lateinit var certificationRepository: ChallengeCertificationRepository

    @MockK
    private lateinit var additionalInfoRepository: ChallengeCertificationAdditionalInfoRepository

    private val journeyChallengeRepository: JourneyChallengeRepository = FakeJourneyChallengeRepository()

    private lateinit var sut: CertificateChallengeUseCase

    @BeforeEach
    fun init() {
        val certificationSlot = slot<ChallengeCertification>()
        every {
            certificationRepository.save(capture(certificationSlot))
        } answers {
            certificationSlot.captured.apply {
                id = 1L
            }
        }

        val additionalInfoSlot = slot<ChallengeCertificationAdditionalInfo>()
        every {
            additionalInfoRepository.save(capture(additionalInfoSlot))
        } answers {
            additionalInfoSlot.captured.apply {
                id = 1L
            }
        }

        every {
            certificationRepository.findByChallengeIdAndJourneyNodeId(challengeId = 1L, journeyNodeId = 1L)
        } returns null

        sut = CertificateChallenge(
            certificationRepository = certificationRepository,
            additionalInfoRepository = additionalInfoRepository,
            journeyChallengeRepository = journeyChallengeRepository
        )
    }

    @Test
    fun `이미 인증한 노드에 대해 인증 요청 시 예외가 발생한다`() {
        val command = command()

        every {
            certificationRepository.findByChallengeIdAndJourneyNodeId(challengeId = 1L, journeyNodeId = 1L)
        } returns mockk()

        expectThrows<AlreadyCertificateNode> {
            sut.command(command = command)
        }
    }

    @Test
    fun `사용자 ID 와 여정 ID로 여정 도전을 찾는다`() {
        val command = command()

        sut.command(command = command)

        verify {
            certificationRepository.findByChallengeIdAndJourneyNodeId(challengeId = 1L, journeyNodeId = 1L)
        }
    }

    @Test
    fun `여정을 성공적으로 인증한다`() {
        val command = command()

        val (result) = sut.command(command = command)

//        verify { journeyChallengeRepository.findByUserIdAndJourneyId(1L, 1L) }
        verify { certificationRepository.findByChallengeIdAndJourneyNodeId(1L, command.journeyNodeId) }
        verify { certificationRepository.save(any()) }
        verify { additionalInfoRepository.save(any()) }

        expectThat(result) {
            get { id } isEqualTo 1L
            get { challengeId } isEqualTo 1L
            get { journeyNodeId } isEqualTo 1L
            get { attachmentImageUrls }
                .hasSize(1)
                .withFirst { isEqualTo("https://example.com/img.png") }
        }
    }

    private fun command() = CertificateChallengeUseCase.Command(
        journeyNodeId = 1L, attachmentImageUrl = "https://example.com/img.png"
    )
}
