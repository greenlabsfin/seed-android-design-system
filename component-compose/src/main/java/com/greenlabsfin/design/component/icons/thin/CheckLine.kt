package com.greenlabsfin.design.component.icons.thin

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

val Icons.OutlinedThin.CheckLine: ImageVector
    get() {
        if (_checkLine != null) {
            return _checkLine!!
        }
        _checkLine = Builder(name = "checkLine", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
                viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(18.0734f, 37.4516f)
                curveTo(17.6734f, 37.4516f, 17.2734f, 37.2516f, 16.9734f, 37.0516f)
                lineTo(7.9734f, 28.0516f)
                curveTo(7.3734f, 27.4516f, 7.3734f, 26.5516f, 7.9734f, 25.9516f)
                curveTo(8.5734f, 25.3516f, 9.4734f, 25.3516f, 10.0734f, 25.9516f)
                lineTo(17.9734f, 33.8516f)
                lineTo(37.8734f, 11.0516f)
                curveTo(38.3734f, 10.4516f, 39.3734f, 10.3516f, 39.9734f, 10.9516f)
                curveTo(40.5734f, 11.4516f, 40.6734f, 12.4516f, 40.0734f, 13.0516f)
                lineTo(19.0734f, 37.0516f)
                curveTo(18.9734f, 37.2516f, 18.5734f, 37.4516f, 18.0734f, 37.4516f)
                close()
            }
        }
        .build()
        return _checkLine!!
    }

private var _checkLine: ImageVector? = null
