package com.example.application.seed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.application.seed.benefit.Benefit1
import com.example.application.seed.benefit.Benefit2
import com.example.application.seed.benefit.Benefit3
import co.seedglobal.design.component.SeedBottomSheetValue
import co.seedglobal.design.component.SeedText
import co.seedglobal.design.component.rememberSeedBottomSheetState
import co.seedglobal.design.component.util.CatchScrollUp
import co.seedglobal.design.component.util.DecorateBackground
import co.seedglobal.design.core.SeedTheme
import kotlinx.coroutines.launch

@Composable
fun PayScreen(
    onScrollChange: (isScrollUp: Boolean) -> Unit = {},
) {
    val bottomSheetState = rememberSeedBottomSheetState(initialValue = SeedBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    scrollState.CatchScrollUp(onScrollChange = onScrollChange)

    DecorateBackground(color = Color(0xFFFAFAFA)) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Benefit1()
            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = SeedTheme.colorScheme.container.outline)
            Spacer(modifier = Modifier.height(28.dp))
            Benefit2(onClick = {
                scope.launch {
                    bottomSheetState.show()
                }
            })
            Spacer(modifier = Modifier.height(36.dp))
            SeedText(text = "돈이되는 지원사업", style = SeedTheme.typoScheme.headline.smallBold)
            Spacer(modifier = Modifier.height(20.dp))
            Benefit3() {}
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
