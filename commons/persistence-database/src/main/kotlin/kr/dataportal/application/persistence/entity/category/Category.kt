package kr.dataportal.application.persistence.entity.category

import kr.dataportal.application.persistence.config.jpa.BaseEntity
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "category")
@Entity
class Category(
    var title: String,

    var description: String? = "",

    ) : BaseEntity() {

    companion object {
        fun create(
            title: String,
            description: String? = ""
        ) = Category(
            title = title,
            description = description,
        )
    }
}
