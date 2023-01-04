package com.example.application.ui.bottomsheet

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.application.R
import com.example.application.ui.theme.SeedSampleTheme
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.component.SeedBottomSheetState
import com.greenlabsfin.design.component.SeedBottomSheetValue
import com.greenlabsfin.design.component.SeedButton
import com.greenlabsfin.design.component.SeedButtonDefaults
import com.greenlabsfin.design.component.SeedCheckbox
import com.greenlabsfin.design.component.SeedIcon
import com.greenlabsfin.design.component.SeedText
import com.greenlabsfin.design.component.SeedTextButton
import com.greenlabsfin.design.component.rememberSeedBottomSheetState
import com.greenlabsfin.design.component.util.DecorateBackground
import com.greenlabsfin.design.core.SeedTheme
import com.greenlabsfin.design.core.color.gray30
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomSheetScreen(
    onShowBottomSheet: (content: @Composable () -> Unit, isFixed: Boolean) -> Unit,
) {
    DecorateBackground(
        SeedTheme.colorScheme.container.neutralTertiary
    ) {
        val bottomSheetState =
            rememberSeedBottomSheetState(initialValue = SeedBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF2B2C32)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            onShowBottomSheet(
                                { SelectMonthContentLayout() },
                                false
                            )
                            scope.launch {
                                bottomSheetState.show()
                            }
                        }
                ) {
                    Image(
                        modifier = Modifier.size(50.dp),
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "launcher"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(

                    ) {
                        SeedText(
                            text = "Marketing Title",
                            style = SeedTheme.typoScheme.body.smallBold,
                            color = SeedTheme.colorScheme.contents.onPrimary
                        )
                        SeedText(
                            text = "Marketing description, description",
                            style = SeedTheme.typoScheme.caption.xSmallRegular,
                            color = SeedTheme.colorScheme.contents.onPrimary
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun SelectMonthContentLayout() {
    val density = LocalDensity.current
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(width = 40.dp, height = 4.dp), onDraw = {
                drawRoundRect(
                    color = gray30,
                    size = Size(
                        width = with(density) { 40.dp.toPx() },
                        height = with(density) { 4.dp.toPx() }
                    ),
                    cornerRadius = CornerRadius(with(density) { 2.dp.toPx() })
                )
            })
        }
        SeedText(
            modifier = Modifier
                .height(58.dp)
                .padding(horizontal = 20.dp),
            text = "월 선택하기",
            style = SeedTheme.typoScheme.body.largeBold
        )
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            val year = 2022
            val month = 11
            for (i in 0 until 33) {
                var targetMonth = month.minus(i)
                var targetYear = year
                while (targetMonth < 1) {
                    targetYear--
                    targetMonth += 12
                }
                item {
                    SeedText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        text = "${targetYear}년 ${targetMonth}월",
                        style = SeedTheme.typoScheme.body.mediumRegular
                    )
                }
            }
        }
    }
}

@Composable
private fun SheetContentLayout(
    maxHeight: Dp,
    bottomSheetState: SeedBottomSheetState = rememberSeedBottomSheetState(initialValue = SeedBottomSheetValue.Hidden),
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    Column(
        modifier = Modifier
            .heightIn(min = 0.dp, max = maxHeight)
            .padding(bottom = 34.dp, start = 20.dp, end = 20.dp)
    ) {
        SeedText(
            modifier = Modifier
                .height(60.dp)
                .padding(top = 4.dp),
            text = "Terms of usage",
            style = SeedTheme.typoScheme.body.mediumBold
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
                    SeedCheckbox(
//                        modifier = Modifier.weight(1f, true),
                        checked = checked,
                        onCheckedChange = { checked = it },
                        text = "Term of something",
                        textStyle = SeedTheme.typoScheme.body.mediumRegular,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    SeedIcon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "",
                        tint = SeedTheme.colorScheme.contents.neutralTertiary,
                    )
                }
            }
        }
        SeedButton(
//            modifier = Modifier.fillMaxWidth(),
            height = SeedButton.Height.Large,
            colors = SeedButtonDefaults.Colors.containerPrimary(),
            text = "동의하고 시작하기"
        ) {
            scope.launch { bottomSheetState.hide() }
        }
    }
}

@Composable
fun PlccBannerContent(
    bottomSheetState: SeedBottomSheetState = rememberSeedBottomSheetState(initialValue = SeedBottomSheetValue.Hidden),
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    Column(
        modifier = Modifier.wrapContentHeight()
    ) {
        Surface(
            color = SeedTheme.colorScheme.contents.neutralPrimary
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
            ) {
                Column(modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 37.dp, start = 20.dp, end = 20.dp)
                ) {
                    SeedText(text = "Marketing\nThis is marketing",
                        style = SeedTheme.typoScheme.body.xLargeBold,
                        color = SeedTheme.colorScheme.contents.onPrimary)
                    Spacer(modifier = Modifier.height(23.dp))
                    SeedText(text = "Marketing description, description is good",
                        style = SeedTheme.typoScheme.caption.xSmallRegular,
                        color = SeedTheme.colorScheme.contents.onPrimary)
                }
            }
        }
        Row(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 34.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SeedTextButton(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .weight(.1f, false),
                text = "다음에",
                style = SeedTheme.typoScheme.body.mediumMedium,
                color = SeedTheme.colorScheme.contents.neutralSecondary,
            ) {
                scope.launch { bottomSheetState.hide() }
            }

            SeedButton(
                modifier = Modifier.weight(.2f, true),
                height = SeedButton.Height.Large,
                colors = SeedButtonDefaults.Colors.tintNeutral(),
                text = "알아보기",
            ) {
                scope.launch { bottomSheetState.hide() }
            }
        }
    }
}

@ThemedPreview
@Composable
fun BottomSheetScreenPreview() {
    SeedSampleTheme {
        Surface(color = SeedTheme.colorScheme.container.background) {
//            BottomSheetScreen()
            PlccBannerContent()
        }
    }
}
