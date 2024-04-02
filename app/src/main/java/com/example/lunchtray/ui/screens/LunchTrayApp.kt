package com.example.lunchtray.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.R
import com.example.lunchtray.data.DataSource
import com.example.lunchtray.ui.viewmodels.OrderViewModel

@Composable
fun LunchTrayApp(
    orderViewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEmpty by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(
        backStackEmpty?.destination?.route ?:Screens.Start.name
    )
    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding->
        val orderUiState by orderViewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Screens.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screens.Start.name){
                StartOrderScreen(
                    onNextButtonClicked = {
                        navController.navigate(Screens.Entree.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(route = Screens.Entree.name){
                EntreeMenuScreen(
                    entrees = DataSource.entreeMenuItems,
                    onNextButtonClicked = {
                        navController.navigate(Screens.SideDish.name)
                    },
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(navController) },
                    onSelectionChanged = {selectedEntree->
                         orderViewModel.updateEntree(selectedEntree)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .verticalScroll(rememberScrollState())
                )
            }
            composable(route = Screens.SideDish.name){
                SideDishScreen(
                    sideDishes = DataSource.sideDishMenuItems,
                    onNextButtonClicked = {
                        navController.navigate(Screens.Accompaniment.name)
                    },
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(navController) },
                    onSelectionChanged = {selectedSideDish->
                        orderViewModel.updateSideDish(selectedSideDish)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .verticalScroll(rememberScrollState())
                )
            }
            composable(route = Screens.Accompaniment.name){
                AccompanimentMenuScreen(
                    accompaniments = DataSource.accompanimentMenuItems,
                    onNextButtonClicked = {
                        navController.navigate(Screens.Summary.name)
                    },
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(navController) },
                    onSelectionChanged = {selectedAccompaniment->
                        orderViewModel.updateAccompaniment(selectedAccompaniment)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .verticalScroll(rememberScrollState())
                )
            }
            composable(route = Screens.Summary.name){
                SummaryScreen(
                    orderUiState = orderUiState,
                    onSubmitButtonClicked = {
                        navController.navigate(Screens.Start.name)
                    },
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(navController) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .verticalScroll(rememberScrollState())
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    navController: NavHostController
) {
    navController.popBackStack(Screens.Start.name, inclusive = false)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    currentScreen: Screens,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = false,
    onNavigateUp: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = currentScreen.title))},
        modifier = modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
