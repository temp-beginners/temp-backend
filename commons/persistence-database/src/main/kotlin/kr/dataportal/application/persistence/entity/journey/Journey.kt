package kr.dataportal.application.persistence.entity.journey

import kr.dataportal.application.persistence.config.jpa.BaseEntity
import kr.dataportal.application.persistence.entity.category.Category
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "journey")
@Entity
class Journey (
    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category,

    val title: String,

    var description : String?,
) : BaseEntity(){

    companion object {
        fun create(
            category: Category,
            title: String,
            description: String?
        ) = Journey(
            category = category,
            title = title,
            description = description
        )
    }
}