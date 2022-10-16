package kr.dataportal.application.definition

data class CategoryDefinition(
    val categoryList: List<CategoryResponse>,
)

data class CategoryResponse(
    val id: Long,
    val title: String,
    val description: String? = "",
)