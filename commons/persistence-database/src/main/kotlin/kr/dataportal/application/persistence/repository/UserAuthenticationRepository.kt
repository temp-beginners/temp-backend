package kr.dataportal.application.persistence.repository

import kr.dataportal.application.persistence.entity.UserAuthentication
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Heli
 * Created on 2022. 09. 07
 */
interface UserAuthenticationRepository : JpaRepository<UserAuthentication, Long>
