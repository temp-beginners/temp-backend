package kr.dataportal.application.persistence.config.jpa

import kr.dataportal.application.notNull
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author Heli
 * Created on 2022. 09. 06
 */
@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(updatable = false)
    lateinit var createdAt: LocalDateTime

    lateinit var modifiedAt: LocalDateTime

    @PrePersist
    fun prePersist() {
        val now = LocalDateTime.now()
        createdAt = now
        modifiedAt = now
    }

    @PreUpdate
    fun preUpdate() {
        modifiedAt = LocalDateTime.now()
    }
}

val BaseEntity.requiredId: Long
    get() = id.notNull { "Entity(${javaClass.simpleName}) id is null" }
