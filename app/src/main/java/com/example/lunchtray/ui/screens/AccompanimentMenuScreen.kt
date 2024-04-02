package com.example.lunchtray.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lunchtray.model.MenuItem
import com.example.lunchtray.model.MenuItem.AccompanimentItem


@Composable
fun AccompanimentMenuScreen(
    accompaniments: List<AccompanimentItem>,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    onSelectionChanged: (AccompanimentItem) -> Unit,
    modifier: Modifier
) {
    BaseMenuScreen(
        options = accompaniments,
        onNextButtonClicked = onNextButtonClicked,
        onCancelButtonClicked = onCancelButtonClicked,
        onSelectionChanged = onSelectionChanged as (MenuItem) -> Unit,
        modifier = modifier
    )
}