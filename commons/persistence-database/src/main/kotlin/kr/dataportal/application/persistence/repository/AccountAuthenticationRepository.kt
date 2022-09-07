package kr.dataportal.application.persistence.repository

import kr.dataportal.application.persistence.entity.AccountAuthentication
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Heli
 * Created on 2022. 09. 07
 */
interface AccountAuthenticationRepository : JpaRepository<AccountAuthentication, Long>
