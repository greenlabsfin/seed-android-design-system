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
import co.seedglobal.design.component.SeedBottomSheetState
import co.seedglobal.design.component.SeedBottomSheetValue
import co.seedglobal.design.component.SeedButton
import co.seedglobal.design.component.SeedButtonDefaults
import co.seedglobal.design.component.SeedText
import co.seedglobal.design.component.SeedTextButton
import co.seedglobal.design.component.rememberSeedBottomSheetState
import co.seedglobal.design.component.util.CatchScrollUp
import co.seedglobal.design.component.util.DecorateBackground
import co.seedglobal.design.core.SeedTheme
import co.seedglobal.design.core.color.green30
import co.seedglobal.design.core.color.red50
import co.seedglobal.design.core.color.white
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
            Divider(color = SeedTheme.colorScheme.container.outline)
            Spacer(modifier = Modifier.height(28.dp))
            Benefit2(onClick = {
                onShowBottomSheet({ SeedBenefitContent() }, true)
            })
            Spacer(modifier = Modifier.height(36.dp))
            SeedText(text = "돈이되는 지원사업", style = SeedTheme.typoScheme.headline.smallBold)
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
        SeedText(
            modifier = Modifier.weight(1f),
            text = "나에게 맞는 \n보조금, 지원사업은?", style = SeedTheme.typoScheme.body.mediumBold)
        SeedButton(
            text = "검색",
            size = SeedButton.Size.XSmall,
            colors = SeedButtonDefaults.Colors.tintNeutral(),
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
            SeedText(text = "2023년 \n기본형 공익직불금", style = SeedTheme.typoScheme.headline.smallBold)
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                SeedText(
                    modifier = Modifier.weight(1f),
                    text = "전체 농가 대상",
                    style = SeedTheme.typoScheme.body.smallBold,
                    color = SeedTheme.colorScheme.contents.neutralSecondary,
                )
                SeedText(
                    text = "D-20",
                    style = SeedTheme.typoScheme.body.smallBold,
                    color = red50,
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            SeedButton(
                modifier = Modifier.fillMaxWidth(),
                text = "공고보기",
                colors = SeedButtonDefaults.Colors.tintPrimary(),
                size = SeedButton.Size.Small,
                onClick = onClick
            )
            Spacer(modifier = Modifier.height(20.dp))
            Divider(color = SeedTheme.colorScheme.container.outline)
            Spacer(modifier = Modifier.height(26.dp))
            SeedText(text = "관련 교육", style = SeedTheme.typoScheme.body.smallBold)
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
            SeedText(
                text = "농민 혜택 카드 출시",
                style = SeedTheme.typoScheme.body.smallBold,
                color = SeedTheme.colorScheme.contents.onPrimary,
            )
            SeedText(
                text = "농약사, 하나로마트, 병원, 약국 결제시 5% 적립",
                style = SeedTheme.typoScheme.caption.xSmallRegular,
                color = SeedTheme.colorScheme.contents.onPrimary,
            )
        }
    }
}

@Composable
fun SeedBenefitContent(
    bottomSheetState: SeedBottomSheetState = rememberSeedBottomSheetState(initialValue = SeedBottomSheetValue.Hidden),
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    Column(
        modifier = Modifier.wrapContentHeight()
    ) {
        androidx.compose.material3.Surface(
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
                size = SeedButton.Size.Large,
                colors = SeedButtonDefaults.Colors.tintNeutral(),
                text = "알아보기",
            ) {
                scope.launch { bottomSheetState.hide() }
            }
        }
    }
}

