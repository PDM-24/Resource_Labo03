package com.alexisflop.labo03.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisflop.labo03.data.objectList
import com.alexisflop.labo03.model.ObjectClass
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {


    fun addData(myObjectClass: ObjectClass) {
        viewModelScope.launch {
            objectList.value.add(myObjectClass)
        }
    }


    fun getData(): MutableList<ObjectClass> {
        return objectList.value
    }
}