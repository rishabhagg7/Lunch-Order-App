package com.example.lunchtray.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.lunchtray.R
import com.example.lunchtray.model.MenuItem
import java.util.Locale

@Composable
fun BaseMenuScreen(
    options: List<MenuItem>,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    onSelectionChanged: (MenuItem) -> Unit = {},
) {
    var selectedItemName by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
        ) {
            options.forEach {item->
                MenuRowItem(
                    item = item,
                    selectedItemName = selectedItemName,
                    onClick = {
                        onSelectionChanged(item)
                        selectedItemName = item.name
                    },
                    modifier = Modifier
                        .selectable(
                            selected = item.name == selectedItemName,
                            onClick = {
                                onSelectionChanged(item)
                                selectedItemName = item.name
                            }
                        )
                )
            }
        }
        MenuScreenButtonGroup(
            selectedItemName = selectedItemName,
            onNextButtonClicked = onNextButtonClicked,
            onCancelButtonClicked = onCancelButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
    }
}

@Composable
fun MenuRowItem(
    item: MenuItem,
    selectedItemName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(vertical = dimensionResource(id = R.dimen.padding_small)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedItemName == item.name,
            onClick = onClick
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = item.getFormattedPrice(),
                style = MaterialTheme.typography.bodyMedium
            )
            HorizontalDivider(
                thickness = dimensionResource(id = R.dimen.divider_thickness)
            )
        }
    }
}

@Composable
fun MenuScreenButtonGroup(
    selectedItemName: String,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedButton(
            onClick = onCancelButtonClicked,
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.cancel).uppercase(Locale.getDefault()),
                textAlign = TextAlign.Center,
            )
        }
        Button(
            onClick = onNextButtonClicked,
            modifier = Modifier
                .weight(1f),
            enabled = selectedItemName.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.onBackground
            )
        ) {
            Text(
                text = stringResource(id = R.string.next).uppercase(Locale.getDefault()),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BaseMenuPreview() {
    BaseMenuScreen(
        options = listOf(
            MenuItem.EntreeItem(
                name = "Option 1 Name",
                description = "This is a test description of option 1 name which is one of the best dish in the world",
                price = 0.0
            ),
            MenuItem.EntreeItem(
                name = "Option 2 Name",
                description = "This is a test description of option 2 name which is one of the best dish in the world",
                price = 0.0
            ),
            MenuItem.EntreeItem(
                name = "Option 3 Name",
                description = "This is a test description of option 3 name which is one of the best dish in the world",
                price = 0.0
            ),
            MenuItem.EntreeItem(
                name = "Option 4 Name",
                description = "This is a test description of option 4 name which is one of the best dish in the world",
                price = 0.0
            )
        ),
        onNextButtonClicked = {},
        onCancelButtonClicked = {},
        onSelectionChanged = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
    )
}