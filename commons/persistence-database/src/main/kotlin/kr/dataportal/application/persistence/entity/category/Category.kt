package kr.dataportal.application.persistence.entity.category

import kr.dataportal.application.enums.category
import kr.dataportal.application.persistence.config.jpa.BaseEntity
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Table(name = "category")
@Entity
class Category constructor(
    @Enumerated(EnumType.STRING)
    var title: category,

    var description: String?,

    ) : BaseEntity() {

    companion object {
        fun create(
            title: category,
            description: String?
        ) = Category(
            title = title,
            description = description
        )


    }
}
