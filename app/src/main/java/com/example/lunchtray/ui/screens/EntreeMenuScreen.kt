package com.example.lunchtray.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lunchtray.model.MenuItem
import com.example.lunchtray.model.MenuItem.EntreeItem

@Composable
fun EntreeMenuScreen(
    entrees: List<EntreeItem>,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    onSelectionChanged: (EntreeItem) -> Unit,
    modifier: Modifier
) {
    BaseMenuScreen(
        options = entrees,
        onNextButtonClicked = onNextButtonClicked,
        onCancelButtonClicked = onCancelButtonClicked,
        onSelectionChanged = onSelectionChanged as (MenuItem) -> Unit,
        modifier = modifier
    )
}