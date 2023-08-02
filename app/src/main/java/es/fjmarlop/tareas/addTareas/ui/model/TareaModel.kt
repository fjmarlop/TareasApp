package es.fjmarlop.tareas.addTareas.ui.model

data class TareaModel(
    val id: Long = System.currentTimeMillis(),
    val tarea: String,
    var seleccionado: Boolean = false
)
