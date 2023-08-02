package es.fjmarlop.tareas.addTareas.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.fjmarlop.tareas.addTareas.ui.model.TareaModel
import javax.inject.Inject

class PantallaTareasViewModel @Inject constructor() : ViewModel() {


    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _tarea = mutableStateListOf<TareaModel>()
    val tarea: List<TareaModel> = _tarea

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onCrearTarea(tarea: String) {
        _tarea.add(TareaModel(tarea = tarea))
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(tareaModel: TareaModel) {
        TODO("Not yet implemented")
    }
}