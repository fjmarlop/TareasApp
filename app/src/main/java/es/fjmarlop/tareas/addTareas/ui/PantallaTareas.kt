package es.fjmarlop.tareas.addTareas.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import es.fjmarlop.tareas.addTareas.ui.model.TareaModel

@Composable
fun PantallaTareas(pantallaTareasViewModel: PantallaTareasViewModel) {

    val showDialog: Boolean by pantallaTareasViewModel.showDialog.observeAsState(false)

    Box(modifier = Modifier.fillMaxSize()) {

        AddTareaDialog(
            show = showDialog,
            onDismiss = { pantallaTareasViewModel.onDialogClose() },
            onTareaAnadida = { pantallaTareasViewModel.onCrearTarea(it) })
        FabDialog(Modifier.align(Alignment.BottomEnd), pantallaTareasViewModel)
        TareasList(pantallaTareasViewModel)
    }
}

@Composable
fun TareasList(pantallaTareasViewModel: PantallaTareasViewModel) {
    val misTareas: List<TareaModel> = pantallaTareasViewModel.tarea

    LazyColumn {
        items(misTareas, key = { it.id }) { tarea ->
            ItemTarea(tareaModel = tarea, pantallaTareasViewModel = pantallaTareasViewModel)
        }
    }
}

@Composable
fun ItemTarea(tareaModel: TareaModel, pantallaTareasViewModel: PantallaTareasViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), elevation = CardDefaults.cardElevation()
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = tareaModel.tarea, modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            )
            Checkbox(
                checked = tareaModel.seleccionado,
                onCheckedChange = { pantallaTareasViewModel.onCheckBoxSelected(tareaModel) })
        }
    }
}

@Composable
fun FabDialog(modifier: Modifier, pantallaTareasViewModel: PantallaTareasViewModel) {
    FloatingActionButton(
        onClick = { pantallaTareasViewModel.onShowDialogClick() },
        modifier = modifier.padding(16.dp)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "add")
    }
}

@Composable
fun AddTareaDialog(show: Boolean, onDismiss: () -> Unit, onTareaAnadida: (String) -> Unit) {

    var tarea by remember {
        mutableStateOf("")
    }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Añade tu tarea",
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = tarea,
                    onValueChange = { tarea = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    onTareaAnadida(tarea)
                    tarea = ""
                }, Modifier.fillMaxWidth()) {
                    Text(text = "Añadir tarea")
                }
            }
        }
    }
}