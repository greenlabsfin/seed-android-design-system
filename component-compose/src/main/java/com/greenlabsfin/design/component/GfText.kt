package com.greenlabsfin.design.component

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.LocalGfColorScheme
import com.greenlabsfin.design.core.LocalGfTypoScheme
import com.greenlabsfin.design.core.color.red60

@Composable
fun GfText(
    modifier: Modifier = Modifier,
    text: String? = null,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    count: Count? = null,
    badge: Boolean = false,
    color: Color = Color.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalGfTypoScheme.current.body.mediumBold,
) {

    // text color priority color -> style -> local provider
    val textColor = color.takeOrElse {
        style.color.takeOrElse {
            LocalGfColorScheme.current.contents.neutralPrimary
        }
    }

    // using text and counter
    val mergedStyle = style.merge(
        TextStyle(
            color = textColor,
        )
    )

    // create annotated text
    // ordering is
    // 1. left icon
    // 2. text
    // 3. new badge
    // 4. count
    // 5. right icon
    val annotatedText = buildAnnotatedString {
        leftIcon?.let { appendInlineContent(leftIconId) }
        append(text ?: "")
        if (badge) appendInlineContent(badgeId)
        count?.let { appendInlineContent(countId) }
        rightIcon?.let { appendInlineContent(rightIconId) }
    }

    val iconPlaceHolder = Placeholder(
        width = with(LocalDensity.current) {
            iconSize.plus(iconMargin).dp.toSp()
        },
        height = with(LocalDensity.current) {
            iconSize.dp.toSp()
        },
        placeholderVerticalAlign = PlaceholderVerticalAlign.Center
    )
    val badgePlaceHolder = Placeholder(
        width = with(LocalDensity.current) {
            badgeRadius.times(2).plus(badgeMargin).dp.toSp()
        },
        height = with(LocalDensity.current) {
            badgeRadius.times(2).dp.toSp()
        },
        placeholderVerticalAlign = PlaceholderVerticalAlign.Top
    )

    val inlineContent = mutableMapOf<String, InlineTextContent>()
    leftIcon?.let { imageVector ->
        inlineContent[leftIconId] = InlineTextContent(iconPlaceHolder) {
            Icon(
                modifier = Modifier.padding(end = iconMargin.dp),
                imageVector = imageVector,
                contentDescription = imageVector.name,
                tint = textColor
            )
        }
    }
    rightIcon?.let { imageVector ->
        inlineContent[rightIconId] = InlineTextContent(iconPlaceHolder) {
            Icon(
                modifier = Modifier.padding(start = iconMargin.dp),
                imageVector = imageVector,
                contentDescription = imageVector.name,
                tint = textColor
            )
        }
    }
    if (badge) {
        inlineContent[badgeId] = InlineTextContent(badgePlaceHolder) {
            Canvas(
                modifier = Modifier
                    .width(badgeRadius.times(2).plus(badgeMargin).dp),
                onDraw = {
                    drawCircle(
                        color = red60,
                        radius = badgeRadius.dp.toPx(),
                        center = Offset(4.dp.toPx(), 4.dp.toPx())
                    )
                })
        }
    }
    count?.let {
        val length = it.count.toString().length
        inlineContent[countId] = InlineTextContent(Placeholder(
            width = with(LocalDensity.current) {
                countSize.times(length).plus(countPadding.times(2)).plus(countMargin).dp.toSp()
            },
            height = with(LocalDensity.current) {
                countSize.plus(countPadding.times(2)).dp.toSp()
            },
            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
        )) {
            Counter(
                count = count
            )
        }
    }

    Text(
        modifier = modifier,
        text = annotatedText,
        style = mergedStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        inlineContent = inlineContent
    )
}

// TODO[tae:2022/12/19] 코멘트 친 부분은 Experimental 이라 좀 깨름칙, 이후 정식 버전에 들어가면 native canvas 대신에 쓰자
@Composable
internal fun Counter(
    count: Count,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 2.dp)
            .clip(CircleShape)
            .background(color = count.containerColor),
        contentAlignment = Alignment.Center
    ) {
        val text = count.count.toString()
//        val textMeasurer = rememberTextMeasurer()
//        val textLayoutResult = textMeasurer.measure(text = AnnotatedString(text))
//        val textSize = textLayoutResult.size

        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                val paint = Paint().apply {
                    this.textSize = count.style.fontSize.toPx()
                    this.color = count.textColor.toArgb()
                    this.textAlign = Paint.Align.CENTER
                }
                val canvasWidth = size.width
                val canvasHeight = size.height
                val yPosition = canvasHeight.div(2) - paint.descent().plus(paint.ascent()).div(2)
                drawContext.canvas.nativeCanvas.drawText(
                    text,
                    canvasWidth.div(2),
                    yPosition,
                    paint
                )

                // this is experimental, so...
//            drawText(
//                textMeasurer = textMeasurer,
//                text = text,
//                topLeft = Offset(
//                    (canvasWidth - textSize.width) / 2f,
//                    (canvasHeight - textSize.height) / 2f
//                ),
//                style = count.style.merge(TextStyle(color = count.textColor)),
//            )
            })
    }
}

private const val leftIconId = "leftIcon"
private const val rightIconId = "rightIcon"
private const val badgeId = "badge"
private const val countId = "count"

private const val iconSize = 20
private const val iconMargin = 4
private const val badgeRadius = 2
private const val badgeMargin = 2
private const val countSize = 8
private const val countPadding = 4
private const val countMargin = 2

@Immutable
data class Count(
    val count: Int,
    val textColor: Color,
    val containerColor: Color,
    val style: TextStyle,
)

@Preview(
    showBackground = true,
)
@Composable
fun GfTextPreview() {
    GfTheme {
        Surface(
            color = GfTheme.colorScheme.container.background
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                GfText(
                    leftIcon = Icons.Filled.Menu,
                    text = "텍스트",
                    badge = true,
                    count = Count(
                        count = 3,
                        textColor = GfTheme.colorScheme.contents.onInverse,
                        containerColor = GfTheme.colorScheme.container.inverse,
                        style = GfTheme.typoScheme.caption.xSmallMedium
                    ),
                    rightIcon = Icons.Filled.ArrowDropDown
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    fontScale = .5f
)
@Composable
fun GfTextPreviewSmallFont() {
    GfTheme {
        Surface(
            color = GfTheme.colorScheme.container.background
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                GfText(
                    leftIcon = Icons.Filled.Menu,
                    text = "텍스트",
                    badge = true,
                    count = Count(
                        count = 3,
                        textColor = GfTheme.colorScheme.contents.onInverse,
                        containerColor = GfTheme.colorScheme.container.inverse,
                        style = GfTheme.typoScheme.caption.xSmallMedium
                    ),
                    rightIcon = Icons.Filled.ArrowDropDown
                )
            }
        }
    }
}
