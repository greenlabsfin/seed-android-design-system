package com.example.application.ui.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.application.ui.theme.GFSampleTheme
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.component.GFButton
import com.greenlabsfin.design.component.GFHeight
import com.greenlabsfin.design.component.GfBottomSheetLayout
import com.greenlabsfin.design.component.GfBottomSheetState
import com.greenlabsfin.design.component.GfBottomSheetValue
import com.greenlabsfin.design.component.GfIcon
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.component.control.GFCheckbox
import com.greenlabsfin.design.component.control.GFSwitch
import com.greenlabsfin.design.component.rememberGfBottomSheetState
import com.greenlabsfin.design.core.GfTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomSheetScreen() {
    val bottomSheetState =
        rememberGfBottomSheetState(initialValue = GfBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var isFixed by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val maxHeight = rememberUpdatedState(
        if (isFixed) configuration.screenHeightDp.div(2).dp
        else configuration.screenHeightDp.times(.7f).dp
    )


    Modifier.fillMaxHeight()
    GfBottomSheetLayout(
        sheetState = bottomSheetState,
        isFixed = isFixed,
        sheetContent = {
            SheetContentLayout(
                maxHeight = maxHeight.value,
                bottomSheetState = bottomSheetState,
                scope = scope
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            GFSwitch(
                checked = isFixed,
                onCheckedChange = { isFixed = isFixed.not() },
                text = "Fixed"
            )
            GFButton(
                modifier = Modifier.fillMaxWidth(),
                height = GFHeight.Large,
                colors = GFButton.Style.containerPrimary,
                text = "Show"
            ) {
                scope.launch {
                    bottomSheetState.show()
                }
            }
        }
    }
}

@Composable
private fun SheetContentLayout(
    maxHeight: Dp,
    bottomSheetState: GfBottomSheetState = rememberGfBottomSheetState(initialValue = GfBottomSheetValue.Hidden),
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    Column(
        modifier = Modifier
            .heightIn(min = 0.dp, max = maxHeight)
            .padding(bottom = 34.dp, start = 20.dp, end = 20.dp)
    ) {
        GfText(
            modifier = Modifier
                .height(60.dp)
                .padding(top = 4.dp),
            text = "약관 동의",
            style = GfTheme.typoScheme.body.mediumBold
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f, true)
                .padding(bottom = 16.dp)
        ) {
            items(40) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 58.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    var checked by remember { mutableStateOf(false) }
                    GFCheckbox(
                        modifier = Modifier.weight(1f, true),
                        checked = checked,
                        onCheckedChange = { checked = it },
                        text = "출금이체 약관동의",
                        textStyle = GfTheme.typoScheme.body.mediumRegular,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    GfIcon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "",
                        tint = GfTheme.colorScheme.contents.neutralTertiary,
                    )
                }
            }
        }
        GFButton(
            modifier = Modifier.fillMaxWidth(),
            height = GFHeight.Large,
            colors = GFButton.Style.containerPrimary,
            text = "동의하고 시작하기"
        ) {
            scope.launch { bottomSheetState.hide() }
        }
    }
}

@ThemedPreview
@Composable
fun BottomSheetScreenPreview() {
    GFSampleTheme {
        Surface(color = GfTheme.colorScheme.container.background) {
            BottomSheetScreen()
        }
    }
}