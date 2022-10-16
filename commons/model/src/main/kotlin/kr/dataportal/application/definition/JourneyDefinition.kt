package kr.dataportal.application.definition

data class JourneyDefinition(
    val journeyList: List<JourneyResponse>,
)

data class JourneyResponse(
    val id: Long,
    val title: String,
    val description: String? = "",
    val categoryId: Long,
)