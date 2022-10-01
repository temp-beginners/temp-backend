package kr.dataportal.application.definition

import kr.dataportal.application.enums.category

data class categoryDefinition(
    val id: Long,
    val title: category,
    val description : String?
)

