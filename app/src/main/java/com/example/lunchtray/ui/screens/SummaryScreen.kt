package com.example.lunchtray.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.lunchtray.R
import com.example.lunchtray.data.OrderUiState
import com.example.lunchtray.model.MenuItem
import com.example.lunchtray.ui.viewmodels.formatPrice
import java.util.Locale

@Composable
fun SummaryScreen(
    orderUiState: OrderUiState,
    onSubmitButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
    ) {
        Text(
            text = stringResource(id = R.string.order_summary),
            fontWeight = FontWeight.Bold
        )
        ItemSummary(
            item = orderUiState.entree,
            modifier = Modifier
                .fillMaxWidth()
        )
        ItemSummary(
            item = orderUiState.sideDish,
            modifier = Modifier
                .fillMaxWidth()
        )
        ItemSummary(
            item = orderUiState.accompaniment,
            modifier = Modifier
                .fillMaxWidth()
        )
        HorizontalDivider(
            thickness = dimensionResource(id = R.dimen.divider_thickness)
        )
        OrderSubCost(
            resourceId = R.string.subtotal,
            price = orderUiState.itemTotalPrice.formatPrice(),
            modifier = Modifier.align(Alignment.End)
        )
        OrderSubCost(
            resourceId = R.string.tax,
            price = orderUiState.orderTax.formatPrice(),
            modifier = Modifier.align(Alignment.End)
        )
        Text(
            text = stringResource(id = R.string.total,orderUiState.orderTotalPrice.formatPrice()),
            modifier = Modifier.align(Alignment.End),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
        MenuScreenButtonGroup(
            onSubmitButtonClicked = onSubmitButtonClicked,
            onCancelButtonClicked = onCancelButtonClicked
        )
    }
}

@Composable
fun OrderSubCost(
    @StringRes resourceId: Int,
    price: String,
    modifier: Modifier
) {
    Text(
        text = stringResource(id = resourceId,price),
        modifier = modifier
    )
}

@Composable
fun ItemSummary(
    item: MenuItem?,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item?.name ?: "")
        Text(text = item?.getFormattedPrice() ?: "")
    }
}

@Composable
fun MenuScreenButtonGroup(
    onSubmitButtonClicked: () -> Unit,
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
            onClick = onSubmitButtonClicked,
            modifier = Modifier
                .weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            )
        ) {
            Text(
                text = stringResource(id = R.string.submit_button).uppercase(Locale.getDefault()),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SummaryPreview() {
    SummaryScreen(
        orderUiState = OrderUiState(
            entree = MenuItem.EntreeItem(
                name = "Option 1 Name",
                description = "This is a test description of option 1 name which is one of the best dish in the world",
                price = 0.0
            ),
            sideDish = MenuItem.SideDishItem(
                name = "Option 2 Name",
                description = "This is a test description of option 2 name which is one of the best dish in the world",
                price = 0.0
            ),
            accompaniment = MenuItem.AccompanimentItem(
                name = "Option 3 Name",
                description = "This is a test description of option 1 name which is one of the best dish in the world",
                price = 0.0
            )
        ),
        onSubmitButtonClicked = {},
        onCancelButtonClicked = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
    )
}