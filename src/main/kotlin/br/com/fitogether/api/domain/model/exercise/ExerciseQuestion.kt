package br.com.fitogether.api.domain.model.exercise

data class ExerciseQuestion(
    val id: Long?,
    val type: String? = "",
    val description: String? = null,
    val options: List<ExerciseQuestionOption?>
)
