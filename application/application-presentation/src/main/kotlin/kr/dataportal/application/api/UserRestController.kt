package kr.dataportal.application.api

import kr.dataportal.application.port.user.CreatePlainAuthUserUseCase
import org.springframework.web.bind.annotation.RestController

/**
 * @author Heli
 * Created on 2022. 09. 07
 */
@RestController
class UserRestController(
    private val createPlainAuthUserUseCase: CreatePlainAuthUserUseCase
) {
}
