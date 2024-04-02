package com.example.lunchtray.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lunchtray.model.MenuItem
import com.example.lunchtray.model.MenuItem.SideDishItem

@Composable
fun SideDishScreen(
    sideDishes: List<SideDishItem>,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    onSelectionChanged: (SideDishItem) -> Unit,
    modifier: Modifier
) {
    BaseMenuScreen(
        options = sideDishes,
        onNextButtonClicked = onNextButtonClicked,
        onCancelButtonClicked = onCancelButtonClicked,
        onSelectionChanged = onSelectionChanged as (MenuItem) -> Unit,
        modifier = modifier
    )
}