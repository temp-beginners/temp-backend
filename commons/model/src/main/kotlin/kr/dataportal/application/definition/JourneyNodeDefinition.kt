package kr.dataportal.application.definition

data class JourneyNodeDefinition(
    val journeyNodeList: List<JourneyNodeResponse>,
)


data class JourneyNodeResponse(
    val id: Long,
    val title: String,
    val description: String? = "",
    val categoryId: Long,
    val journeyId: Long,
    val nodeNum: Long,
    val level: Long,

    )