package com.example.application.seed.benefit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.greenlabsfin.design.component.GFButton
import com.greenlabsfin.design.component.GFHeight
import com.greenlabsfin.design.component.GfBottomSheetState
import com.greenlabsfin.design.component.GfBottomSheetValue
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.component.GfTextButton
import com.greenlabsfin.design.component.rememberGfBottomSheetState
import com.greenlabsfin.design.component.util.CatchScrollUp
import com.greenlabsfin.design.component.util.DecorateBackground
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.green30
import com.greenlabsfin.design.core.color.red50
import com.greenlabsfin.design.core.color.white
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun BenefitScreen(
    navController: NavController,
    onScrollChange: (isUp: Boolean) -> Unit = {},
    onShowBottomSheet: (bottomSheetContent: @Composable () -> Unit, isFixed: Boolean) -> Unit,
) {
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
            Divider(color = GfTheme.colorScheme.container.outline)
            Spacer(modifier = Modifier.height(28.dp))
            Benefit2(onClick = {
                onShowBottomSheet({ SeedBenefitContent() }, true)
            })
            Spacer(modifier = Modifier.height(36.dp))
            GfText(text = "돈이되는 지원사업", style = GfTheme.typoScheme.headline.smallBold)
            Spacer(modifier = Modifier.height(20.dp))
            Benefit3() {
                navController.navigate(MoreBenefit.route)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Benefit3 {

            }
        }
    }
}

@Composable
fun Benefit1() {
    Row(
        modifier = Modifier.defaultMinSize(minHeight = 88.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
                .size(28.dp)
                .background(color = Color(0xFF4A89F9))
        )
        GfText(
            modifier = Modifier.weight(1f),
            text = "나에게 맞는 \n보조금, 지원사업은?", style = GfTheme.typoScheme.body.mediumBold)
        GFButton(
            text = "검색",
            height = GFHeight.XSmall,
            colors = GFButton.Style.tintNeutral,
            onClick = {

            }
        )
    }
}

@Composable
fun Benefit3(
    onClick: () -> Unit,
) {
    Surface(
        color = white,
        shape = RoundedCornerShape(16.dp),
        elevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)) {
            GfText(text = "2023년 \n기본형 공익직불금", style = GfTheme.typoScheme.headline.smallBold)
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                GfText(
                    modifier = Modifier.weight(1f),
                    text = "전체 농가 대상",
                    style = GfTheme.typoScheme.body.smallBold,
                    color = GfTheme.colorScheme.contents.neutralSecondary,
                )
                GfText(
                    text = "D-20",
                    style = GfTheme.typoScheme.body.smallBold,
                    color = red50,
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            GFButton(
                modifier = Modifier.fillMaxWidth(),
                text = "공고보기",
                colors = GFButton.Style.tintPrimary,
                height = GFHeight.Small,
                onClick = onClick
            )
            Spacer(modifier = Modifier.height(20.dp))
            Divider(color = GfTheme.colorScheme.container.outline)
            Spacer(modifier = Modifier.height(26.dp))
            GfText(text = "관련 교육", style = GfTheme.typoScheme.body.smallBold)
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(167.dp)
                    .background(color = green30)
            ) {}
        }
    }
}

@Composable
fun Benefit2(onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0XFF2B2C32),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .defaultMinSize(minHeight = 80.dp)
                .padding(horizontal = 15.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = onClick
                ),
            verticalArrangement = Arrangement.Center
        ) {
            GfText(
                text = "농민 혜택 카드 출시",
                style = GfTheme.typoScheme.body.smallBold,
                color = GfTheme.colorScheme.contents.onPrimary,
            )
            GfText(
                text = "농약사, 하나로마트, 병원, 약국 결제시 5% 적립",
                style = GfTheme.typoScheme.caption.xSmallRegular,
                color = GfTheme.colorScheme.contents.onPrimary,
            )
        }
    }
}

@Composable
fun SeedBenefitContent(
    bottomSheetState: GfBottomSheetState = rememberGfBottomSheetState(initialValue = GfBottomSheetValue.Hidden),
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    Column(
        modifier = Modifier.wrapContentHeight()
    ) {
        androidx.compose.material3.Surface(
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

