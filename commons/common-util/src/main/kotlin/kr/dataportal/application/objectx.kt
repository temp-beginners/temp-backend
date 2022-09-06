package kr.dataportal.application

/**
 * @author Heli
 * Created on 2022. 09. 06
 */
fun <T : Any> T?.notNull(): T = requireNotNull(this)
inline fun <T : Any> T?.notNull(lazyMessage: () -> Any): T = requireNotNull(this, lazyMessage)
