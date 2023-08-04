package es.fjmarlop.tareas.addTareas.ui

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.fjmarlop.tareas.addTareas.ui.model.TareaModel
import kotlinx.coroutines.launch
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
        val index = _tarea.indexOf(tareaModel)
        _tarea[index] = _tarea[index].let {
            it.copy(seleccionado = !it.seleccionado)
        }
    }

    fun onItemRemove(tareaModel: TareaModel, context: Context) {
        val tarea = _tarea.find { it.id == tareaModel.id }
        _tarea.remove(tarea)
        viewModelScope.launch {
            Toast.makeText(context, "Tarea borrada correctamente", Toast.LENGTH_SHORT).show()
        }
    }
}