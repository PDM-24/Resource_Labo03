package com.alexisflop.labo03.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexisflop.labo03.data.objectList
import com.alexisflop.labo03.model.ObjectClass
import com.alexisflop.labo03.viewmodel.DataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ObjectListComponent(modifier: Modifier = Modifier) {
    val listShared: MutableState<MutableList<ObjectClass>> =
        remember { mutableStateOf(mutableListOf()) }
    val loadingState: MutableState<Boolean> = remember { mutableStateOf(false) }
    val viewModel = DataViewModel()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            GlobalScope.launch(Dispatchers.IO) {
                loadingState.value = true
                listShared.value = viewModel.getData()
                Thread.sleep(2000)
                loadingState.value = false
            }
        }) {
            Text(text = "Get OBJECTS from database")
        }
        if (loadingState.value) {
            if (listShared.value.isEmpty())
                Text(text = "No data")
            else
                CircularProgressIndicator()
        } else {

            LazyColumn(
                modifier = Modifier
                    .weight(9f)
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            ) {

                itemsIndexed(listShared.value) { index, item ->
                    Text(text = item.propertyOne)
                }
            }
        }
    }

}

@Preview(showSystemUi = false)
@Composable
private fun ObjectListComponentPreview() {
    ObjectListComponent()
}