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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.application.R
import com.example.application.ui.BottomNavigation
import com.example.application.ui.theme.GFSampleTheme
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.component.GFButton
import com.greenlabsfin.design.component.GFHeight
import com.greenlabsfin.design.component.GfBottomSheetState
import com.greenlabsfin.design.component.GfBottomSheetTopBarLayout
import com.greenlabsfin.design.component.GfBottomSheetValue
import com.greenlabsfin.design.component.GfIcon
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.component.GfTextButton
import com.greenlabsfin.design.component.GfTopBarDefaults
import com.greenlabsfin.design.component.control.GFCheckbox
import com.greenlabsfin.design.component.rememberGfBottomSheetState
import com.greenlabsfin.design.component.util.DecorateBackground
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.gray30
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomSheetScreen(
    onNavigationClick: () -> Unit = {},
    navController: NavController? = null,
) {
    DecorateBackground(
        GfTheme.colorScheme.container.neutralTertiary
    ) {
        val bottomSheetState =
            rememberGfBottomSheetState(initialValue = GfBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()
        var isFixed by remember { mutableStateOf(false) }

        GfBottomSheetTopBarLayout(
            topBarPadding = GfTopBarDefaults.paddingOf(horizontal = 20.dp),
            title = stringResource(id = R.string.app_name),
            titleAlignment = Alignment.CenterStart,
            onNavigationClick = onNavigationClick,
            sheetState = bottomSheetState,
            bottomBar = { BottomNavigation(navController = navController) },
            isFixed = isFixed,
            sheetContent = {
//            PlccBannerContent(
//                bottomSheetState = bottomSheetState,
//                scope = scope
//            )
                SelectMonthContentLayout()
            }
        ) {
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
                            GfText(
                                text = "Marketing Title",
                                style = GfTheme.typoScheme.body.smallBold,
                                color = GfTheme.colorScheme.contents.onPrimary
                            )
                            GfText(
                                text = "Marketing description, description",
                                style = GfTheme.typoScheme.caption.xSmallRegular,
                                color = GfTheme.colorScheme.contents.onPrimary
                            )
                        }
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
        GfText(
            modifier = Modifier
                .height(58.dp)
                .padding(horizontal = 20.dp),
            text = "월 선택하기",
            style = GfTheme.typoScheme.body.largeBold
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
                    GfText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        text = "${targetYear}년 ${targetMonth}월",
                        style = GfTheme.typoScheme.body.mediumRegular
                    )
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
            text = "Terms of usage",
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
//                        modifier = Modifier.weight(1f, true),
                        checked = checked,
                        onCheckedChange = { checked = it },
                        text = "Term of something",
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
//            modifier = Modifier.fillMaxWidth(),
            height = GFHeight.Large,
            colors = GFButton.Style.containerPrimary,
            text = "동의하고 시작하기"
        ) {
            scope.launch { bottomSheetState.hide() }
        }
    }
}

@Composable
fun PlccBannerContent(
    bottomSheetState: GfBottomSheetState = rememberGfBottomSheetState(initialValue = GfBottomSheetValue.Hidden),
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    Column(
        modifier = Modifier.wrapContentHeight()
    ) {
        Surface(
            color = GfTheme.colorScheme.contents.neutralPrimary
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
                    GfText(text = "Marketing\nThis is marketing",
                        style = GfTheme.typoScheme.body.xLargeBold,
                        color = GfTheme.colorScheme.contents.onPrimary)
                    Spacer(modifier = Modifier.height(23.dp))
                    GfText(text = "Marketing description, description is good",
                        style = GfTheme.typoScheme.caption.xSmallRegular,
                        color = GfTheme.colorScheme.contents.onPrimary)
                }
            }
        }
        Row(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 34.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GfTextButton(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .weight(.1f, false),
                text = "다음에",
                style = GfTheme.typoScheme.body.mediumMedium,
                color = GfTheme.colorScheme.contents.neutralSecondary,
            ) {
                scope.launch { bottomSheetState.hide() }
            }

            GFButton(
                modifier = Modifier.weight(.2f, true),
                height = GFHeight.Large,
                colors = GFButton.Style.tintNeutral,
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
    GFSampleTheme {
        Surface(color = GfTheme.colorScheme.container.background) {
//            BottomSheetScreen()
            PlccBannerContent()
        }
    }
}
