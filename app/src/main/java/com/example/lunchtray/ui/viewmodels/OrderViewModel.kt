package com.example.lunchtray.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.lunchtray.data.OrderUiState
import com.example.lunchtray.model.MenuItem
import com.example.lunchtray.model.MenuItem.AccompanimentItem
import com.example.lunchtray.model.MenuItem.EntreeItem
import com.example.lunchtray.model.MenuItem.SideDishItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class OrderViewModel: ViewModel() {
    private val taxRate = 0.08
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState = _uiState.asStateFlow()

    fun updateEntree(
        selectedEntree: EntreeItem
    ) {
        val previousEntree = _uiState.value.entree
        updateItem(selectedEntree,previousEntree)
    }

    fun updateSideDish(
        selectedSideDish: SideDishItem
    ) {
        val previousSideDish = _uiState.value.sideDish
        updateItem(selectedSideDish,previousSideDish)
    }

    fun updateAccompaniment(
        selectedAccompaniment:AccompanimentItem
    ) {
        val previousAccompaniment = _uiState.value.accompaniment
        updateItem(selectedAccompaniment,previousAccompaniment)
    }

    private fun updateItem(
        newItem: MenuItem,
        previousItem: MenuItem?
    ){
        _uiState.update { currentState->
            val previousItemPrice = previousItem?.price ?: 0.0
            // subtract previous item price in case an item of this category was already added.
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
            // recalculate tax
            val tax = itemTotalPrice * taxRate
            currentState.copy(
                entree = if(newItem is EntreeItem) newItem else currentState.entree,
                sideDish = if(newItem is SideDishItem) newItem else currentState.sideDish,
                accompaniment = if(newItem is AccompanimentItem) newItem else currentState.accompaniment,
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax
            )
        }
    }
}

fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}