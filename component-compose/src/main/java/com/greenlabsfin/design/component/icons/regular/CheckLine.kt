package com.greenlabsfin.design.component.icons.regular

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.component.icons.Icons

val Icons.OutlinedRegular.CheckLine: ImageVector
    get() {
        if (_checkLine != null) {
            return _checkLine!!
        }
        _checkLine = Builder(name = "checkLine", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
                viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(17.7999f, 37.5999f)
                curveTo(17.3199f, 37.5999f, 16.8699f, 37.4099f, 16.5299f, 37.0699f)
                lineTo(7.5299f, 28.0699f)
                curveTo(6.8299f, 27.3699f, 6.8299f, 26.2299f, 7.5299f, 25.5199f)
                curveTo(8.2299f, 24.8199f, 9.3699f, 24.8199f, 10.0699f, 25.5199f)
                lineTo(17.7099f, 33.1599f)
                lineTo(37.4499f, 10.6099f)
                curveTo(38.0999f, 9.86f, 39.2399f, 9.79f, 39.9899f, 10.4399f)
                curveTo(40.7399f, 11.0899f, 40.8099f, 12.2299f, 40.1599f, 12.9799f)
                lineTo(19.1499f, 36.9899f)
                curveTo(18.8199f, 37.3599f, 18.3499f, 37.5899f, 17.8599f, 37.5999f)
                curveTo(17.8399f, 37.5999f, 17.8199f, 37.5999f, 17.7999f, 37.5999f)
                close()
            }
        }
        .build()
        return _checkLine!!
    }

private var _checkLine: ImageVector? = null
