package com.example.fetchandroidtest.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchandroidtest.data.models.Item
import com.example.fetchandroidtest.data.respository.ItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {

    private val TAG = this::class.simpleName

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    init {
        fetchItemList()
    }

    private fun fetchItemList() {
        viewModelScope.launch {
            try {
                val response = ItemRepository.getItems()

                _items.value = response
                    .filter { !it.name.isNullOrBlank() }
                    .sortedWith { a, b ->
                        val listIdCompare = a.listId.compareTo(b.listId)
                        if (listIdCompare != 0) {
                            listIdCompare
                        } else {
                            val nameA = extractIntFromName(a.name!!)
                            val nameB = extractIntFromName(b.name!!)
                            nameA.compareTo(nameB)
                        }
                    }
                Log.d(TAG, "fetchItemList: " + _items.value)

            } catch (e: Exception) {
                Log.e(TAG, "fetchItemList: " + e.message)
            }
        }
    }

    private fun extractIntFromName(name: String): Int {
        val prefix = "Item "
        return name.removePrefix(prefix).toInt()
    }
}
