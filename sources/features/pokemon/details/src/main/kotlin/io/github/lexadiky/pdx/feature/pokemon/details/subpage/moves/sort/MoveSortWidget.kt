@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves.sort

import androidx.compose.material3.AssistChip
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSort
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSortDirection
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSortDirectionBuilder
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSortType
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSortTypeBuilder
import io.github.lexadiky.pdx.ui.uikit.resources.render

@Composable
internal fun MoveSortWidget(onUpdated: (MoveSort) -> Unit) {
    MoveSortWidgetImpl(onUpdated, di.viewModel())
}

@Composable
private fun MoveSortWidgetImpl(onUpdated: (MoveSort) -> Unit, vm: MoveSortWidgetViewModel) {
    LaunchedEffect(vm.state.sort) {
        onUpdated(vm.state.sort)
    }

    ElevatedFilterChip(
        selected = vm.state.isSet,
        onClick = { vm.openBuild() },
        label = { Text(text = vm.state.title.render()) }

    )

    DropdownMenu(
        expanded = vm.state.isMenuOpen,
        onDismissRequest = { vm.dismissBuild() },
    ) {
        when (val builder = vm.state.builder) {
            is MoveSortDirectionBuilder -> MoveSortWidgetDirectionDropdown(
                builder = builder,
                onDirectionClicked = { vm.selectDirection(it) }
            )
            is MoveSortTypeBuilder -> MoveSortWidgetTypeDropdown(
                builder = builder,
                onTypeClicked = { vm.selectType(it) }
            )
            null -> Unit
        }
    }
}

@Composable
private fun MoveSortWidgetTypeDropdown(
    builder: MoveSortTypeBuilder,
    onTypeClicked: (MoveSortType) -> Unit,
) {
    builder.availableTypes.forEach { type ->
        DropdownMenuItem(
            text = { Text(text = type.title.render()) },
            onClick = { onTypeClicked(type) }
        )
    }
}

@Composable
private fun MoveSortWidgetDirectionDropdown(
    builder: MoveSortDirectionBuilder,
    onDirectionClicked: (MoveSortDirection) -> Unit,
) {
    builder.availableDirection.forEach { direction ->
        DropdownMenuItem(
            text = { Text(text = direction.title.render()) },
            onClick = { onDirectionClicked(direction) }
        )
    }
}
