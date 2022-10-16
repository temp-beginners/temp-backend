package kr.dataportal.application.persistence.entity.journey

import kr.dataportal.application.persistence.config.jpa.BaseEntity
import kr.dataportal.application.persistence.entity.category.Category
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "journey_node")
@Entity
class JourneyNode(
    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category,

    @ManyToOne
    @JoinColumn(name = "journey_id")
    val journey: Journey,

    val title: String,

    var description: String? = "",

    val level: Long,

    val nodeNum: Long,
) : BaseEntity() {
    companion object {
        fun create(
            category: Category,
            journey: Journey,
            title: String,
            description: String? = "",
            level: Long,
            nodeNum: Long,
        ) = JourneyNode(
            category = category,
            journey = journey,
            title = title,
            description = description,
            level = level,
            nodeNum = nodeNum,
        )
    }
}